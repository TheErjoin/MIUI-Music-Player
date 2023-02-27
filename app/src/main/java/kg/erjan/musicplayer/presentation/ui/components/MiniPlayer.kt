package kg.erjan.musicplayer.presentation.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.erjan.domain.entities.tracks.Tracks
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.extensions.navigate
import kg.erjan.musicplayer.presentation.ui.helpers.Auxiliary
import kg.erjan.musicplayer.presentation.ui.helpers.Screen
import kg.erjan.musicplayer.presentation.ui.theme.EerieBlack
import kg.erjan.musicplayer.presentation.ui.theme.SpanishGray

@Composable
fun MiniPlayer(
    modifier: Modifier,
    auxiliary: Auxiliary
) {
    val currentSong by remember {
        mutableStateOf(auxiliary.musicPlayerRemote.currentSong)
    }

    val isPlaying by remember {
        mutableStateOf(auxiliary.musicPlayerRemote.isPlaying)
    }

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
            modifier = Modifier
                .background(
                    EerieBlack
                )
                .clickable {
                    auxiliary.navController.navigate(Screen.TRACK_SCREEN)
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    MiniPlayerContent(tracks = currentSong)
                }
                IconButton(onClick = {
                    if (!isPlaying){
                        auxiliary.musicPlayerRemote.resumePlaying()
                    }else{
                        auxiliary.musicPlayerRemote.pauseSong()
                    }
                }) {
                    Image(
                        painter = painterResource(id = if (!isPlaying) R.drawable.ic_play else R.drawable.ic_pause),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    auxiliary.musicPlayerRemote.playNextSong()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_skip_next),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun MiniPlayerContent(tracks: Tracks) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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
                text = tracks.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tracks.artistName,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = SpanishGray
            )
        }
    }
}
