package com.example.githubrepo.di

import android.app.Application
import com.example.githubrepo.MainActivity
import com.example.githubrepo.TrendingRepoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: TrendingRepoFragment)
}