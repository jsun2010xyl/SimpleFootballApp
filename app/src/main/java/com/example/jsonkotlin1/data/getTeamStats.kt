package com.example.jsonkotlin1.data

import com.example.jsonkotlin1.data.db.entity.FootballTeam

enum class Result{
    WIN, LOSS, DRAW
}


fun update(list: List<FootballTeam>, teamId: String, result: Result){


    
}

fun getTeamStats(fetchedFootballStats: FootballStats): List<FootballTeam>{
    val list: List<FootballTeam> = mutableListOf()
    fetchedFootballStats.forEach {
        // Use teamId because two teams could have the same name



    }
    return list
}