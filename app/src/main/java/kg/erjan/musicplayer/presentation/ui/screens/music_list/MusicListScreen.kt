package kg.erjan.musicplayer.presentation.ui.screens.music_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.ui.screens.music_list.components.MusicCard
import kg.erjan.musicplayer.presentation.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun MusicListScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 6.dp)
        ) {
            DrawerSettings {
                //TODO onClick nav to drawer
            }
            SearchBar {
                //TODO onClick nav to search screen
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        MusicCards()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.all_songs),
            fontSize = Typography.h5.fontSize,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        MusicTabs()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MusicTabs() {
    val pagerState = rememberPagerState(initialPage = 3)

    val list = listOf(
        stringResource(R.string.tracks),
        stringResource(R.string.Performers),
        stringResource(R.string.Albums),
        stringResource(R.string.packages),
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .clip(RoundedCornerShape(2.dp)),
                height = 4.dp,
                color = Grape
            )
        }) {
        list.forEachIndexed { index, _ ->
            Tab(
                modifier = Modifier.width(150.dp),
                text = {
                    Text(
                        text = list[index],
                        fontSize = 13.sp,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray,
                        maxLines = 1,
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
            )
        }
    }
}

@Composable
private fun MusicCards() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        MusicCard(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Grape, Color.White.copy(alpha = 0.2F))
                    )
                ), title = stringResource(R.string.favorite), icon = R.drawable.ic_favorite
        )
        MusicCard(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Amazon, Color.White.copy(alpha = 0.2F))
                    )
                ), title = stringResource(R.string.playlists), icon = R.drawable.ic_playlist
        )
        MusicCard(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(DirtyBrown, Color.White.copy(alpha = 0.2F))
                    )
                ), title = stringResource(R.string.recent), icon = R.drawable.ic_recent
        )
    }
}

@Composable
private fun DrawerSettings(onClick: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.ic_drawer),
        contentDescription = null,
        modifier = Modifier
            .size(28.dp)
            .clickable {
                onClick()
            })
}

@Composable
private fun SearchBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(BlackOlive)
            .height(40.dp)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.padding(start = 6.dp)
            )
            Text(
                text = stringResource(R.string.search_song_playlist_and_artist),
                fontSize = 12.sp,
                color = PhilippineSilver
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_microphone),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
}
