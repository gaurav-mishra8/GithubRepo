package com.example.githubrepo.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.TrendingRepoDataSource
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingRepoViewModel @Inject constructor(
    private val dataSource: TrendingRepoDataSource
) : ViewModel() {

    private val uiStateLiveData = MutableLiveData<ViewState>()

    fun getTrendingRepoList(): LiveData<ViewState> = uiStateLiveData

    fun fetchTrendingRepos() {
        uiStateLiveData.value = ViewState(isLoading = true)

        viewModelScope.launch {
            try {
                val viewRepoList = dataSource.fetchTrendingRepo()

                uiStateLiveData.value = getCurrentViewState().copy(
                    isLoading = false,
                    data = viewRepoList,
                    error = null,
                    isRefreshing = false
                )

            } catch (e: Exception) {

                uiStateLiveData.value =
                    getCurrentViewState().copy(isLoading = false, data = null, error = e.message)

            }
        }
    }

    private fun getCurrentViewState(): ViewState {
        return uiStateLiveData.value!!
    }


}

data class ViewState(
    val isLoading: Boolean = false,
    val data: List<ViewTrendingRepoModel>? = null,
    val error: String? = "",
    val isRefreshing: Boolean = false
)