package com.shreyash.github.starwar.Activity

import android.os.Bundle
import android.service.autofill.FieldClassification.Match
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shreyash.github.starwar.MatchesAdapter
import com.shreyash.github.starwar.PointsAdapter
import com.shreyash.github.starwar.R
import com.shreyash.github.starwar.SharedPreferencesUtils
import com.shreyash.github.starwar.data.Matches
import com.shreyash.github.starwar.data.Users
import com.shreyash.github.starwar.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersMatchesActivity : AppCompatActivity() {


    lateinit var matchesRecyclerView: RecyclerView
    lateinit var matchesAdapter: MatchesAdapter
    var userId = 0
    var list = ArrayList<Matches>()
    var retrievedList = HashMap<Int, Users>()
    lateinit var userName :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users_matches)

        matchesRecyclerView = findViewById(R.id.matchesRecyclerView)
        userName = findViewById(R.id.userName)
        userId = intent.getIntExtra("userId", 0)
        userName.text = intent.getStringExtra("userName").toString()
        list = ArrayList<Matches>()
        matchesRecyclerView.layoutManager = LinearLayoutManager(this)
        matchesAdapter = MatchesAdapter(list,userId)
        matchesRecyclerView.adapter = matchesAdapter

        matchesAdapter.notifyDataSetChanged()

        retrievedList = SharedPreferencesUtils.getHashMap(this)
        fetchMatches()
    }


    private fun fetchMatches() {
        val apiService = RetrofitClient.instance
        apiService.getMatches().enqueue(object : Callback<List<Matches>> {
            override fun onResponse(call: Call<List<Matches>>, response: Response<List<Matches>>) {
                if (response.isSuccessful) {
                    val matchList = response.body() ?: emptyList()
                    var matchListFinal = ArrayList<Matches>()

                    for (i in matchList) {

                        println(i.match)
                        var payerId1 = i.player1?.id
                        var payerId2 = i.player2?.id

                        if (payerId1 != null || payerId2 != null) {
                            if (userId == payerId1 || userId == payerId2) {
                                matchListFinal.add(i)
                                if (retrievedList.containsKey(payerId1!!)) {
                                    i.player1?.name = retrievedList[payerId1]?.name.toString()
                                }
                                if (retrievedList.containsKey(payerId2!!)) {
                                    i.player2?.name = retrievedList[payerId2]?.name.toString()
                                }
                            }


                        }

                    }
                    matchListFinal.reverse()
                    list.addAll(matchListFinal)
                    matchesAdapter.notifyDataSetChanged()



                    Log.d("MainActivity", "Fetched Matches: $matchList")
                    // Here you could set up another RecyclerView adapter if needed
                } else {
                    Log.e("MainActivity", "Failed to retrieve matches: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Matches>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching matches", t)
            }
        })
    }
}