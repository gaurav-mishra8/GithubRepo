package com.example.githubrepo.di

import android.app.Application
import androidx.room.Room
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

}