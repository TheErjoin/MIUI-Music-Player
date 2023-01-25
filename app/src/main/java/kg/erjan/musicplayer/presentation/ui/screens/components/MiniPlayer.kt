package kg.erjan.musicplayer.presentation.ui.screens.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.ui.theme.EerieBlack
import kg.erjan.musicplayer.presentation.ui.theme.SpanishGray

@Composable
fun MiniPlayer(modifier: Modifier) {
    AnimatedVisibility(
        visible = true,
        enter = slideIn {
            IntOffset(0, it.height / 2)
        } + fadeIn(),
        exit = slideOut {
            IntOffset(0, it.height / 2)
        } + fadeOut(),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.background(
                EerieBlack
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                MiniPlayerContent()
            }
        }
    }
}

@Composable
fun MiniPlayerContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(18.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_music_logo),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                "Let me hear",
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Fear and loathing in las vegas",
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = SpanishGray
            )
        }
    }
}
