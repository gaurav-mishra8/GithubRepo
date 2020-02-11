package com.example.githubrepo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrendingRepo::class], version = 1)
abstract class TrendingRepoDb : RoomDatabase() {

    abstract fun trendingRepoDao(): TrendingRepoDao

}