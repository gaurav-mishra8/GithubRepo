package com.example.githubrepo.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.kotlincoroutines.main.utils.MainCoroutineScopeRule
import com.example.android.kotlincoroutines.main.utils.getValueForTest
import com.example.githubrepo.data.ITrendingRepoDataSource
import com.example.githubrepo.utils.getDummyTrendingRepoModelList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrendingRepoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    lateinit var subject: TrendingRepoViewModel

    @Before
    fun setup() {
    }

    @Test
    fun `emit view loading state`() = coroutineScope.runBlockingTest {

        val errorDataSource = mockk<ITrendingRepoDataSource>()
        coEvery() { errorDataSource.fetchTrendingRepo() } returns getDummyTrendingRepoModelList()
        subject = TrendingRepoViewModel(errorDataSource)

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

    @Test
    fun `emit view error state`() = coroutineScope.runBlockingTest {

        val errorDataSource = mockk<ITrendingRepoDataSource>()
        coEvery() { errorDataSource.fetchTrendingRepo() } throws IllegalArgumentException("Error occurred")
        subject = TrendingRepoViewModel(errorDataSource)

        coroutineScope.dispatcher.pauseDispatcher()

        val liveData = subject.getTrendingRepoList()

        subject.fetchTrendingRepos()

        assertEquals(
            ViewState(isLoading = true), liveData.getValueForTest()
        )

        coroutineScope.dispatcher.resumeDispatcher()

        assertEquals(
            ViewState(isLoading = false, data = null, error = "Error occurred"),
            liveData.getValueForTest()
        )

    }


}