package com.cme.clef.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.main.MainScreenContent
import com.cme.clef.ui.main.screens.ALBUM_INFO_KEY
import com.cme.clef.ui.main.screens.AlbumDetailsScreen


@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()


    NavHost(navController = navController , startDestination = Route.MainNavigation.route) {


        navigation(route = Route.MainNavigation.route ,startDestination = Route.MainScreenContent.route) {
            composable(route = Route.MainScreenContent.route) {
                MainScreenContent(navController)
            }

            composable(route = Route.AlbumDetailsScreen.route) {

                navController.previousBackStackEntry?.savedStateHandle?.get<AlbumInfo>(
                    ALBUM_INFO_KEY)
                    ?.let { album ->

                        AlbumDetailsScreen(album, mainNavController = navController)
                    }

            }


        }
    }

}

 fun NavHostController.navigateToAlbumDetailsScreen(album: AlbumInfo) {
     currentBackStackEntry?.savedStateHandle?.set(ALBUM_INFO_KEY, album)

     navigate(
        route = Route.AlbumDetailsScreen.route
    )
}

fun NavHostController.navigateToBottomBarPage(route: String) {

    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

}
