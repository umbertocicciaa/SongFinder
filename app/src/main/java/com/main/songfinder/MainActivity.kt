package com.main.songfinder

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.main.songfinder.SongFinderApplication.Companion.context

/**
 *  Questi classe si occupa dell'activity principale
 *  @author umbertodomenicociccia
 * */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        val data: SharedPreferences = context.getSharedPreferences("song_finder", MODE_PRIVATE)
        data.edit().clear().apply()
        Log.d("MainActivity", "cleared data")
    }


}