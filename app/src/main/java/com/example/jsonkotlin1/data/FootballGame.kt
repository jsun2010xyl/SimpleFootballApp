package com.example.jsonkotlin1.data


import com.google.gson.annotations.SerializedName

data class FootballGame(
    val awayScore: Int,
    val awayTeamId: String,
    val awayTeamName: String,
    val gameId: String,
    val homeScore: Int,
    val homeTeamId: String,
    val homeTeamName: String
)