package com.example.jsonkotlin1.data

import com.example.jsonkotlin1.data.db.entity.FootballTeam

enum class Result{
    WIN, LOSS, DRAW
}


fun update(list: List<FootballTeam>, teamId: String, result: Result){
    for ( i in list.indices ){
        if ( list[i].teamId == teamId ){
            when (result) {
                Result.WIN -> {
                    list[i].wins++
                }
                Result.DRAW -> {
                    list[i].draws++
                }
                else -> {
                    list[i].losses++
                }
            }
            return
        }
    }

    list.add(FootballTeam())
}

fun getTeamStats(fetchedFootballStats: FootballStats): List<FootballTeam>{
    val list: List<FootballTeam> = mutableListOf()
    fetchedFootballStats.forEach {
        // Use teamId because two teams could have the same name
        if ( it.homeScore > it.awayScore ){
            // home team wins
            update(list, it.homeTeamId, Result.WIN)
            update(list, it.awayTeamId, Result.LOSS)
        } else if ( it.homeScore == it.awayScore ){
            update(list, it.homeTeamId, Result.DRAW)
            update(list, it.awayTeamId, Result.DRAW)
        } else {
            update(list, it.homeTeamId, Result.LOSS)
            update(list, it.awayTeamId, Result.WIN)
        }
    }
    return list
}