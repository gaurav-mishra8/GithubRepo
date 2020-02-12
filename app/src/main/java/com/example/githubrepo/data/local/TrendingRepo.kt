package com.example.githubrepo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class TrendingRepo(
    @field:SerializedName("author") val author: String?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("avatar") val avatar: String?,
    @field:SerializedName("description") val description: String?
)