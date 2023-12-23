package com.main.songfinder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Questa classe fornisce variabili globali utili allo sviluppo
 * @author umbertodomenicociccias
 * */
class SongFinderApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        /**
         * token fornito dal provider delle api
         * */
        const val TOKEN = "obNeGsUzN5SzzWHFeqTHYV7Z9fhaHSM2HdThUgZlPv5Y2eXjxYRqpAGuYWyVYQdy"

        /**
         * header per le https request al provider
         * */
        const val HEADER = "Bearer $TOKEN"

        /**
         * valore positivo per il response
         * */
        const val RESPONSE_OK: Int = 200
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}