package com.muggle.tiktokcopy.business.home.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.intent.VideoPlayAct
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.home.repo.HomeScreenRepo
import com.muggle.tiktokcopy.business.home.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2026/3/22 23:26
 * @author muggle
 * @desc
 */
@HiltViewModel
class HomeScreenVm @Inject constructor(private val repo: HomeScreenRepo) : ViewModel() {
    private val _homeScreenState = mutableStateOf(HomeScreenState())
    val homeScreenState = _homeScreenState

    fun onReceiveVideoPlayAct(videoPlayAct: VideoPlayAct) {
        when (videoPlayAct) {
            is VideoPlayAct.ChangeControlLayerVisibility -> {
                TODO()
            }

            is VideoPlayAct.ChangePlaySpeed -> {
                TODO()
            }

            is VideoPlayAct.ChangeScreenIntensity -> {
                TODO()
            }

            is VideoPlayAct.ChangeVolumeValue -> {
                TODO()
            }

            VideoPlayAct.ClickAutoPlayNext -> {
                TODO()
            }

            is VideoPlayAct.ClickAutoRotate -> {
                TODO()
            }

            VideoPlayAct.ClickBackendAudioSetting -> {
                TODO()
            }

            VideoPlayAct.ClickBackendWindowSetting -> {
                TODO()
            }

            VideoPlayAct.ClickCacheVideo -> {
                TODO()
            }

            VideoPlayAct.ClickCloseTimer -> {
                TODO()
            }

            VideoPlayAct.ClickDanmakuSetting -> {
                TODO()
            }

            is VideoPlayAct.ClickDanmakuSwitch -> {
                TODO()
            }

            VideoPlayAct.ClickFeedback -> {
                TODO()
            }

            VideoPlayAct.ClickMore -> {
                TODO()
            }

            VideoPlayAct.ClickOpenOnOtherDevice -> {
                TODO()
            }

            VideoPlayAct.ClickPauseWhenFinish -> {
                TODO()
            }

            VideoPlayAct.ClickReplayOnFinish -> {
                TODO()
            }

            is VideoPlayAct.ClickSendDanmaku -> {
                TODO()
            }

            VideoPlayAct.ClickSpeedText -> {
                TODO()
            }

            VideoPlayAct.ClickTvMirror -> {
                TODO()
            }

            VideoPlayAct.ClickUninterest -> {
                TODO()
            }

            VideoPlayAct.ClickWatchLater -> {
                TODO()
            }

            VideoPlayAct.PauseVideo -> {
                TODO()
            }

            VideoPlayAct.PlayVideo -> {
                TODO()
            }

            is VideoPlayAct.SwitchOrientation -> {
                TODO()
            }
        }
    }

    fun onReceiveWidgetClickAct(videoWidgetClickAct: VideoWidgetClickAct) {
        when (videoWidgetClickAct) {
            is VideoWidgetClickAct.ClickAddToClickWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickAuthorAvatar -> {
                TODO()
            }

            VideoWidgetClickAct.ClickAuthorName -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickAuthorWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickCreateTogetherAuthorAvatar -> {
                TODO()
            }

            VideoWidgetClickAct.ClickCreateTogetherMore -> {
                TODO()
            }

            VideoWidgetClickAct.ClickDanmakuEditEntrance -> {
                TODO()
            }

            VideoWidgetClickAct.ClickDrawerMenu -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickLikeWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickMusicAlbumEntrance -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickRecommendEntrance -> {
                TODO()
            }

            VideoWidgetClickAct.ClickSearchIcon -> {
                TODO()
            }

            VideoWidgetClickAct.ClickShareWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickVideoBottomWidget -> {
                TODO()
            }

            VideoWidgetClickAct.ClickVideoCommentEntrance -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickVideoContentWarningWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.ClickVideoRelativeWidget -> {
                TODO()
            }

            is VideoWidgetClickAct.LongClickVideoProgressWidget -> {
                TODO()
            }
        }
    }
}