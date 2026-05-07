package com.muggle.tiktokcopy.ui.component.video

import android.text.TextUtils
import android.util.Log
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.Timeline
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
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
    autoPlay: Boolean = false,
    contentScale: ContentScale = ContentScale.FillWidth,
    singleVideoUiState: SingleVideoUiState,
    onPlayerCallback: (PlayerEventType) -> Unit = {},
    onReceiveWidgetClickAct: (VideoWidgetClickAct) -> Unit = {}
) {

    Log.i(
        TAG,
        "VideoPlayerWidget compose start: singleVideoUiState = $singleVideoUiState," +
                "autoPlay = $autoPlay"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(760.cdp)
    ) {
        VideoPlayer(
            autoPlay = autoPlay,
            contentScale = contentScale,
            videoUrl = singleVideoUiState.videoUrl,
            videoCoverUrl = singleVideoUiState.videoCoverUrl,
            playing = singleVideoUiState.isPlaying,
            onPlayerCallback = {
                Log.i(TAG, "VideoPlayerWidget: onPlayerCallback it = $it")
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
                if (!TextUtils.isEmpty(singleVideoUiState.videoContentDesc)) {
                    // TODO 点击事件
                    VerticalDivider(8.cdp)
                    VideoContentDesc(singleVideoUiState.videoContentDesc)
                }
            }
            if (singleVideoUiState.videoBottomWidgetType != null) {
                VerticalDivider(8.cdp)
                VideoBottomWidget(
                    singleVideoUiState.videoBottomWidgetType!!,
                    onClickAct = {
                        onReceiveWidgetClickAct(it)
                    }
                )
            }
            if (singleVideoUiState.videoContentWarningType != null) {
                VerticalDivider(8.cdp)
                VideoContentWarningWidget(
                    singleVideoUiState.videoContentWarningType!!,
                    onClickAct = {
                        onReceiveWidgetClickAct(it)
                    }
                )
            }
            if (singleVideoUiState.totalDurationMs > 15 * 1000) {
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
                avatarUrl = singleVideoUiState.author?.avatar ?: "",
                subscribeState = singleVideoUiState.subscribeState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            LikeWidget(
                countString = singleVideoUiState.likeCountStr,
                likeState = singleVideoUiState.likeState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                })
            VerticalDivider(8.cdp)
            CommentEntranceWidget(
                commentCountStr = singleVideoUiState.commentCountStr,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            AddCollectWidget(
                collectCountString = singleVideoUiState.collectCountStr,
                collectState = singleVideoUiState.collectState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            ShareWidget(
                shareCountString = singleVideoUiState.shareCountStr,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(8.cdp)
            MusicAlbumEntrance(
                albumState = singleVideoUiState.musicAlbumState,
                onClickAct = {
                    onReceiveWidgetClickAct(it)
                }
            )
            VerticalDivider(
                if (singleVideoUiState.videoBottomWidgetType is VideoBottomWidgetType.ListenMusic) {
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
    autoPlay: Boolean = false,
    contentScale: ContentScale = ContentScale.FillWidth,
    videoUrl: String = "",
    videoCoverUrl: String = "",
    playing: Boolean = false,
    onPlayerCallback: (PlayerEventType) -> Unit = {}
) {
    Log.i(TAG, "VideoPlayer compose start: playing = $playing,autoPlay = $autoPlay")

    val ctx = LocalContext.current

    val lifecycleOwer = LocalLifecycleOwner.current

    val player = remember {
        ExoPlayer.Builder(ctx).setRenderersFactory(
            DefaultRenderersFactory(ctx).apply {
                setEnableDecoderFallback(true)
            }
        ).build().apply {
//            playWhenReady = autoPlay
        }
    }

    val isAutoPlay by remember {
        derivedStateOf {
            autoPlay
        }
    }

    var isPlayerReady by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(lifecycleOwer, isAutoPlay, isPlayerReady) {
        Log.i(TAG, "VideoPlayer: init MediaItem,videoUrl = $videoUrl")
        player.clearMediaItems()
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
    }

    val curPlayerListener = remember {
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
                if (playbackState == Player.STATE_READY) {
                    isPlayerReady = true
                    if (lifecycleOwer.lifecycle.currentState.isAtLeast(
                            Lifecycle.State.RESUMED
                        ) && isAutoPlay
                    ) {
                        player.playWhenReady = true
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                Log.i(TAG, "onIsPlayingChanged: isPlaying = $isPlaying")
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
                onPlayerCallback(PlayerEventType.IsLoadingChanged(isLoading))
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

    DisposableEffect(Unit) {
        Log.i(TAG, "VideoPlayer: init MediaItem,videoUrl = $videoUrl")

        player.addListener(curPlayerListener)

        player.clearMediaItems()
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()

        val lifecycleObserver = LifecycleEventObserver { source, event ->
            Log.i(TAG, "VideoPlayer: source = $source,event = $event")
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    if (isPlayerReady && autoPlay) {
                        player.prepare()
                        player.playWhenReady = true
                    }
                }

                Lifecycle.Event.ON_PAUSE -> {
                    player.pause()
                    player.playWhenReady = false
                }

                else -> {
                    Log.i(TAG, "VideoPlayer: event = $event")
                }
            }
        }

        lifecycleOwer.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            Log.i(TAG, "VideoPlayer: onDispose")
            player.stop()
            player.removeListener(curPlayerListener)
            if (!player.isReleased) {
                player.release()
            }
            lifecycleOwer.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(760.cdp)
            .clickable {
                Log.i(TAG, "VideoPlayer: click playing = $playing")
                if (playing) {
                    player.pause()
                } else {
                    player.play()
                }
            }
    ) {
        ContentFrame(
            modifier = Modifier.fillMaxSize(),
            player = player.apply {
                repeatMode = REPEAT_MODE_ALL
            },
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

        if (!playing) {
            Image(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .size(66.cdp),
                painter = painterResource(R.drawable.video_play_icon),
                contentDescription = "",
                alpha = 0.8f
            )
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(506.cdp))

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

private const val TAG = "VideoPlayerWidget"