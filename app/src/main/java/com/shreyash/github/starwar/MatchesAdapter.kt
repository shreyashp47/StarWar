package com.shreyash.github.starwar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shreyash.github.starwar.data.Matches
import com.shreyash.github.starwar.data.Users

class MatchesAdapter(private val userList: List<Matches>, private val userId: Int) :
    RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val player1: TextView = view.findViewById(R.id.player1)
        val player2: TextView = view.findViewById(R.id.player2)
        val matchPoints: TextView = view.findViewById(R.id.matchPoints)
        val lay: LinearLayout = view.findViewById(R.id.lay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = userList[position]
        holder.player1.text = match.player1?.name
        holder.player2.text = match.player2?.name

        holder.matchPoints.text =
            match.player1?.score.toString() + " - " + match.player2?.score.toString()

        if (userId == match.player1?.id)
            if (match.player1?.score!! > match.player2?.score!!) {
                holder.lay.setBackgroundColor(holder.itemView.context.getColor(R.color.green))

            } else if (match.player2?.score!! > match.player1?.score!!) {
                holder.lay.setBackgroundColor(holder.itemView.context.getColor(R.color.red))
            }

        if (userId == match.player2?.id){
            if (match.player2?.score!! > match.player1?.score!!) {
                holder.lay.setBackgroundColor(holder.itemView.context.getColor(R.color.green))
            } else if (match.player1?.score!! > match.player2?.score!!) {
                holder.lay.setBackgroundColor(holder.itemView.context.getColor(R.color.red))
            }
        }


    }

    override fun getItemCount() = userList.size
}