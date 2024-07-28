package com.cme.clef.ui.main.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.common.components.image.DefaultAsyncImage
import com.cme.clef.ui.main.screens.components.AlbumDetailsTopBar
import com.cme.clef.ui.theme.ClefTheme
import com.cme.clef.ui.theme.QuickSilver
import com.cme.clef.ui.theme.SpanishGray
import com.cme.clef.ui.theme.TaupeGray
import com.cme.clef.R

const val ALBUM_INFO_KEY = "album_info"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlbumDetailsScreen(albumInfo: AlbumInfo,mainNavController: NavHostController) {

    val uriHandler =  LocalUriHandler.current

    Scaffold(
        topBar = { AlbumDetailsTopBar(albumInfo, mainNavController) },
    ) {  innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 30.dp)) {
            Spacer(modifier = Modifier.height(20.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val imageSize = maxWidth
                DefaultAsyncImage(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(24.dp)),
                    imageUrl = albumInfo.changeImageUrlResolution(800,800) ?: "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/eb777e7a-7d3c-487e-865a-fc83920564a1/d7kpm65-437b2b46-06cd-4a86-9041-cc8c3737c6f0.jpg/v1/fill/w_800,h_800,q_75,strp/no_album_art__no_cover___placeholder_picture_by_cmdrobot_d7kpm65-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9ODAwIiwicGF0aCI6IlwvZlwvZWI3NzdlN2EtN2QzYy00ODdlLTg2NWEtZmM4MzkyMDU2NGExXC9kN2twbTY1LTQzN2IyYjQ2LTA2Y2QtNGE4Ni05MDQxLWNjOGMzNzM3YzZmMC5qcGciLCJ3aWR0aCI6Ijw9ODAwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.8yjX5CrFjxVH06LB59TpJLu6doZb0wz8fGQq4tM64mg"
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = albumInfo.joinGenresWithSeparator(), style = MaterialTheme.typography.labelMedium.copy(color = SpanishGray))

            Spacer(modifier = Modifier.height(14.dp))

            Text(text = albumInfo.name, style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = albumInfo.artistName, style = MaterialTheme.typography.labelLarge.copy(color = TaupeGray))
            Spacer(modifier = Modifier.height(15.dp))

            Text(text = albumInfo.releaseDate, style = MaterialTheme.typography.labelMedium.copy(color = QuickSilver))

            Spacer(modifier = Modifier.weight(1f))

            Button(modifier = Modifier.align(Alignment.CenterHorizontally),onClick = {
                uriHandler.openUri(albumInfo.albumLink)
            }) {
                Image(painter = painterResource(id = R.drawable.ic_play), contentDescription = "")
            }

            Spacer(modifier = Modifier.weight(1f))


            Text(text = stringResource(id = R.string.copyright), style = MaterialTheme.typography.labelMedium)

            Spacer(modifier = Modifier.height(20.dp))

        }


    }
}

@Preview(showBackground = true)
@Composable
fun AlbumDetailsScreensPreview() {
    ClefTheme {
        AlbumDetailsScreen(AlbumInfo("1","","","","","",true, listOf()),rememberNavController())
    }
}