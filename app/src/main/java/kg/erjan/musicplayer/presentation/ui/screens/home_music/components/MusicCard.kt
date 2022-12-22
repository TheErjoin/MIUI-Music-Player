package kg.erjan.musicplayer.presentation.ui.screens.home_music.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MusicCard(
    onClick: () -> Unit,
    modifier: Modifier,
    title: String,
    icon: Int
) {
    Box(
        modifier = modifier
            .width(115.dp)
            .height(90.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}