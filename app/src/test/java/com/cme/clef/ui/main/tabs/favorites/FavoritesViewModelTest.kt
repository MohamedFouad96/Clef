package com.cme.clef.ui.main.tabs.favorites

import com.cme.clef.di.DependencyContainer
import com.cme.clef.di.fake.FakeFavoritesRepository
import com.cme.clef.di.fake.FakeHomeRepository
import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.ui.main.tabs.home.HomeViewModel
import com.cme.clef.util.MainCoroutineRule
import com.cme.clef.util.runTest
import kotlinx.coroutines.Dispatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var repo: IFavoritesRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.favoritesViewModel
        repo = dependencyContainer.fakeFavoritesRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()



    @Test
    fun fetchFavoriteMusicAlbumsThenShouldReturnListOfAlbums() = mainCoroutineRule.runTest  {



        viewModel.fetchFavoriteMusicAlbums(Dispatchers.IO)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.musicAlbums.value?.isNotEmpty(),
            Matchers.`is`(true)
        )

        MatcherAssert.assertThat(
            viewModel.musicAlbums.value?.size,
            Matchers.`is`(3)
        )

        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )

    }


    @Test
    fun fetchFavoriteMusicAlbumsWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeFavoritesRepository).setIsWithError(true)

        viewModel.fetchFavoriteMusicAlbums(Dispatchers.IO)


        Thread.sleep(50)


        MatcherAssert.assertThat(
            viewModel.errorState.value,
            Matchers.`is`("Something Wrong")
        )
        MatcherAssert.assertThat(
            viewModel.showLoading.value,
            Matchers.`is`(false)
        )



    }


}