package com.cme.clef.di


import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.ui.main.screens.AlbumDetailsViewModel
import com.cme.clef.ui.main.tabs.favorites.FavoritesViewModel
import com.cme.clef.ui.main.tabs.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { createHomeViewModel(get()) }
    viewModel { createFavoritesViewModel(get()) }
    viewModel { createAlbumDetailsViewModel(get()) }

}

fun createHomeViewModel(repo: IHomeRepository): HomeViewModel {
    return HomeViewModel(repo)
}

fun createFavoritesViewModel(repo: IFavoritesRepository): FavoritesViewModel {
    return FavoritesViewModel(repo)
}

fun createAlbumDetailsViewModel(repo: IHomeRepository): AlbumDetailsViewModel {
    return AlbumDetailsViewModel(repo)
}


