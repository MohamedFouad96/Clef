package com.cme.clef.ui.navgraph

sealed class Route(val route: String) {

    data object None: Route(route = "none")

    data object MainNavigation: Route(route = "mainNavigation")

    data object MainScreenContent: Route(route = "mainScreenContent")

    data object HomePage: Route(route = "homePage")
    data object FavoritesPage: Route(route = "favoritesPage")
    data object AlbumDetailsScreen: Route(route = "albumDetailsScreen")


}