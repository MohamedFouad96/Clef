package com.cme.clef.di

import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.repo.FavoritesRepository
import com.cme.clef.repo.HomeRepository
import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.repo.IHomeRepository
import org.koin.dsl.module


val repoModule = module {
    single { createHomeRepo(get(), get()) }
    single { createFavoritesRepo(get()) }
}

fun createHomeRepo(api: ClefApi, albumsDao: IAlbumsDao): IHomeRepository {
    return HomeRepository(api,albumsDao)
}

fun createFavoritesRepo(albumsDao: IAlbumsDao): IFavoritesRepository {
    return FavoritesRepository(albumsDao)
}