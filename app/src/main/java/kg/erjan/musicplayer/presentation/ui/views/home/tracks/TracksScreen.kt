package kg.erjan.musicplayer.presentation.ui.views.home.tracks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
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
import kg.erjan.musicplayer.presentation.ui.theme.Grape
import kg.erjan.musicplayer.presentation.ui.theme.RippleColor
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
            TrackList(it, viewModel)
        }
    }
}

@Composable
private fun TrackList(tracks: List<Tracks>, viewModel: TracksViewModel) {
    LazyColumn {
        itemsIndexed(
            items = tracks
        ) { index, item ->
            ItemTrack(onClick = { viewModel.playerRemote.openQueue(tracks,index,true) }, item)
        }
    }
}

@Composable
private fun ItemTrack(onClick: () -> Unit, tracks: Tracks) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = RippleColor)
            ) {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = tracks.title,
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
                        append(tracks.artistName)
                        append("  |  ")
                        append(tracks.albumName)
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
                .padding(end = 4.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false)
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
                painter = painterResource(id = R.drawable.ic_play),
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
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false)
                ) {
                    //TODO onClick to bottom sheet sorting
                },
                painter = painterResource(id = R.drawable.ic_sorting),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(14.dp))
            Image(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false)
                ) {
                    //TODO onClick to select todo list from music
                },
                painter = painterResource(id = R.drawable.ic_select_todo),
                contentDescription = null
            )
        }
    }
}
