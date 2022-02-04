package com.example.jsonkotlin1.data.network

import com.example.jsonkotlin1.data.FootballStats
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FootballStatsApiService {

    @GET("soccer_game_results.json")
    fun getFootballStats(

    ): Deferred<FootballStats>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): FootballStatsApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://s.yimg.com/cv/ae/default/171221/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) // for Deferred
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FootballStatsApiService::class.java)
        }


    }


}