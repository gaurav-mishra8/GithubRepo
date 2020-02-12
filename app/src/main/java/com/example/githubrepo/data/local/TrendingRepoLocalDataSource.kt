package com.example.githubrepo.data.local

import android.content.SharedPreferences
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import javax.inject.Inject


class TrendingRepoLocalDataSource @Inject constructor(
    private val trendingRepoDao: TrendingRepoDao,
    private val prefs: SharedPreferences
) {

    private val INSERT_TIMESTAMP = "insert_timestamp"

    fun isCacheValid(): Boolean {

        val currentTimeMillis = System.currentTimeMillis()
        val storedTimeMillis = prefs.getLong(INSERT_TIMESTAMP, 0L)
        val diff = currentTimeMillis - storedTimeMillis

        val diffHours = diff / (60 * 60 * 1000)

        return diffHours < 2

    }

    suspend fun getTrendingRepos() = trendingRepoDao.fetchTrendingRepo()

    suspend fun insertAll(repoList: List<ViewTrendingRepoModel>) {
        val res = trendingRepoDao.insertAll(repoList)
        if (res.isNotEmpty()) {
            prefs.edit().putLong(INSERT_TIMESTAMP, System.currentTimeMillis()).apply()
        }
    }
}