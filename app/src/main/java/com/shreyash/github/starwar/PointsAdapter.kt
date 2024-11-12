package com.shreyash.github.starwar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shreyash.github.starwar.Activity.MatchPointsActivity
import com.shreyash.github.starwar.Activity.UsersMatchesActivity
import com.shreyash.github.starwar.data.Users

class PointsAdapter(private val userList: List<Users>) :
    RecyclerView.Adapter<PointsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName)
        val userPoints: TextView = view.findViewById(R.id.userPoints)
        val userImage: ImageView = view.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.name
        holder.userPoints.text = user.points.toString()
        Glide.with(holder.userImage.context)
            .load(user.icon)
            .into(holder.userImage)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UsersMatchesActivity::class.java).apply {
                putExtra("userName", user.name)
                putExtra("userId", user.id)
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = userList.size
}