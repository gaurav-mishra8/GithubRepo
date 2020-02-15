package com.example.githubrepo.utils

import com.example.githubrepo.ui.model.ViewTrendingRepoModel

fun getDummyTrendingRepoModelList(): List<ViewTrendingRepoModel> {

    val list = mutableListOf<ViewTrendingRepoModel>()

    repeat(5) {
        list.add(getDummyTrendingRepoModel(it + 1))
    }

    return list
}

fun getDummyTrendingRepoModel(id: Int): ViewTrendingRepoModel {

    return ViewTrendingRepoModel(
        id = id,
        author = "John",
        name = "Java Projects",
        avatar = "",
        description = "Sample java projects"
    )

}