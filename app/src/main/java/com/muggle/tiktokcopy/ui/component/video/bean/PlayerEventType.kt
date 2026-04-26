package com.muggle.tiktokcopy.ui.component.video.bean

import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.VideoSize

/**
 * @date 2026/4/15 0:24
 * @author muggle
 * @desc
 */
sealed interface PlayerEventType {

    /**
     *
     */
    class Event(val events: Player.Events) : PlayerEventType

    /**
     *
     */
    class PlayBackStateChange(val playbackState: Int) : PlayerEventType

    /**
     *
     */
    class IsPlayingChanged(val isPlaying: Boolean) : PlayerEventType

    /**
     *
     */
    class PlayerError(val error: PlaybackException) : PlayerEventType

    /**
     *
     */
    class RepeatModeChanged(val repeatMode: Int) : PlayerEventType

    /**
     *
     */
    class PlayWhenReadyChanged(val playWhenReady: Boolean, val reason: Int) : PlayerEventType

    /**
     *
     */
    class PositionDiscontinuity(
        val oldPosition: Player.PositionInfo,
        val newPosition: Player.PositionInfo,
        val reason: Int
    ) : PlayerEventType

    /**
     *
     */
    class VideoSizeChanged(val videoSize: VideoSize) : PlayerEventType

    /**
     *
     */
    class TimelineChanged(val timeline: Timeline, val reason: Int) : PlayerEventType

    /**
     *
     */
    class IsLoadingChanged(val isLoading: Boolean) : PlayerEventType

    /**
     *
     */
    class PlayerErrorChanged(val error: PlaybackException?) : PlayerEventType

    /**
     *
     */
    class VolumeChanged(val volume: Float) : PlayerEventType

    /**
     *
     */
    class DeviceVolumeChanged(val volume: Int, val muted: Boolean) : PlayerEventType

    /**
     *
     */
    class SurfaceSizeChanged(val width: Int, val height: Int) : PlayerEventType

    /**
     *
     */
    object RenderedFirstFrame : PlayerEventType


    class CurrentPageChange(val index: Int) : PlayerEventType
}