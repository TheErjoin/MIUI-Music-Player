package kg.erjan.musicplayer.presentation.ui.screens.home.tracks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kg.erjan.domain.entities.tracks.Tracks
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.extensions.collectUIState
import kg.erjan.musicplayer.presentation.extensions.navigateSafely
import kg.erjan.musicplayer.presentation.ui.navigation.Screen
import kg.erjan.musicplayer.presentation.ui.theme.Grape
import kg.erjan.musicplayer.presentation.ui.theme.SpanishGray

@Composable
fun TracksScreen(
    viewModel: TracksViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column(modifier = Modifier.padding(top = 12.dp)) {
        PlayRandomOrder()
        Spacer(modifier = Modifier.height(12.dp))
        viewModel.trackState.collectAsState().collectUIState {
            TrackList(it,navController)
        }
    }
}

@Composable
private fun TrackList(tracks: List<Tracks>, navController: NavHostController) {
    LazyColumn {
        items(
            items = tracks
        ) {
            ItemTrack(onClick = { navController.navigateSafely(Screen.TRACK_SCREEN.route) }, it)
        }
    }
}

@Composable
private fun ItemTrack(onClick: () -> Unit, tracks: Tracks) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = tracks.nameTrack,
                fontSize = 14.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = buildAnnotatedString {
                        if (tracks.albumTrack == "Download" || tracks.nameTrack == "<unknown>"){
                            append(stringResource(R.string.unknown_performers))
                            append("  |  ")
                            append(stringResource(R.string.unknown_album))
                        } else {
                            append(tracks.artistTrack)
                            append("  |  ")
                            append(tracks.albumTrack)
                        }
                    },
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = SpanishGray
                )
            }
        }
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    //TODO onClick to more image
                },
            painter = painterResource(id = R.drawable.ic_more_track),
            contentDescription = null,
        )
    }
}

@Composable
private fun PlayRandomOrder() {
    Row(
        modifier = Modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ) {
            //TODO onClick for choose random track
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Grape)
                .size(36.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_play_arrow),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.play_track_random_order),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.clickable {
                    //TODO onClick to bottom sheet sorting
                },
                painter = painterResource(id = R.drawable.ic_sorting),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(14.dp))
            Image(
                modifier = Modifier.clickable {
                    //TODO onClick to select todo list from music
                },
                painter = painterResource(id = R.drawable.ic_select_todo),
                contentDescription = null
            )
        }
    }
}
