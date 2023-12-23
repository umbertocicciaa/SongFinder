package com.main.songfinder.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Questo singleton si occupa di fornire metodi per la creazione dei vari servizi di rete. Fornisce inoltre l'endpoint a cui si appigliano le richieste
 * @author umbertodomenicociccia
 * */
object ServiceCreator {

    /**
     * end point delle api request
     * */
    private const val BASE_URL = "https://api.genius.com/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}