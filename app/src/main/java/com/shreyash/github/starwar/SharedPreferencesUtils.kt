package com.shreyash.github.starwar




import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shreyash.github.starwar.data.Users

object SharedPreferencesUtils {

    private const val PREF_NAME = "MyPrefs"
    private const val KEY_HASH_MAP = "hash_map_key"

    // Store HashMap in SharedPreferences
    fun storeHashMap(context: Context, map: HashMap<Int, Users>) {
        val gson = Gson()
        val json = gson.toJson(map) // Convert the HashMap to JSON string

        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_HASH_MAP, json)
        editor.apply() // Apply changes asynchronously
    }

    // Retrieve HashMap from SharedPreferences
    fun getHashMap(context: Context): HashMap<Int, Users> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_HASH_MAP, null)

        return if (json != null) {
            val gson = Gson()
            val type = object : TypeToken<HashMap<Int, Users>>() {}.type
            gson.fromJson(json, type) // Convert JSON string back to HashMap
        } else {
            HashMap() // Return an empty HashMap if not found
        }
    }
}
