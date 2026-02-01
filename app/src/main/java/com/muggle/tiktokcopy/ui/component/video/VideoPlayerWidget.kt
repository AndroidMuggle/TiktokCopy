package com.muggle.tiktokcopy.ui.component.video

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.ContentFrame
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2026/1/28 22:40
 * @author muggle
 * @desc
 */

@Composable
fun VideoPlayerWidget(
    player: Player,
    contentScale: ContentScale
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        VideoPlayer(player = player, contentScale = contentScale)
        Box(modifier = Modifier.fillMaxSize())
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun VideoPlayer(
    player: Player,
    contentScale: ContentScale
) {

    val playerListener = object : Player.Listener {
        override fun onEvents(
            player: Player,
            events: Player.Events
        ) {
            super.onEvents(player, events)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
        }

        override fun onMediaItemTransition(
            mediaItem: MediaItem?,
            reason: Int
        ) {
            super.onMediaItemTransition(mediaItem, reason)
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            super.onRepeatModeChanged(repeatMode)
        }

        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            super.onPlayWhenReadyChanged(playWhenReady, reason)
        }

        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            super.onPositionDiscontinuity(oldPosition, newPosition, reason)
        }
    }

    val curPlayerListener = remember {
        playerListener
    }

    var isPlaying by remember {
        mutableStateOf(false)
    }

    DisposableEffect(player) {
        player.addListener(curPlayerListener)
        onDispose {
            player.removeListener(curPlayerListener)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(760.cdp)
            .clickable {
                isPlaying = !isPlaying
                if (isPlaying) {
                    player.pause()
                } else {
                    player.play()
                }
            }
    ) {
        ContentFrame(
            modifier = Modifier.fillMaxSize(),
            player = player,
            contentScale = contentScale,
        )

        if (!isPlaying) {
            Image(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(66.cdp),
                painter = painterResource(R.drawable.video_play_icon),
                contentDescription = "",
                alpha = 0.2f
            )
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(476.cdp))

            Row(
                modifier = Modifier
                    .height(34.cdp)
                    .width(90.cdp)
                    .border(
                        width = 1.cdp,
                        color = Color(0x7f525252),
                        shape = RoundedCornerShape(48.cdp)
                    )
                    .clickable {
                        // TODO: 切换横竖屏
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(12.cdp))
                Image(
                    modifier = Modifier.size(18.cdp),
                    painter = painterResource(R.drawable.video_switch_orientation),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(2.cdp))
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "全屏观看",
                    fontSize = 12.csp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewVideoPlayerWidget() {
    VideoPlayer(
        player = ExoPlayer.Builder(LocalContext.current).build(),
        contentScale = ContentScale.FillWidth
    )
}