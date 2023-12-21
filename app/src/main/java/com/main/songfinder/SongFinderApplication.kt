package com.main.songfinder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SongFinderApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN="obNeGsUzN5SzzWHFeqTHYV7Z9fhaHSM2HdThUgZlPv5Y2eXjxYRqpAGuYWyVYQdy"
        const val RESPONSE_OK: Int =200
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}