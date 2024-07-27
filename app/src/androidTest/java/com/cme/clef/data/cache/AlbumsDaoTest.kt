package com.cme.clef.data.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.cache.entity.MusicAlbumEntity
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumsDaoTest: KoinTest {

    private val dao: IAlbumsDao by inject()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown()= runTest {
        dao.clearAllAlbums()
    }

    @Test
    fun getMusicAlbumsWithInsertedAlbumsThenShouldReturnSavedMusicAlbums() = runTest {

        val albums = mutableListOf<MusicAlbumEntity>()

        for (i in 1..10) {
            albums.add(MusicAlbumEntity().apply { id = "$i" })
        }
        dao.insertMusicAlbums(albums)




        val retrievedAlbums = dao.getMusicAlbums()
        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.size, `is`(10))


    }

    @Test
    fun getMusicAlbumsThenShouldReturnEmptyList() = runTest {

        val retrievedAlbums = dao.getMusicAlbums()
        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.isEmpty(), `is`(true))


    }




    @Test()
    fun insertMusicAlbumsWithSingleAlbumThenReturnInsertedAlbum() = runTest {
        val album = MusicAlbumEntity().apply { id = "1" }

        dao.insertMusicAlbums(listOf(album))


        val retrievedAlbums = dao.getMusicAlbums().firstOrNull()

        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums?.id, `is`(album.id))

    }

    @Test()
    fun insertMusicAlbumsWithMultipleAlbumsThenReturnInsertedAlbums() = runTest {
        val album1 = MusicAlbumEntity().apply { id = "1" }
        val album2 = MusicAlbumEntity().apply { id = "2" }
        val album3 = MusicAlbumEntity().apply { id = "3" }

        dao.insertMusicAlbums(listOf(album1, album2 ,album3 ))



        val retrievedAlbums = dao.getMusicAlbums()

        assertThat(retrievedAlbums.last().id, `is`("3"))

    }

    @Test()
    fun insertMusicAlbumsWithEmptyAlbumsThenReturnEmpty() = runTest {

        dao.insertMusicAlbums(listOf())


        val retrievedAlbums = dao.getMusicAlbums()

        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.isEmpty(), `is`(true))

    }



    @Test
    fun clearDataWithInsertedAlbumsThenShouldReturnEmptyList() = runTest {

        val album1 = MusicAlbumEntity().apply { id = "1" }
        val album2 = MusicAlbumEntity().apply { id = "2" }
        val album3 = MusicAlbumEntity().apply { id = "3" }

        dao.insertMusicAlbums(listOf(album1, album2 ,album3 ))

        dao.clearAllAlbums()
        val retrievedAlbums = dao.getMusicAlbums()
        assertTrue(retrievedAlbums.isEmpty())
    }


    @Test
    fun getFavoriteAlbumsThenShouldReturnFavoriteAlbumsOnly() = runTest {

        val albums = mutableListOf<MusicAlbumEntity>()

        for (i in 1..10) {
            albums.add(MusicAlbumEntity().apply { id = "$i"  ; isFavorite = i % 2 == 0})
        }

        dao.insertMusicAlbums(albums)


        val retrievedAlbums = dao.getFavoriteAlbums()
        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.size, `is`(5))


    }

    @Test
    fun setFavoriteAlbumWithFalseThenShouldRemoveOneAlbumsFromFavoriteAlbums() = runTest {

        val albums = mutableListOf<MusicAlbumEntity>()

        for (i in 1..10) {
            albums.add(MusicAlbumEntity().apply { id = "$i"  ; isFavorite = i % 2 == 0})
        }

        dao.insertMusicAlbums(albums)


        dao.setFavoriteAlbum("2", false)

        val retrievedAlbums = dao.getFavoriteAlbums()
        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.size, `is`(4))


    }

    @Test
    fun insertMusicAlbumsWithTrueThenShouldAddOneAlbumFromFavoriteAlbums() = runTest {

        val albums = mutableListOf<MusicAlbumEntity>()

        for (i in 1..10) {
            albums.add(MusicAlbumEntity().apply { id = "$i"  ; isFavorite = i % 2 == 0})
        }

        dao.insertMusicAlbums(albums)


        dao.setFavoriteAlbum("1", true)


        val retrievedAlbums = dao.getFavoriteAlbums()
        assertThat(retrievedAlbums, notNullValue())
        assertThat(retrievedAlbums.size, `is`(6))

    }



}