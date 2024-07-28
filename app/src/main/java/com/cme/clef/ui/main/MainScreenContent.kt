package com.cme.clef.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cme.clef.ui.main.components.BottomNavigationItem
import com.cme.clef.ui.main.tabs.favorites.FavoritesPage
import com.cme.clef.ui.main.tabs.home.HomePage
import com.cme.clef.ui.navgraph.Route
import com.cme.clef.ui.navgraph.navigateToBottomBarPage
import com.cme.clef.ui.navgraph.navigateToAlbumDetailsScreen
import com.cme.clef.ui.theme.BlueCola
import com.cme.clef.ui.theme.ClefTheme
import com.cme.clef.ui.theme.DimGray


@Composable
fun MainScreenContent(mainNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {



            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->

                    val isSelected = navigationItem.route == currentDestination?.route
                    val label = stringResource(id = navigationItem.label)
                    val textColor = if(isSelected) BlueCola else DimGray
                    val icon = if(isSelected) navigationItem.activeIcon else navigationItem.icon

                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(label, style = MaterialTheme.typography.bodySmall, color = textColor)
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = label
                            )
                        },
                        onClick = {
                            navController.navigateToBottomBarPage(navigationItem.route)

                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = DimGray,
                        )
                    )
                }
            }
        }
    ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Route.HomePage.route,
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                composable(Route.HomePage.route) {
                   HomePage {
                       mainNavController.navigateToAlbumDetailsScreen(it)
                   }
                }

                composable(Route.FavoritesPage.route) {
                    FavoritesPage {
                        mainNavController.navigateToAlbumDetailsScreen(it)
                    }
                }
            }


    }
}





@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MainScreenContentPreview() {
    ClefTheme {
        MainScreenContent(rememberNavController())
    }
}