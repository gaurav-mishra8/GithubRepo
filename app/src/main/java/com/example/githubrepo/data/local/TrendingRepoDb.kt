package com.example.githubrepo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubrepo.ui.model.ViewTrendingRepoModel

@Database(entities = [ViewTrendingRepoModel::class], version = 1, exportSchema = false)
abstract class TrendingRepoDb : RoomDatabase() {

    abstract fun trendingRepoDao(): TrendingRepoDao

}