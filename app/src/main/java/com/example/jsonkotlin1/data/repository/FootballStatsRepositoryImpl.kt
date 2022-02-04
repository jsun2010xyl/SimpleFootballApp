package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.db.FootballStatsDao
import com.example.jsonkotlin1.data.db.entity.FootballGame
import com.example.jsonkotlin1.data.db.entity.FootballTeam
import com.example.jsonkotlin1.data.network.NetworkDataSource

class FootballStatsRepositoryImpl (
    private val footballStatsDao : FootballStatsDao,
    private val footballNetworkDataSource : NetworkDataSource): FootballStatsRepository {
    override suspend fun getFootballStandings(): LiveData<List<FootballTeam>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamDetails(): LiveData<List<FootballGame>> {
        TODO("Not yet implemented")
    }
}