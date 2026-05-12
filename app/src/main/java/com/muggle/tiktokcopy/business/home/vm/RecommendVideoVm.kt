package com.muggle.tiktokcopy.business.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.intent.VideoPlayAct
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.home.repo.HomeScreenRepo
import com.muggle.tiktokcopy.business.home.state.RecommendTabVideoUiState
import com.muggle.tiktokcopy.ui.component.video.bean.PlayerEventType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2026/4/2 0:52
 * @author muggle
 * @desc
 */
@HiltViewModel
class RecommendVideoVm @Inject constructor(private val repo: HomeScreenRepo) : ViewModel() {
    private val _recommendTabVideoUiState = MutableStateFlow(RecommendTabVideoUiState())
    val recommendTabVideoUiState: StateFlow<RecommendTabVideoUiState> = _recommendTabVideoUiState

    fun onReceiveVideoPlayAct(videoPlayAct: VideoPlayAct) {
        when (videoPlayAct) {
            is VideoPlayAct.ChangeControlLayerVisibility -> {
                //TODO()
            }

            is VideoPlayAct.ChangePlaySpeed -> {
                //TODO()
            }

            is VideoPlayAct.ChangeScreenIntensity -> {
                //TODO()
            }

            is VideoPlayAct.ChangeVolumeValue -> {
                //TODO()
            }

            VideoPlayAct.ClickAutoPlayNext -> {
                //TODO()
            }

            is VideoPlayAct.ClickAutoRotate -> {
                //TODO()
            }

            VideoPlayAct.ClickBackendAudioSetting -> {
                //TODO()
            }

            VideoPlayAct.ClickBackendWindowSetting -> {
                //TODO()
            }

            VideoPlayAct.ClickCacheVideo -> {
                //TODO()
            }

            VideoPlayAct.ClickCloseTimer -> {
                //TODO()
            }

            VideoPlayAct.ClickDanmakuSetting -> {
                //TODO()
            }

            is VideoPlayAct.ClickDanmakuSwitch -> {
                //TODO()
            }

            VideoPlayAct.ClickFeedback -> {
                //TODO()
            }

            VideoPlayAct.ClickMore -> {
                //TODO()
            }

            VideoPlayAct.ClickOpenOnOtherDevice -> {
                //TODO()
            }

            VideoPlayAct.ClickPauseWhenFinish -> {
                //TODO()
            }

            VideoPlayAct.ClickReplayOnFinish -> {
                //TODO()
            }

            is VideoPlayAct.ClickSendDanmaku -> {
                //TODO()
            }

            VideoPlayAct.ClickSpeedText -> {
                //TODO()
            }

            VideoPlayAct.ClickTvMirror -> {
                //TODO()
            }

            VideoPlayAct.ClickUninterest -> {
                //TODO()
            }

            VideoPlayAct.ClickWatchLater -> {
                //TODO()
            }

            VideoPlayAct.PauseVideo -> {
                _recommendTabVideoUiState.update {
                    it.copy(
                        videoUiStateList = it.videoUiStateList.apply {
                            set(it.selectIndex, get(it.selectIndex).copy(isPlaying = false))
                        }
                    )
                }
                Log.i(TAG, "onReceiveVideoPlayAct: ")
            }

            VideoPlayAct.PlayVideo -> {
                _recommendTabVideoUiState.update {
                    it.copy(
                        videoUiStateList = it.videoUiStateList.apply {
                            set(it.selectIndex, get(it.selectIndex).copy(isPlaying = true))
                        }
                    )
                }
            }

            is VideoPlayAct.SwitchOrientation -> {
                //TODO()
            }
        }
    }

    fun onReceiveWidgetClickAct(videoWidgetClickAct: VideoWidgetClickAct) {
        when (videoWidgetClickAct) {
            is VideoWidgetClickAct.ClickAddToCollectWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickAuthorAvatar -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickAuthorName -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickAuthorWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickCreateTogetherAuthorAvatar -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickCreateTogetherMore -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickDanmakuEditEntrance -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickDrawerMenu -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickLikeWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickMusicAlbumEntrance -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickRecommendEntrance -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickSearchIcon -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickShareWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickVideoBottomWidget -> {
                //TODO()
            }

            VideoWidgetClickAct.ClickVideoCommentEntrance -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickVideoContentWarningWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.ClickVideoRelativeWidget -> {
                //TODO()
            }

            is VideoWidgetClickAct.LongClickVideoProgressWidget -> {
                //TODO()
            }
        }
    }

    fun onReceivePlayerEvent(playerEventType: PlayerEventType) {
        Log.i(TAG, "onReceivePlayerEvent: playerEventType = $playerEventType")
        when (playerEventType) {
            is PlayerEventType.DeviceVolumeChanged -> {
                //TODO()
            }

            is PlayerEventType.Event -> {
                //TODO()
            }

            is PlayerEventType.IsLoadingChanged -> {
                //TODO()
            }

            is PlayerEventType.IsPlayingChanged -> {
                _recommendTabVideoUiState.update {
                    it.copy(
                        videoUiStateList = it.videoUiStateList.apply {
                            set(
                                it.selectIndex,
                                get(it.selectIndex).copy(isPlaying = playerEventType.isPlaying)
                            )
                        }
                    )
                }
                Log.i(
                    TAG,
                    "onReceivePlayerEvent: videoUiStateList[0] = ${_recommendTabVideoUiState.value.videoUiStateList[0]}"
                )
            }

            is PlayerEventType.PlayBackStateChange -> {
                //TODO()
            }

            is PlayerEventType.PlayWhenReadyChanged -> {
                //TODO()
            }

            is PlayerEventType.PlayerError -> {
                //TODO()
            }

            is PlayerEventType.PlayerErrorChanged -> {
                //TODO()
            }

            is PlayerEventType.PositionDiscontinuity -> {
                //TODO()
            }

            PlayerEventType.RenderedFirstFrame -> {
                //TODO()
            }

            is PlayerEventType.RepeatModeChanged -> {
                //TODO()
            }

            is PlayerEventType.SurfaceSizeChanged -> {
//                //TODO()
            }

            is PlayerEventType.TimelineChanged -> {
                //TODO()
            }

            is PlayerEventType.VideoSizeChanged -> {
                //TODO()
            }

            is PlayerEventType.VolumeChanged -> {
                //TODO()
            }

            is PlayerEventType.CurrentPageChange -> {
                _recommendTabVideoUiState.update {
                    val oldIndex = it.selectIndex
                    it.copy(
                        selectIndex = playerEventType.index,
                        videoUiStateList = it.videoUiStateList.apply {
                            set(oldIndex, get(playerEventType.index).copy(isPlaying = false))
                            set(
                                playerEventType.index,
                                get(playerEventType.index).copy(isPlaying = false)
                            )
                        }
                    )
                }
                Log.i(TAG, "onReceivePlayerEvent: playerEventType.index = ${playerEventType.index}")
            }

            is PlayerEventType.CurrentPageOffsetFraction -> {
                _recommendTabVideoUiState.update {
                    it.copy(videoUiStateList = it.videoUiStateList.apply {
                        set(
                            it.selectIndex,
                            get(it.selectIndex).copy(isScrolling = playerEventType.isScrolling)
                        )
                    })
                }
            }
        }
    }

    companion object {
        private const val TAG = "RecommendVideoVm"
    }
}