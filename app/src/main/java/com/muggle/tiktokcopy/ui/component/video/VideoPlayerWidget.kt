package com.muggle.tiktokcopy.ui.component.video

import android.text.TextUtils
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.media3.common.Timeline
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.ContentFrame
import coil3.compose.AsyncImage
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.home.state.SingleVideoUiState
import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorWidgetType
import com.muggle.tiktokcopy.ui.component.video.bean.PlayerEventType
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
    contentScale: ContentScale,
    singleVideoUiState: SingleVideoUiState,
    onPlayerCallback: (PlayerEventType) -> Unit = {},
    onReceiveWidgetClickAct: (VideoWidgetClickAct) -> Unit = {}
) {

    val videoState by remember {
        mutableStateOf(singleVideoUiState)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(760.cdp)
    ) {
        VideoPlayer(
            player = player,
            contentScale = contentScale,
            videoUrl = videoState.videoUrl,
            videoCoverUrl = videoState.videoCoverUrl,
            onPlayerCallback = {
                onPlayerCallback(it)
            }
        )
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
                DanmakuEditEntrance(
                    onClick = {
                        onReceiveWidgetClickAct(VideoWidgetClickAct.ClickDanmakuEditEntrance)
                    }
                )
                if (null != singleVideoUiState.recommendState) {
                    Spacer(modifier = Modifier.height(8.cdp))
                    RecommendWidget(
                        state = singleVideoUiState.recommendState,
                        onClick = {
                            onReceiveWidgetClickAct(VideoWidgetClickAct.ClickRecommendEntrance(it))
                        }
                    )
                }
                if (null != singleVideoUiState.videoRelativeContentType) {
                    Spacer(modifier = Modifier.height(8.cdp))
                    VideoRelativeWidget(
                        state = singleVideoUiState.videoRelativeContentType,
                        onClick = {
                            onReceiveWidgetClickAct(VideoWidgetClickAct.ClickVideoRelativeWidget(it))
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.cdp))
                VideoAuthor(
                    userName = "字节跳动",
                    authorWidgetType = AuthorWidgetType.CreateTogether(
                        authorList = createAuthorList()
                    ),
                    onClickAct = {
                        onReceiveWidgetClickAct(it)
                    }
                )
                if (!TextUtils.isEmpty(videoState.videoContentDesc)) {
                    // TODO 点击事件
                    VerticalDivider(8.cdp)
                    VideoContentDesc(videoState.videoContentDesc)
                }
            }
            if (videoState.videoBottomWidgetType != null) {
                VerticalDivider(8.cdp)
                VideoBottomWidget(
                    videoState.videoBottomWidgetType!!,
                    onClickAct = {
                        onReceiveWidgetClickAct(it)
                    }
                )
            }
            if (videoState.videoContentWarningType != null) {
                VerticalDivider(8.cdp)
                VideoContentWarningWidget(
                    videoState.videoContentWarningType!!,
                    onClickAct = {
                        onReceiveWidgetClickAct(it)
                    }
                )
            }
            if (videoState.totalDurationMs > 15 * 1000) {
                // TODO: 进度条处理，添加数据字段
                VerticalDivider(8.cdp)
                VideoProgressWidget(chapterSecList = listOf(5, 26, 78, 93))
            } else {
                // TODO: 滑动条透明响应区域
            }
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.BottomEnd),
        ) {
            AuthorAvatarWidget(
                avatarUrl = videoState.author.avatar ?: "",
                subscribeState = videoState.subscribeState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            LikeWidget(
                countString = videoState.likeCountStr,
                likeState = videoState.likeState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                })
            VerticalDivider(8.cdp)
            CommentEntranceWidget(
                commentCountStr = videoState.commentCountStr,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            AddCollectWidget(
                collectCountString = videoState.collectCountStr,
                collectState = videoState.collectState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            ShareWidget(
                shareCountString = videoState.shareCountStr,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            MusicAlbumEntrance(
                albumState = videoState.musicAlbumState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(
                if (videoState.videoBottomWidgetType is VideoBottomWidgetType.ListenMusic) {
                    0.cdp
                } else {
                    54.cdp
                }
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun BoxScope.VideoPlayer(
    player: Player,
    contentScale: ContentScale,
    videoUrl: String = "",
    videoCoverUrl: String = "",
    onPlayerCallback: (PlayerEventType) -> Unit = {}
) {

    val playerListener = remember {
        object : Player.Listener {
            override fun onEvents(
                player: Player,
                events: Player.Events
            ) {
                super.onEvents(player, events)
                onPlayerCallback(PlayerEventType.Event(events))
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                onPlayerCallback(PlayerEventType.PlayBackStateChange(playbackState))
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                onPlayerCallback(PlayerEventType.IsPlayingChanged(isPlaying))
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                onPlayerCallback(PlayerEventType.PlayerError(error))
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                super.onRepeatModeChanged(repeatMode)
                onPlayerCallback(PlayerEventType.RepeatModeChanged(repeatMode))
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                super.onPlayWhenReadyChanged(playWhenReady, reason)
                onPlayerCallback(PlayerEventType.PlayWhenReadyChanged(playWhenReady, reason))
            }

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                reason: Int
            ) {
                super.onPositionDiscontinuity(oldPosition, newPosition, reason)
                onPlayerCallback(
                    PlayerEventType.PositionDiscontinuity(
                        oldPosition,
                        newPosition,
                        reason
                    )
                )
            }

            override fun onVideoSizeChanged(videoSize: VideoSize) {
                super.onVideoSizeChanged(videoSize)
                onPlayerCallback(PlayerEventType.VideoSizeChanged(videoSize))
            }

            override fun onTimelineChanged(
                timeline: Timeline,
                reason: Int
            ) {
                super.onTimelineChanged(timeline, reason)
                onPlayerCallback(PlayerEventType.TimelineChanged(timeline, reason))
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                super.onIsLoadingChanged(isLoading)
                onPlayerCallback(PlayerEventType.IsPlayingChanged(isLoading))
            }

            override fun onPlayerErrorChanged(error: PlaybackException?) {
                super.onPlayerErrorChanged(error)
                onPlayerCallback(PlayerEventType.PlayerErrorChanged(error))
            }

            override fun onVolumeChanged(volume: Float) {
                super.onVolumeChanged(volume)
                onPlayerCallback(PlayerEventType.VolumeChanged(volume))
            }

            override fun onDeviceVolumeChanged(volume: Int, muted: Boolean) {
                super.onDeviceVolumeChanged(volume, muted)
                onPlayerCallback(PlayerEventType.DeviceVolumeChanged(volume, muted))
            }

            override fun onSurfaceSizeChanged(width: Int, height: Int) {
                super.onSurfaceSizeChanged(width, height)
                onPlayerCallback(PlayerEventType.SurfaceSizeChanged(width, height))
            }

            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()
                onPlayerCallback(PlayerEventType.RenderedFirstFrame)
            }
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

    LaunchedEffect(videoUrl) {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
    }

    LaunchedEffect(Unit) {

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
            shutter = {
                AsyncImage(
                    model = videoCoverUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
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