package com.example.jsonkotlin1.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.data.FootballStats
import java.lang.Exception

class NetworkDataSourceImpl(private val apiService: FootballStatsApiService) : NetworkDataSource {
    private val _downloadedData = MutableLiveData<FootballStats>()
    override val downloadedData: LiveData<FootballStats>
        get() = _downloadedData

    override suspend fun fetchData() {
        try {
            val fetchedData = apiService.getFootballStats().await()
            _downloadedData.postValue(fetchedData)
        }catch(e: Exception){ // TODO
            Log.e("Connectivity", "No Internet connection.", e)
        }
    }
}