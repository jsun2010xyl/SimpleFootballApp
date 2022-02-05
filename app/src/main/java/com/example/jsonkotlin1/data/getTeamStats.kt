package com.example.jsonkotlin1.data

import com.example.jsonkotlin1.data.db.entity.FootballTeam

enum class Result{
    WIN, LOSS, DRAW
}

fun update(list: MutableList<FootballTeam>, name: String, teamId: String, result: Result){
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
    when (result) {
        Result.WIN -> {
            list.add(FootballTeam(teamId, name, 1, 0, 0, 0.0))
        }
        Result.DRAW -> {
            list.add(FootballTeam(teamId, name, 0, 0, 1, 0.0))
        }
        else -> {
            list.add(FootballTeam(teamId, name, 0, 1, 0, 0.0))
        }
    }
}

fun getTeamStats(fetchedFootballStats: FootballStats): List<FootballTeam>{
    val list: MutableList<FootballTeam> = mutableListOf()
    fetchedFootballStats.forEach {
        // Use teamId because two teams could have the same name
        if ( it.homeScore > it.awayScore ){
            // home team wins
            update(list, it.homeTeamName, it.homeTeamId, Result.WIN)
            update(list, it.awayTeamName, it.awayTeamId, Result.LOSS)
        } else if ( it.homeScore == it.awayScore ){
            update(list, it.homeTeamName, it.homeTeamId, Result.DRAW)
            update(list, it.awayTeamName, it.awayTeamId, Result.DRAW)
        } else {
            update(list, it.homeTeamName, it.homeTeamId, Result.LOSS)
            update(list, it.awayTeamName, it.awayTeamId, Result.WIN)
        }
    }

    list.forEach {
        it.winPercentage = it.wins.toDouble()/(it.wins+it.draws+it.losses).toDouble()
    }
    return list
}