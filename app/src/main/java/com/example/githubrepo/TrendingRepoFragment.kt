package com.example.githubrepo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.githubrepo.ui.ViewModelFactory
import com.example.githubrepo.ui.viewModel.TrendingRepoViewModel
import com.example.githubrepo.ui.viewModel.ViewState
import javax.inject.Inject

class TrendingRepoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        val app = context.applicationContext as GithubRepoApplication
        app.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trending_repo, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = getViewModelByFactory<TrendingRepoViewModel>(factory = viewModelFactory) {
            getTrendingRepoList().observe(viewLifecycleOwner, Observer {
                bindViewState(it)
            })
        }

        viewModel.fetchTrendingRepos()
    }

    private fun bindViewState(it: ViewState) {

    }
}