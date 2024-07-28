package com.cme.clef.ui.main.tabs.favorites.components.favorites_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.common.components.image.DefaultAsyncImage
import com.cme.clef.ui.theme.ClefTheme

@Composable
fun FavoriteMusicAlbumItem(modifier: Modifier = Modifier,album: AlbumInfo) {

        Row(modifier = modifier) {
            DefaultAsyncImage(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
                imageUrl = album.imageUrl ?: "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/eb777e7a-7d3c-487e-865a-fc83920564a1/d7kpm65-437b2b46-06cd-4a86-9041-cc8c3737c6f0.jpg/v1/fill/w_800,h_800,q_75,strp/no_album_art__no_cover___placeholder_picture_by_cmdrobot_d7kpm65-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9ODAwIiwicGF0aCI6IlwvZlwvZWI3NzdlN2EtN2QzYy00ODdlLTg2NWEtZmM4MzkyMDU2NGExXC9kN2twbTY1LTQzN2IyYjQ2LTA2Y2QtNGE4Ni05MDQxLWNjOGMzNzM3YzZmMC5qcGciLCJ3aWR0aCI6Ijw9ODAwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.8yjX5CrFjxVH06LB59TpJLu6doZb0wz8fGQq4tM64mg"
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(horizontalAlignment = Alignment.Start) {

                Text(text = album.name, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Black))

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = album.artistName, style = MaterialTheme.typography.labelMedium)

            }

        }


}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun FavoriteMusicAlbumItemPreview() {
    ClefTheme {
        FavoriteMusicAlbumItem(
           album =   AlbumInfo(
                "id",
                "The Pink House",
                "Sam Smith",
                "Aug 20, 2024",
                "",
                "",
                true,
                listOf()
            )
        )
    }
}