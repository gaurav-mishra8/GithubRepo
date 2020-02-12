package com.example.githubrepo.mapper

import com.example.githubrepo.data.local.TrendingRepo
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ViewMapperTest {

    private lateinit var viewMapper: ViewMapper

    @Before
    fun setUp() {
        viewMapper = ViewMapper()
    }

    @Test
    fun `mapper returns non-null values for null keys`() {
        val viewRepoModel = viewMapper.map(getNullKeysRepoItem())

        assertNotNull(viewRepoModel.author)
        assertNotNull(viewRepoModel.name)
        assertNotNull(viewRepoModel.description)
        assertNotNull(viewRepoModel.avatar)
    }

    private fun getNullKeysRepoItem(): TrendingRepo {
        val trendingRepo = TrendingRepo(
            author = "trendingAuthor",
            name = null,
            description = null,
            avatar = null
        )

        return trendingRepo
    }


}