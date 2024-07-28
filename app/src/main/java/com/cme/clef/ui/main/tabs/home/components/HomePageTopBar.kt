package com.cme.clef.ui.main.tabs.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cme.clef.R
import com.cme.clef.ui.theme.ClefTheme


@Composable
fun HomePageTopBar() {

    Column(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.what_do_you_want), style = MaterialTheme.typography.titleMedium)

    }
}


@Preview(showBackground = true)
@Composable
fun HomePageTopBarPreview() {
    ClefTheme {
        HomePageTopBar()
    }
}