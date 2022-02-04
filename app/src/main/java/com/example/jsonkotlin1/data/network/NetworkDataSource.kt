package com.example.jsonkotlin1.data.network

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.FootballStats

interface NetworkDataSource {
    val downloadedData: LiveData<FootballStats>

    suspend fun fetchData(

    )
}