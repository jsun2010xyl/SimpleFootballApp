package com.example.jsonkotlin1.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jsonkotlin1.data.db.entity.FootballGame
import com.example.jsonkotlin1.data.db.entity.FootballTeam

@Database(entities = [FootballGame::class, FootballTeam::class], version = 1)
abstract class FootballStatsDatabase: RoomDatabase() {
    abstract fun footballStatsDao() : FootballStatsDao

    companion object {
        @Volatile private var instance: FootballStatsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, FootballStatsDatabase::class.java, "itemdb.db")
                .build()
    }

}