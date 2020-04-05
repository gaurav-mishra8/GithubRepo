package com.example.githubrepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubrepo.ui.ViewModelFactory
import com.example.githubrepo.ui.viewModel.TrendingRepoViewModel
import com.example.githubrepo.ui.viewModel.ViewState
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as GithubRepoApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = getViewModelByFactory<TrendingRepoViewModel>(factory = viewModelFactory) {
            getTrendingRepoList().observe(this@MainActivity, Observer {
                bindViewState(it)
            })
        }

        viewModel.fetchTrendingRepos()
    }

    private fun bindViewState(it: ViewState) {

    }
}
