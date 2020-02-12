package com.example.githubrepo.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.githubrepo.data.local.TrendingRepoDao
import com.example.githubrepo.data.local.TrendingRepoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Application): TrendingRepoDb {
        return Room.databaseBuilder(
            context,
            TrendingRepoDb::class.java, "github.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTrendingRepoDao(db: TrendingRepoDb): TrendingRepoDao {
        return db.trendingRepoDao()
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Application): SharedPreferences {
        return context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

}