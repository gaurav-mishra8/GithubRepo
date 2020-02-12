package com.example.githubrepo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepo.ui.model.ViewTrendingRepoModel

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trendingRepo: ViewTrendingRepoModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(trendingRepos: List<ViewTrendingRepoModel>): List<Long>

    @Query("SELECT * from ViewTrendingRepoModel")
    suspend fun fetchTrendingRepo(): List<ViewTrendingRepoModel>
}