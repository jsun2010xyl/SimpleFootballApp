package com.example.jsonkotlin1
import kotlinx.serialization.Serializable

@Serializable
data class TotalGame(val name:String, var wins: Int, var losses: Int, var draws: Int, var totalGame: Int)

@Serializable
data class Team(val name:String, var wins:Int, var losses:Int, var draws:Int, var winP:Float, var T:MutableList<TotalGame>)
