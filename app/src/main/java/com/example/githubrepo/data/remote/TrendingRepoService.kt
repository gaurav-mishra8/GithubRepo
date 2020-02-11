package com.example.githubrepo.data.remote

import com.example.githubrepo.data.local.TrendingRepo
import retrofit2.http.GET

interface TrendingRepoService {

    @GET("/repositories?since=daily")
    suspend fun fetchTrendingRepos(): List<TrendingRepo>
}