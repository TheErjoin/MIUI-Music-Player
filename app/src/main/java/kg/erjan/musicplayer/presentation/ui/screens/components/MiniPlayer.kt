package kg.erjan.musicplayer.presentation.ui.screens.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kg.erjan.musicplayer.R
import kg.erjan.musicplayer.presentation.extensions.navigateSafely
import kg.erjan.musicplayer.presentation.ui.navigation.Screen
import kg.erjan.musicplayer.presentation.ui.theme.EerieBlack
import kg.erjan.musicplayer.presentation.ui.theme.SpanishGray

@Composable
fun MiniPlayer(modifier: Modifier, navController: NavHostController) {

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
                    navController.navigateSafely(Screen.TRACK_SCREEN.route)
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
                    MiniPlayerContent()
                }
                IconButton(onClick = { /*TODO onClick play*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /*TODO onClick skip next*/ }) {
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
fun MiniPlayerContent() {
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
                "Let me hear",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Fear and loathing in las vegas",
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = SpanishGray
            )
        }
    }
}
