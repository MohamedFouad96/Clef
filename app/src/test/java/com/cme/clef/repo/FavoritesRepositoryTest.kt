package com.cme.clef.repo


import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.CacheState
import com.cme.clef.data.util.Resource
import com.cme.clef.di.DependencyContainer
import com.cme.clef.util.MainCoroutineRule
import com.cme.clef.util.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class FavoritesRepositoryTest {

    //Setup
    private lateinit var repo: IFavoritesRepository
    private lateinit var albumsDao: IAlbumsDao

    private val dependencyContainer = DependencyContainer()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @BeforeEach
    fun setup(){
        dependencyContainer.build()
        albumsDao = dependencyContainer.fakeAlbumsDao
        repo = dependencyContainer.favoriteRepo
    }


    @Test
    fun getFavoriteMusicAlbumsWithCachedFavoriteItemsThenShouldReturnCachedDataFavoriteItems() = mainCoroutineRule.runTest {

        dependencyContainer.setCacheStateTo(CacheState.WithFullData)


        val response = repo.getFavoriteMusicAlbums().lastOrNull()


        MatcherAssert.assertThat(response?.data?.size, `is`(50))

    }


    @Test
    fun getFavoriteMusicAlbumsWithoutCachedFavoriteItemsThenShouldReturnCachedDataFavoriteItems() = mainCoroutineRule.runTest {

        dependencyContainer.setCacheStateTo(CacheState.WithHalfData)


        val response = repo.getFavoriteMusicAlbums().lastOrNull()


        MatcherAssert.assertThat(response?.data?.isEmpty(), `is`(true))

    }





}