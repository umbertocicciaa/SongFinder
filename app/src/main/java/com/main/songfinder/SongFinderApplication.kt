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
        const val TOKEN = ""

        /**
         * valore positivo del response fornito dal provider delle api
         * */
        const val RESPONSE_OK: Int = 200

        const val FINDMEURL:String ="https://github.com/umbertocicciaa"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
