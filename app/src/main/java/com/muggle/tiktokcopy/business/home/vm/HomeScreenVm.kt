package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.intent.ScrollTabClickAct
import com.muggle.tiktokcopy.business.home.intent.VideoPlayAct
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.home.repo.HomeScreenRepo
import com.muggle.tiktokcopy.business.home.state.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2026/3/22 23:26
 * @author muggle
 * @desc
 */
@HiltViewModel
class HomeScreenVm @Inject constructor(private val repo: HomeScreenRepo) : ViewModel() {
    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState

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

    fun onReceiveScrollTabClickAct(scrollTabClickAct: ScrollTabClickAct) {
        when (scrollTabClickAct) {
            ScrollTabClickAct.LongClickTab -> {
                TODO()
            }

            is ScrollTabClickAct.SelectTab -> {
                _homeScreenUiState.update {
                    it.copy(tabItemList = it.tabItemList.apply {
                        forEachIndexed { index, state ->
                            if (state.isSelected) {
                                set(index, state.copy(isSelected = false))
                            }
                        }
                        set(
                            scrollTabClickAct.index,
                            get(scrollTabClickAct.index).copy(isSelected = true)
                        )
                    })
                }
            }

            is ScrollTabClickAct.RefreshTab -> {
                TODO()
            }
        }
    }
}