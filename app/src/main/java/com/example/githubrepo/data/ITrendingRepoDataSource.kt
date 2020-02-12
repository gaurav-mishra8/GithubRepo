package com.example.githubrepo.data

import com.example.githubrepo.ui.model.ViewTrendingRepoModel

interface ITrendingRepoDataSource {

    suspend fun fetchTrendingRepo(): List<ViewTrendingRepoModel>

}