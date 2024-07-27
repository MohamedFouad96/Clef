package com.cme.clef.repo


import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.util.CacheState
import com.cme.clef.data.util.NetworkState
import com.cme.clef.data.util.Resource
import com.cme.clef.di.DependencyContainer
import com.cme.clef.util.MainCoroutineRule
import com.cme.clef.util.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    //Setup
    private lateinit var repo: IHomeRepository
    private lateinit var albumsDao: IAlbumsDao
    private lateinit var api: ClefApi

    private val dependencyContainer = DependencyContainer()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @BeforeEach
    fun setup(){
        dependencyContainer.build()
        albumsDao = dependencyContainer.fakeAlbumsDao
        api = dependencyContainer.fakeClefApi
        repo = dependencyContainer.homeRepo
    }



    @Test
    fun getMusicAlbumsWithSuccessNetworkResponseThenShouldReturnDataFromLocal() = mainCoroutineRule.runTest {


        val response = repo.getMusicAlbums().lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(
            response?.data?.size,
            `is`(100)
        )

        val album = response?.data?.first()
        MatcherAssert.assertThat(album?.id, Matchers.`is`("id1"))

    }

    @Test
    fun getMusicAlbumsWithNetworkErrorAndCachedDataThenShouldReturnCachedData() = mainCoroutineRule.runTest {

        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)
        dependencyContainer.setCacheStateTo(CacheState.WithFullData)

        var response: Resource<List<AlbumInfo>>? = null

        repo.getMusicAlbums().collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }


        MatcherAssert.assertThat(response?.data?.size, `is`(100))

    }

    @Test
    fun getMusicAlbumsWithNetworkErrorWithoutCachedDataThenShouldReturnEmpty() = mainCoroutineRule.runTest {


        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)

        var response: Resource<List<AlbumInfo>>? = null

        repo.getMusicAlbums().collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }

        MatcherAssert.assertThat(response?.data?.isEmpty(), Matchers.`is`(true))


    }





}