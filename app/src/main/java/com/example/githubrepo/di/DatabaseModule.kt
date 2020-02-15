package com.example.githubrepo.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.githubrepo.data.ITrendingRepoDataSource
import com.example.githubrepo.data.TrendingRepoDataSource
import com.example.githubrepo.data.local.TrendingRepoDao
import com.example.githubrepo.data.local.TrendingRepoDb
import com.example.githubrepo.data.local.TrendingRepoLocalDataSource
import com.example.githubrepo.data.remote.TrendingRepoService
import com.example.githubrepo.mapper.ViewMapper
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

    @Singleton
    @Provides
    fun provideTrendingLocalDataSource(
        trendingRepoLocalDataSource: TrendingRepoLocalDataSource,
        trendingRepoService: TrendingRepoService,
        viewMapper: ViewMapper
    ): ITrendingRepoDataSource {
        return TrendingRepoDataSource(
            trendingRepoLocalDataSource,
            trendingRepoService = trendingRepoService,
            viewMapper = viewMapper
        )
    }


}