package kg.erjan.musicplayer.presentation.ui.screens.home

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.google.accompanist.permissions.*
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.App
import kg.erjan.musicplayer.presentation.ui.screens.components.MiniPlayer
import kg.erjan.musicplayer.presentation.ui.screens.components.MusicCard
import kg.erjan.musicplayer.presentation.ui.screens.home.albums.AlbumsScreen
import kg.erjan.musicplayer.presentation.ui.screens.home.packages.PackagesScreen
import kg.erjan.musicplayer.presentation.ui.screens.home.performers.PerformersScreen
import kg.erjan.musicplayer.presentation.ui.screens.home.tracks.TracksScreen
import kg.erjan.musicplayer.presentation.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun MusicListScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box {
        HomeComponents(
            navController = navController,
        )
        MiniPlayer(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            player = viewModel.musicPlayerRemote
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeComponents(navController: NavHostController) {

    val musicPermissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)
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
        when (musicPermissionState.status) {
            PermissionStatus.Granted -> {
                MusicTabs(navController)
            }
            is PermissionStatus.Denied -> {
                GrandPermission()
            }
        }
    }
}

@Composable
private fun GrandPermission() {

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Text(
            text = stringResource(R.string.grant_us_permission_to_display_the_list_of_songs),
            fontSize = 13.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    ).setData(
                        Uri.parse("package:${App.PACKAGE_NAME}")
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Grape),
            modifier = Modifier
                .height(44.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(R.string.grant), fontSize = 16.sp, color = Color.White)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MusicTabs(navController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0)

    val tabData = listOf(
        stringResource(R.string.tracks),
        stringResource(R.string.performers),
        stringResource(R.string.albums),
        stringResource(R.string.packages),
    )

    Column {
        Tabs(pagerState = pagerState,tabData = tabData)
        MusicTabsScreen(pagerState = pagerState,tabData = tabData,navController = navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MusicTabsScreen(
    pagerState: PagerState,
    tabData: List<String>,
    navController: NavHostController
) {
    HorizontalPager(
        count = tabData.size,
        state = pagerState,
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (page) {
                0 -> TracksScreen(navController = navController)
                1 -> PerformersScreen()
                2 -> AlbumsScreen()
                else -> PackagesScreen()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Tabs(pagerState: PagerState, tabData: List<String>) {

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
        tabData.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = tabData[index],
                        fontSize = 13.sp,
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
