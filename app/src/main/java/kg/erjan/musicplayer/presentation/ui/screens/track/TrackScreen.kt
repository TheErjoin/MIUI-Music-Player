package kg.erjan.musicplayer.presentation.ui.screens.track

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.ui.theme.AmericanViolet
import kg.erjan.musicplayer.presentation.ui.theme.BlueViolet
import kg.erjan.musicplayer.presentation.ui.theme.Indigo
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrackScreen() {
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .background(Indigo)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ToolbarMusicInfo(pagerState)
        LogoAndLyricsMusic(pagerState)
        MusicInfo()
        MusicSlider()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LogoAndLyricsMusic(pagerState: PagerState) {

}

@Composable
private fun MusicSlider() {
//    Slider(value =, onValueChange =)
}

@Composable
private fun MusicInfo() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ToolbarMusicInfo(pagerState: PagerState) {
    val tabData = listOf(
        stringResource(R.string.tracks),
        stringResource(id = R.string.lyrics_song)
    )

    Box(
        modifier = Modifier.fillMaxWidth()
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
