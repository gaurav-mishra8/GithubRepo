package com.example.githubrepo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trendingRepo: TrendingRepo)

    @Query("SELECT * from TrendingRepo")
    suspend fun fetchTrendingRepo(): List<TrendingRepo>
}