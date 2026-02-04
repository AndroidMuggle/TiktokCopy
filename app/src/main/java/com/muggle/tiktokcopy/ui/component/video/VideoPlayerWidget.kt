package com.muggle.tiktokcopy.ui.component.video

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.ContentFrame
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorWidgetType
import com.muggle.tiktokcopy.ui.component.video.bean.VideoBottomWidgetType
import com.muggle.tiktokcopy.utils.VerticalDivider
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
        modifier = Modifier
            .fillMaxWidth()
            .height(760.cdp)
    ) {
        VideoPlayer(player = player, contentScale = contentScale)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomStart)
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 276.cdp)
                    .padding(start = 11.cdp)
            ) {
                DanmakuEditEntrance()
                Spacer(modifier = Modifier.height(8.cdp))
                RecommendWidget()
                Spacer(modifier = Modifier.height(8.cdp))
                VideoRelativeWidget()
                Spacer(modifier = Modifier.height(8.cdp))
                VideoAuthor(
                    userName = "字节跳动",
                    authorWidgetType = AuthorWidgetType.CreateTogether(
                        authorList = createAuthorList()
                    )
                )
                VerticalDivider(8.cdp)
                VideoContentDesc("我的刀盾、比比拉布、我的刀盾、比比拉布、我的刀盾、比比拉布、我的刀盾、比比拉布、我的刀盾、比比拉布、")
                VerticalDivider(8.cdp)
            }
            VideoBottomWidget(VideoBottomWidgetType.RelativeSearch("我的刀盾是什么梗"))
//            VideoContentWarningWidget(VideoContentWarningType.ContentWarning("情节演绎，注意甄别"))
//            VerticalDivider(8.cdp)
            VideoProgressWidget(chapterSecList = listOf(5, 26, 78, 93))
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.BottomEnd),
        ) {
            AuthorAvatarWidget()
            VerticalDivider(8.cdp)
            LikeWidget()
            VerticalDivider(8.cdp)
            CommentEntranceWidget()
            VerticalDivider(8.cdp)
            AddCollectWidget()
            VerticalDivider(8.cdp)
            ShareWidget()
            VerticalDivider(8.cdp)
            MusicAlbumEntrance()
            // TODO: 根据bottomWidgetType确认竖向padding
            VerticalDivider(54.cdp)
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun BoxScope.VideoPlayer(
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

        override fun onVideoSizeChanged(videoSize: VideoSize) {
            super.onVideoSizeChanged(videoSize)
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
//    VideoPlayer(
//        player = ExoPlayer.Builder(LocalContext.current).build(),
//        contentScale = ContentScale.FillWidth
//    )
}

private fun createAuthorList(): List<LoginResponseBean> {
    return arrayListOf<LoginResponseBean>().apply {
        add(
            LoginResponseBean(
                avatar = "",
                password = "",
                phoneNumber = "",
                tiktokId = "",
                userId = "",
                username = "TODO()"
            )
        )

        add(
            LoginResponseBean(
                avatar = "",
                password = "",
                phoneNumber = "",
                tiktokId = "",
                userId = "",
                username = "PUBG"
            )
        )

        add(
            LoginResponseBean(
                avatar = "",
                password = "",
                phoneNumber = "",
                tiktokId = "",
                userId = "",
                username = "火影忍者"
            )
        )

        add(
            LoginResponseBean(
                avatar = "",
                password = "",
                phoneNumber = "",
                tiktokId = "",
                userId = "",
                username = "蜡笔小新"
            )
        )
    }
}