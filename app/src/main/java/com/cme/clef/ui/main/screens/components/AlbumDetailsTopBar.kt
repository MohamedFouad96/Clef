package com.cme.clef.ui.main.screens.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cme.clef.R
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.main.screens.AlbumDetailsViewModel
import com.cme.clef.ui.theme.ClefTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailsTopBar(albumInfo: AlbumInfo,mainNavController: NavHostController) {

    val viewModel = koinViewModel<AlbumDetailsViewModel>()
    val isFavorite by rememberSaveable { viewModel.isFavorite  }
    


    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl


    LaunchedEffect(key1 = isFavorite) {
        albumInfo.isFavorite = isFavorite ?: albumInfo.isFavorite
    }


    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                stringResource(id = R.string.detail),
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                mainNavController.popBackStack()
            }) {
                Icon(
                    modifier = Modifier.graphicsLayer {
                        if (isRtl) {
                            scaleX = -1f  // Flip the icon horizontally for RTL layout
                        }
                    },
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.changeAlbumFavorite(albumInfo.id, !(isFavorite ?: albumInfo.isFavorite) )
            }) {
                Image(painter = painterResource(id = if (isFavorite ?: albumInfo.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite), contentDescription = "")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun AlbumDetailsTopBarPreview() {
    ClefTheme {
        AlbumDetailsTopBar(AlbumInfo(
            "id",
            "The Pink House",
            "Sam Smith",
            "Aug 20, 2024",
            "",
            "",
            true,
            listOf()
        ),rememberNavController())
    }
}