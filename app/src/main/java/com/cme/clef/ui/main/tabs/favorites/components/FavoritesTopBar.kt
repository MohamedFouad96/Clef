package com.cme.clef.ui.main.tabs.favorites.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cme.clef.R
import com.cme.clef.ui.theme.ClefTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesTopBar() {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                stringResource(id = R.string.favorite_list),
                style = MaterialTheme.typography.titleSmall
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun WatchListTopBarPreview() {
    ClefTheme {
        FavoritesTopBar()
    }
}