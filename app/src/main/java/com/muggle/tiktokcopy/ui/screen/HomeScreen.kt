package com.muggle.tiktokcopy.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer
import com.muggle.tiktokcopy.ui.component.nav.BottomNavigator
import com.muggle.tiktokcopy.ui.component.video.VideoPlayerWidget

/**
 * @date 2026/2/6 0:16
 * @author muggle
 * @desc
 */
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // TODO: 顶部tab栏

        Column(modifier = Modifier.fillMaxSize()) {
            VideoPlayerWidget(
                player = ExoPlayer.Builder(LocalContext.current).build(),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xff181818)),
                contentAlignment = Alignment.Center
            ) {
                BottomNavigator()
            }
        }
    }
}