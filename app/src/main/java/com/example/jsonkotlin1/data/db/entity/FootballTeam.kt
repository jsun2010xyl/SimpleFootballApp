package com.example.jsonkotlin1.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "footballteams")
data class FootballTeam(
    @PrimaryKey
    val teamId: String,
    val teamName: String,
    var wins: Int,
    var losses: Int,
    var draws: Int,
    var winPercentage: Double
)
