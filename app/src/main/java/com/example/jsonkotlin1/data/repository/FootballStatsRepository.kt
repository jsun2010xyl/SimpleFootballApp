package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.db.entity.FootballGame
import com.example.jsonkotlin1.data.db.entity.FootballTeam

interface FootballStatsRepository {

    suspend fun getFootballStandings(): LiveData<List<FootballTeam>>

    suspend fun getTeamDetails(): LiveData<List<FootballGame>>
}