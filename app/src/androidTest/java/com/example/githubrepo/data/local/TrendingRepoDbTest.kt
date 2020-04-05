package com.example.githubrepo.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubrepo.ui.model.ViewTrendingRepoModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TrendingRepoDbTest {

    private lateinit var trendingRepoDao: TrendingRepoDao
    private lateinit var db: TrendingRepoDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TrendingRepoDb::class.java
        ).build()
        trendingRepoDao = db.trendingRepoDao()
    }


    @Test
    fun db_insert_one_item__success() = runBlocking {

        val viewTrendingRepoModel = getDummyTrendingRepoModel(42)

        trendingRepoDao.insert(viewTrendingRepoModel)

        val listRepos = trendingRepoDao.fetchTrendingRepo()

        assertEquals(1, listRepos.size)

    }

    @Test
    fun db_insert_all_item__success() = runBlocking {

        val res = trendingRepoDao.insertAll(getDummyTrendingRepoModelList())

        val listRepos = trendingRepoDao.fetchTrendingRepo()

        assertEquals(5, listRepos.size)

    }

    @Test
    fun db_correct_item_returned() = runBlocking {

        val trendingRepoModel = getDummyTrendingRepoModel(7)

        trendingRepoDao.insert(trendingRepoModel)

        val fetchedTrendingRepoModel = trendingRepoDao.fetchTrendingRepo()

        assertEquals(trendingRepoModel, fetchedTrendingRepoModel[0])

    }


    private fun getDummyTrendingRepoModelList(): List<ViewTrendingRepoModel> {

        val list = mutableListOf<ViewTrendingRepoModel>()

        repeat(5) {
            list.add(getDummyTrendingRepoModel(it + 1))
        }

        return list
    }

    private fun getDummyTrendingRepoModel(id: Int): ViewTrendingRepoModel {

        return ViewTrendingRepoModel(
            id = id,
            author = "John",
            name = "Java Projects",
            avatar = "",
            description = "Sample java projects"
        )

    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}