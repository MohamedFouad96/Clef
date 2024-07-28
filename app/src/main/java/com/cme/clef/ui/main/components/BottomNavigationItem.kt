package com.cme.clef.ui.main.components

import com.cme.clef.R
import com.cme.clef.ui.navgraph.Route


data class BottomNavigationItem(
    val label : Int = R.string.home,
    val icon : Int = R.drawable.ic_home,
    val activeIcon: Int = R.drawable.ic_home_active,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = R.string.home,
                icon = R.drawable.ic_home,
                activeIcon = R.drawable.ic_home_active,
                route = Route.HomePage.route
            ),
            BottomNavigationItem(
                label = R.string.favorites,
                icon = R.drawable.ic_favorite,
                activeIcon = R.drawable.ic_favorite_active,
                route = Route.FavoritesPage.route
            )
        )
    }
}