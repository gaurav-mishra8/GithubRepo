package com.example.githubrepo

import android.app.Application
import com.example.githubrepo.di.AppComponent
import com.example.githubrepo.di.DaggerAppComponent

class GithubRepoApplication : Application() {

    val appComponent by lazy {
        initAppComponent()
    }

    private fun initAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }

}