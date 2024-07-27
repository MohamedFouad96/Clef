package com.cme.clef.ui.main.tabs.home

import com.cme.clef.di.DependencyContainer
import com.cme.clef.di.fake.FakeHomeRepository
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.util.MainCoroutineRule
import com.cme.clef.util.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var repo: IHomeRepository
    private val dependencyContainer = DependencyContainer()


    @BeforeEach
    fun setUp(){
        dependencyContainer.build()
        viewModel = dependencyContainer.homeViewModel
        repo = dependencyContainer.fakeHomeRepo
    }


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()



    @Test
    fun fetchMusicAlbumsThenShouldReturnListOfAlbums() = mainCoroutineRule.runTest  {



        viewModel.fetchMusicAlbums()


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
    fun fetchMusicAlbumsWithErrorThenShouldReturnErrorState() = mainCoroutineRule.runTest  {


        (repo as FakeHomeRepository).setIsWithError(true)

        viewModel.fetchMusicAlbums()


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