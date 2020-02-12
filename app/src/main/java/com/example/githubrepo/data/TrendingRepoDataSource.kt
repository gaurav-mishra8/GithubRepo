package com.example.githubrepo.data

import com.example.githubrepo.data.local.TrendingRepoLocalDataSource
import com.example.githubrepo.data.remote.TrendingRepoService
import com.example.githubrepo.mapper.ViewMapper
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepoDataSource @Inject constructor(
    private val trendingRepoLocalDataSource: TrendingRepoLocalDataSource,
    private val trendingRepoService: TrendingRepoService,
    private val viewMapper: ViewMapper
) : ITrendingRepoDataSource {

    override suspend fun fetchTrendingRepo(): List<ViewTrendingRepoModel> {

        if (trendingRepoLocalDataSource.isCacheValid()) {
            //serve data from cache
            return trendingRepoLocalDataSource.getTrendingRepos()
        } else {
            //fetch trending repos from network
            val domainRepoList = trendingRepoService.fetchTrendingRepos()

            val viewRepoList = withContext(Dispatchers.Default) {
                //map to view repoModel
                domainRepoList.map {
                    viewMapper.map(it)
                }
            }

            //save repos to local db
            trendingRepoLocalDataSource.insertAll(viewRepoList)
            return viewRepoList
        }

    }


}

