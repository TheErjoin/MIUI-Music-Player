package kg.erjan.musicplayer.presentation.ui.screens.track

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrackScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    val tabData = listOf(
        stringResource(R.string.tracks),
        stringResource(id = R.string.lyrics_song)
    )

    Column(
        modifier = Modifier
            .background(Indigo)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        ToolbarMusicInfo(pagerState, tabData)
        Spacer(modifier = Modifier.height(22.dp))
        LogoAndLyricsMusic(pagerState, tabData)
        Spacer(modifier = Modifier.height(42.dp))
        MusicInfo()
        MusicSlider()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LogoAndLyricsMusic(pagerState: PagerState, tabData: List<String>) {
    HorizontalPager(
        count = tabData.size,
        state = pagerState,
    ) { page ->
        Column {
            when (page) {
                0 -> ImageMusic()
                else -> LyricsMusic()
            }
        }
    }
}

@Composable
private fun LyricsMusic() {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .fillMaxHeight(0.45F),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.no_text), fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(45.dp))
        OutlinedButton(
            onClick = { },
            border = BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(50),
            modifier = Modifier.background(Color.Transparent),
            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
        ) {
            Text(
                text = stringResource(R.string.added),
                fontSize = 14.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun ImageMusic() {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .fillMaxHeight(0.45F)
            .background(BrightLavender)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_playlist),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun MusicSlider() {
//    Slider(value =, onValueChange =)
}

@Composable
private fun MusicInfo() {
    val isFavorite = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "The Gong of Knockout",
                fontSize = 21.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Fear,and Loathing in Las Vegas",
                fontSize = 14.sp,
                color = TropicalViolet
            )
        }
        Image(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) { isFavorite.value = !isFavorite.value }
                .align(Alignment.TopEnd),
            painter = painterResource(id = if (!isFavorite.value) R.drawable.ic_favorite_no_active else R.drawable.ic_favorite_active),
            contentDescription = null,
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ToolbarMusicInfo(pagerState: PagerState, tabData: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { /*TODO onClick */ },
        )
        Tabs(pagerState, tabData, Modifier.align(Alignment.Center))
        Image(
            painter = painterResource(id = R.drawable.ic_more_track),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(20.dp)
                .clickable { /*TODO onClick */ },
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tabs(pagerState: PagerState, tabData: List<String>, modifier: Modifier) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .height(32.dp)
            .width(200.dp),
        backgroundColor = BlueViolet,
        indicator = {},
    ) {
        tabData.forEachIndexed { index, _ ->
            val selected = pagerState.currentPage == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        AmericanViolet
                    )
                else Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        BlueViolet
                    ),
                text = {
                    Text(
                        text = tabData[index],
                        fontSize = 11.sp,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray,
                        maxLines = 1,
                        modifier = Modifier.requiredWidth(130.dp),
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
            )
        }
    }
}
