package com.example.githubrepo.mapper

import com.example.githubrepo.data.local.TrendingRepo
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import javax.inject.Inject

class ViewMapper @Inject constructor() : Mapper<TrendingRepo, ViewTrendingRepoModel>() {

    override fun map(trendingRepo: TrendingRepo): ViewTrendingRepoModel {

        return ViewTrendingRepoModel(
            author = trendingRepo.author ?: "Unkown",
            name = trendingRepo.name ?: "Trending Github Repo",
            description = trendingRepo.description ?: "",
            avatar = trendingRepo.avatar
                ?: "http://st.rfclipart.com/image/thumbnail/6a-2d-e3/symbolic-open-book-Download-Royalty-free-Vector-File-EPS-1613.jpg"
        )

    }
}