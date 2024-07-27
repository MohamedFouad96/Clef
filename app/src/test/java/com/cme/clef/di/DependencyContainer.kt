package com.cme.clef.di


import com.cme.clef.data.cache.FakeAlbumsDao
import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.remote.FakeClefApi
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.util.CacheState
import com.cme.clef.data.util.NetworkState
import com.cme.clef.di.fake.FakeFavoritesRepository
import com.cme.clef.di.fake.FakeHomeRepository
import com.cme.clef.repo.FavoritesRepository
import com.cme.clef.repo.HomeRepository
import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.ui.main.screens.AlbumDetailsViewModel
import com.cme.clef.ui.main.tabs.favorites.FavoritesViewModel
import com.cme.clef.ui.main.tabs.home.HomeViewModel


class DependencyContainer {

    lateinit var fakeClefApi: ClefApi



    lateinit var fakeAlbumsDao: IAlbumsDao


    lateinit var fakeHomeRepo: IHomeRepository
    lateinit var fakeFavoritesRepo: IFavoritesRepository



    lateinit var homeRepo: IHomeRepository
    lateinit var favoriteRepo: IFavoritesRepository


    lateinit var homeViewModel: HomeViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    fun build() {

        fakeClefApi = FakeClefApi()

        fakeAlbumsDao = FakeAlbumsDao()


        fakeFavoritesRepo = FakeFavoritesRepository()
        fakeHomeRepo = FakeHomeRepository()


        homeRepo = HomeRepository(fakeClefApi,fakeAlbumsDao)
        favoriteRepo = FavoritesRepository(fakeAlbumsDao)

        homeViewModel = HomeViewModel(fakeHomeRepo)
        favoritesViewModel = FavoritesViewModel(fakeFavoritesRepo)
        albumDetailsViewModel = AlbumDetailsViewModel(fakeHomeRepo)
    }


    fun setWebServiceStateTo(state: NetworkState) {
        (fakeClefApi as FakeClefApi).state = state
    }

    fun setCacheStateTo(state: CacheState) {
        (fakeAlbumsDao as FakeAlbumsDao).state = state
    }


}