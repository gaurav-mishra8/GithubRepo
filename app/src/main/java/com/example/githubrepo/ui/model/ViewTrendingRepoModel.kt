package com.example.githubrepo.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ViewTrendingRepoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String,
    val name: String,
    val avatar: String,
    val description: String
)