package com.shreyash.github.starwar.Activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shreyash.github.starwar.PointsAdapter
import com.shreyash.github.starwar.R
import com.shreyash.github.starwar.SharedPreferencesUtils
import com.shreyash.github.starwar.data.Matches
import com.shreyash.github.starwar.data.Users
import com.shreyash.github.starwar.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchPointsActivity : AppCompatActivity() {

    lateinit var pointsRecyclerView: RecyclerView
    lateinit var btn: Button
    lateinit var pointsAdapter: PointsAdapter
    lateinit var userList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pointsRecyclerView = findViewById(R.id.pointsRecyclerView)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener() {
            if (btn.getTag().toString() == "0") {
                if (usersMap.isNotEmpty()) {
                    var sortedMap = sortMapDescending(usersMap)
                    userList.clear()
                    for (i in sortedMap) {
                        userList.add(i.value)
                    }
                    pointsRecyclerView.adapter = PointsAdapter(userList)
                    pointsAdapter.notifyDataSetChanged()
                    btn.setTag(1)
                    btn.text = "Low To High"

                }
            } else {
                if (usersMap.isNotEmpty()) {

                    var sortedMap = sortMapAscending(usersMap)
                    userList.clear()
                    for (i in sortedMap) {
                        userList.add(i.value)
                    }
                    pointsRecyclerView.adapter = PointsAdapter(userList)
                    pointsAdapter.notifyDataSetChanged()
                    btn.setTag(0)
                    btn.text = "High To Low"

                }
            }
        }
        userList = ArrayList<Users>()

        pointsRecyclerView.layoutManager = LinearLayoutManager(this)
        pointsAdapter = PointsAdapter(userList)
        pointsRecyclerView.adapter = pointsAdapter
        pointsAdapter.notifyDataSetChanged()

        fetchUsers()

    }

    val usersMap = HashMap<Int, Users>()
    private fun fetchUsers() {
        val apiService = RetrofitClient.instance
        apiService.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    //userList.addAll(list)
                    //pointsRecyclerView.adapter = PointsAdapter(userList)

                    usersMap.clear()
                    for (i in list) {
                        usersMap[i.id] = i
                    }
                    SharedPreferencesUtils.storeHashMap(application, usersMap)

                    fetchMatches()


                } else {
                    Log.e("MainActivity", "Failed to retrieve users: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching users", t)
            }
        })
    }

    private fun fetchMatches() {
        val apiService = RetrofitClient.instance
        apiService.getMatches().enqueue(object : Callback<List<Matches>> {
            override fun onResponse(call: Call<List<Matches>>, response: Response<List<Matches>>) {
                if (response.isSuccessful) {
                    val matchList = response.body() ?: emptyList()
                    var matchListFinal = ArrayList<Matches>()



                    for (match in matchList) {
                        if (match.player1?.score!! > match.player2?.score!!) {
                            if (usersMap.containsKey(match.player1?.id)) {
                                var points = usersMap[match.player1?.id]?.points
                                if (points != null) {
                                    usersMap[match.player1?.id]?.points = points + 3
                                }
                            }

                        } else if (match.player2?.score!! > match.player1?.score!!) {
                            var points = usersMap[match.player2?.id]?.points
                            if (points != null) {
                                usersMap[match.player2?.id]?.points = points + 3
                            }

                        } else {
                            var points = usersMap[match.player1?.id]?.points
                            if (points != null) {
                                usersMap[match.player1?.id]?.points = points + 1
                            }

                            points = usersMap[match.player2?.id]?.points
                            if (points != null) {
                                usersMap[match.player2?.id]?.points = points + 1
                            }

                        }

                        val sortedMap = sortMapDescending(usersMap)

                        userList.clear()
                        for (i in sortedMap) {
                            userList.add(i.value)
                        }
                        pointsRecyclerView.adapter = PointsAdapter(userList)
                        pointsAdapter.notifyDataSetChanged()

                    }





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

    fun sortMapDescending(map: HashMap<Int, Users>): Map<Int, Users> {
        return map.entries
            .sortedByDescending { it.value.points }
            .associate { it.key to it.value }
    }

    fun sortMapAscending(map: HashMap<Int, Users>): Map<Int, Users> {
        return map.entries
            .sortedBy { it.value.points }
            .associate { it.key to it.value }
    }
}