package com.example.jsonkotlin1.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "footballgames")
data class FootballGame(
    val awayScore: Int,
    val awayTeamId: String,
    val awayTeamName: String,
    @PrimaryKey
    val gameId: String,
    val homeScore: Int,
    val homeTeamId: String,
    val homeTeamName: String
)