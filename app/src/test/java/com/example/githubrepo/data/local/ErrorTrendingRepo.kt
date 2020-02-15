package com.example.githubrepo.data.local

import com.example.githubrepo.data.ITrendingRepoDataSource
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import java.lang.IllegalArgumentException

class ErrorTrendingRepo :ITrendingRepoDataSource{
    override suspend fun fetchTrendingRepo(): List<ViewTrendingRepoModel> {
        throw IllegalArgumentException("Error occurred")
    }
}