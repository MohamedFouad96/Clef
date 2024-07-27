package com.cme.clef.di


import com.cme.clef.data.cache.FakeAlbumsDao
import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.remote.FakeClefApi
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.util.CacheState
import com.cme.clef.data.util.NetworkState
import com.cme.clef.repo.FavoritesRepository
import com.cme.clef.repo.HomeRepository
import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.repo.IHomeRepository


class DependencyContainer {

    lateinit var fakeClefApi: ClefApi



    lateinit var fakeAlbumsDao: IAlbumsDao



    lateinit var homeRepo: IHomeRepository
    lateinit var favoriteRepo: IFavoritesRepository




    fun build() {

        fakeClefApi = FakeClefApi()

        fakeAlbumsDao = FakeAlbumsDao()




        homeRepo = HomeRepository(fakeClefApi,fakeAlbumsDao)
        favoriteRepo = FavoritesRepository(fakeAlbumsDao)


    }


    fun setWebServiceStateTo(state: NetworkState) {
        (fakeClefApi as FakeClefApi).state = state
    }

    fun setCacheStateTo(state: CacheState) {
        (fakeAlbumsDao as FakeAlbumsDao).state = state
    }


}