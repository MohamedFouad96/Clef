package com.cme.clef.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.remote.util.ApiErrorResponse
import com.cme.clef.data.remote.util.ApiSuccessResponse
import com.cme.clef.util.Files.ALBUMS_FILE
import com.cme.clef.util.Files.EMPTY_FILE
import com.cme.clef.util.Files.NULL_FILE
import com.cme.clef.util.enqueueResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ClefApiTest: KoinTest {

    private val api: ClefApi by inject()
    private val mockServer: MockWebServer by inject()



    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun getAlbumsWithSuccessCodeThenReturnAlbums() = runTest {
        mockServer.enqueueResponse(ALBUMS_FILE, 200)


        val response =  api.getAlbums().lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body?.feed?.results?.firstOrNull() , notNullValue())
        assertThat(response.body?.feed?.results?.firstOrNull()?.id ,  `is`("1759045282"))

    }



    @Test
    fun getAlbumsWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(ALBUMS_FILE, 413)


        val response =  api.getAlbums().lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun getAlbumsWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.getAlbums().lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }

    @Test
    fun getAlbumsWithEmptyResponseThenReturnApiSuccessResponseWithNullValues() = runTest {
        mockServer.enqueueResponse(EMPTY_FILE, 200)


        val response =  api.getAlbums().lastOrNull() as ApiSuccessResponse


        assertThat(response.body?.feed?.results?.isEmpty() , `is`(true))

    }


}