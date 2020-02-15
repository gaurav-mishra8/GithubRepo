package com.example.githubrepo.data.local

import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

class TrendingRepoLocalDataSourceTest {

    private var prefs: SharedPreferences = mock(SharedPreferences::class.java)
    private val trendingRepoDao = mock(TrendingRepoDao::class.java)

    private lateinit var trendingRepoLocalDataSource: TrendingRepoLocalDataSource

    @Before
    fun setUp() {
        trendingRepoLocalDataSource = TrendingRepoLocalDataSource(trendingRepoDao, prefs)

    }

    @Test
    fun `cache is valid true for one hour ago`() {

        `when`(prefs.getLong(anyString(), anyLong())).thenReturn(getOneHourAgoTime())

        val isCacheValid = trendingRepoLocalDataSource.isCacheValid()

        assertEquals(true, isCacheValid)
    }

    @Test
    fun `cache is valid false for two hour ago`() {

        `when`(prefs.getLong(anyString(), anyLong())).thenReturn(getTwoHourAgoTime())

        val isCacheValid = trendingRepoLocalDataSource.isCacheValid()

        assertEquals(false, isCacheValid)
    }

    @Test
    fun `cache is valid false for three hour ago`() {

        `when`(prefs.getLong(anyString(), anyLong())).thenReturn(getThreeHourAgoTime())

        val isCacheValid = trendingRepoLocalDataSource.isCacheValid()

        assertEquals(false, isCacheValid)
    }


    private fun getTwoHourAgoTime(): Long {
        val now = Calendar.getInstance()
        now.add(Calendar.HOUR, -2)
        val twohoursAgoMillis = now.timeInMillis
        return twohoursAgoMillis
    }

    private fun getOneHourAgoTime(): Long {
        val now = Calendar.getInstance()
        now.add(Calendar.HOUR, -1)
        val onehourAgoMillis = now.timeInMillis
        return onehourAgoMillis
    }

    private fun getThreeHourAgoTime(): Long {
        val now = Calendar.getInstance()
        now.add(Calendar.HOUR, -3)
        val threeHourAgoMillis = now.timeInMillis
        return threeHourAgoMillis
    }


}