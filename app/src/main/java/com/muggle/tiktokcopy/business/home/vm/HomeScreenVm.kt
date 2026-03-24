package com.muggle.tiktokcopy.business.home.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.business.home.intent.BottomNavigatorClickAct
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

    fun onReceiveBottomNaviClickAct(bottomNaviClickAct: BottomNavigatorClickAct) {
        when (bottomNaviClickAct) {
            BottomNavigatorClickAct.ClickCreateVideoPage -> {
                _homeScreenState.value =
                    _homeScreenState.value.copy(
                        bottomNavigatorState = _homeScreenState.value.bottomNavigatorState.apply {
                            forEachIndexed { index, state ->
                                if (state.isSelected) {
                                    set(index, state.copy(isSelected = false))
                                }
                            }
                            set(0, first().copy(navName = "首页"))
                            set(2, get(2).copy(isSelected = true, newMessageCount = 0))
                        }
                    )
            }

            BottomNavigatorClickAct.ClickFriendPage -> {
                _homeScreenState.value =
                    _homeScreenState.value.copy(
                        bottomNavigatorState = _homeScreenState.value.bottomNavigatorState.apply {
                            forEachIndexed { index, state ->
                                if (state.isSelected) {
                                    set(index, state.copy(isSelected = false))
                                }
                            }
                            set(0, first().copy(navName = "首页"))
                            set(1, get(1).copy(isSelected = true, newMessageCount = 0))
                        }
                    )
            }

            is BottomNavigatorClickAct.ClickHomePage -> {
                _homeScreenState.value =
                    _homeScreenState.value.copy(
                        bottomNavigatorState = _homeScreenState.value.bottomNavigatorState.apply {
                            if (first().isSelected) {
                                when (bottomNaviClickAct.homePageType) {
                                    HomePageClickType.SingleVideo -> {
                                        set(
                                            0, first().copy(
                                                navIcon = R.drawable.common_nav_switch,
                                                navName = "首页"
                                            )
                                        )
                                    }

                                    HomePageClickType.VideoList -> {
                                        set(
                                            0,
                                            first().copy(
                                                navIcon = R.drawable.common_nav_left,
                                                navName = "返回"
                                            )
                                        )
                                    }
                                }

                            } else {
                                forEachIndexed { index, state ->
                                    if (state.isSelected) {
                                        set(index, state.copy(isSelected = false))
                                    }
                                }
                                when (bottomNaviClickAct.homePageType) {
                                    HomePageClickType.SingleVideo -> {
                                        set(
                                            0, first().copy(
                                                navIcon = R.drawable.common_nav_switch,
                                                navName = "首页",
                                                isSelected = true
                                            )
                                        )
                                    }

                                    HomePageClickType.VideoList -> {
                                        set(
                                            0,
                                            first().copy(
                                                navIcon = R.drawable.common_nav_left,
                                                navName = "返回",
                                                isSelected = true
                                            )
                                        )
                                    }
                                }
                            }
                        },
                        curHomePageVideoType = bottomNaviClickAct.homePageType
                    )
            }

            BottomNavigatorClickAct.ClickMessagePage -> {
                _homeScreenState.value =
                    _homeScreenState.value.copy(
                        bottomNavigatorState = _homeScreenState.value.bottomNavigatorState.apply {
                            forEachIndexed { index, state ->
                                if (state.isSelected) {
                                    set(index, state.copy(isSelected = false))
                                }
                            }
                            set(0, first().copy(navName = "首页"))
                            set(3, get(3).copy(isSelected = true))
                        }
                    )
            }

            BottomNavigatorClickAct.ClickMinePage -> {
                _homeScreenState.value =
                    _homeScreenState.value.copy(
                        bottomNavigatorState = _homeScreenState.value.bottomNavigatorState.apply {
                            forEachIndexed { index, state ->
                                if (state.isSelected) {
                                    set(index, state.copy(isSelected = false))
                                }
                            }
                            set(0, first().copy(navName = "首页"))
                            set(4, get(4).copy(isSelected = true))
                        }
                    )
            }
        }
    }

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