package com.example.githubrepo.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.kotlincoroutines.main.utils.MainCoroutineScopeRule
import com.example.android.kotlincoroutines.main.utils.getValueForTest
import com.example.githubrepo.data.ITrendingRepoDataSource
import com.example.githubrepo.data.TrendingRepoDataSource
import com.example.githubrepo.data.local.MockTrendingRepo
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import com.example.githubrepo.utils.getDummyTrendingRepoModelList
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TrendingRepoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val dataSource = MockTrendingRepo()

    lateinit var subject: TrendingRepoViewModel

    @Before
    fun setup() {
        subject = TrendingRepoViewModel(dataSource)
    }

    @Test
    fun `emit view loading state`() = coroutineScope.runBlockingTest {

        coroutineScope.dispatcher.pauseDispatcher()

        val liveData = subject.getTrendingRepoList()

        subject.fetchTrendingRepos()

        assertEquals(
            ViewState(isLoading = true), liveData.getValueForTest()
        )

        coroutineScope.dispatcher.resumeDispatcher()

        assertEquals(
            ViewState(isLoading = false, data = getDummyTrendingRepoModelList()),
            liveData.getValueForTest()
        )

    }


}