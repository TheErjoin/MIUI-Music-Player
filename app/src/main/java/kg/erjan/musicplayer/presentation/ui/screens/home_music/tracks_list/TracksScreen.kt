package kg.erjan.musicplayer.presentation.ui.screens.home_music.tracks_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TracksScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Music",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}