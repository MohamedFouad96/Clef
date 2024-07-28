package com.cme.clef.ui.common.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cme.clef.R
import com.cme.clef.ui.theme.ClefTheme
import com.cme.clef.ui.theme.SpanishGray


@Composable
fun EmptyState(title: String,message: String, image: Painter) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.weight(1f))


        Image(painter = image, contentDescription = "")
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = title, style = MaterialTheme.typography.titleSmall.copy(textAlign = TextAlign.Center))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = message, style = MaterialTheme.typography.labelMedium.copy(color = SpanishGray,textAlign = TextAlign.Center))


        Spacer(modifier = Modifier.weight(1f))

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun EmptyStatePreview() {
    ClefTheme {
        EmptyState(stringResource(id = R.string.we_are_sorry),stringResource(id = R.string.find_your_favorite_album),painterResource(id = R.drawable.ic_no_results))
    }
}