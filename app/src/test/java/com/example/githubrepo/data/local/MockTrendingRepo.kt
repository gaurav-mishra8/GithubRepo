package com.example.githubrepo.data.local

import com.example.githubrepo.data.ITrendingRepoDataSource
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import com.example.githubrepo.utils.getDummyTrendingRepoModelList

class MockTrendingRepo : ITrendingRepoDataSource {

    override suspend fun fetchTrendingRepo(): List<ViewTrendingRepoModel> {
        return getDummyTrendingRepoModelList()
    }
}