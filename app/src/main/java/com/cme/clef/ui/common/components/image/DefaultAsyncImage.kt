package com.cme.clef.ui.common.components.image

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.cme.clef.ui.common.components.feedback.DefaultCircularProgressIndicator
import com.cme.clef.ui.theme.ClefTheme
import com.cme.clef.ui.theme.DimGray
import com.cme.clef.R

@SuppressLint("SuspiciousIndentation")
@Composable
fun DefaultAsyncImage(modifier: Modifier = Modifier,imageUrl: String) {

    val isLoading = rememberSaveable { mutableStateOf(true) }
    val isError = rememberSaveable { mutableStateOf(false) }

    val model = ImageRequest.Builder(context = LocalContext.current).data(imageUrl)
        .crossfade(true).build()

            Box(
                modifier = modifier.background(DimGray),
                        contentAlignment = Alignment.Center

            ) {

                if (isLoading.value)
                    DefaultCircularProgressIndicator()

                if(isError.value)
                    Image(painter = painterResource(id = R.drawable.ic_warning), contentDescription = "")

                AsyncImage(modifier = Modifier.matchParentSize(),model = imageUrl, contentDescription = "", contentScale = ContentScale.Crop, onState = {

                    isError.value = it is AsyncImagePainter.State.Error

                    when(it) {
                        is AsyncImagePainter.State.Loading ->  isLoading.value = true
                        AsyncImagePainter.State.Empty, is AsyncImagePainter.State.Success,is AsyncImagePainter.State.Error -> isLoading.value = false
                    }


                })

            }



}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun EventItemPreview() {
    ClefTheme {
        DefaultAsyncImage(imageUrl = "")
    }
}