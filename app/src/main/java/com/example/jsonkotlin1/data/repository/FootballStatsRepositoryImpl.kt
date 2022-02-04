package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.FootballStats
import com.example.jsonkotlin1.data.db.FootballStatsDao
import com.example.jsonkotlin1.data.db.entity.FootballGame
import com.example.jsonkotlin1.data.db.entity.FootballTeam
import com.example.jsonkotlin1.data.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FootballStatsRepositoryImpl (
    private val footballStatsDao : FootballStatsDao,
    private val footballNetworkDataSource : NetworkDataSource): FootballStatsRepository {

    init{
        footballNetworkDataSource.downloadedData.observeForever { newFootballStats ->
            persistFetchedFootballStats(newFootballStats)
        }
    }

    private fun persistFetchedFootballStats(fetchedFootballStats: FootballStats){
        GlobalScope.launch(Dispatchers.IO) {
            // fetchedItemList是ItemList，参数是List<Item>
            // 可以从Arraylist到List
            footballStatsDao.upsertGames(fetchedFootballStats)
            // TODO : upsertTeamStats
            
        }
    }


    override suspend fun getFootballStandings(): LiveData<List<FootballTeam>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamDetails(): LiveData<List<FootballGame>> {
        TODO("Not yet implemented")
    }
}