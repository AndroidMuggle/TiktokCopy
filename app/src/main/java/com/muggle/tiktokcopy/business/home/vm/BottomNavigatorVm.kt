package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.business.home.intent.BottomNavigatorClickAct
import com.muggle.tiktokcopy.business.home.repo.BottomNavRepo
import com.muggle.tiktokcopy.business.home.state.BottomNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2026/3/25 23:11
 * @author muggle
 * @desc
 */
@HiltViewModel
class BottomNavigatorVm @Inject constructor(private val repo: BottomNavRepo) : ViewModel() {
    private val _bottomNavState = MutableStateFlow(BottomNavState())
    val bottomNavState: StateFlow<BottomNavState> = _bottomNavState

    fun onReceiveBottomNaviClickAct(bottomNaviClickAct: BottomNavigatorClickAct) {
        when (bottomNaviClickAct) {
            BottomNavigatorClickAct.ClickCreateVideoPage -> {
                // todo 点击事件处理
            }

            BottomNavigatorClickAct.ClickFriendPage -> {
                _bottomNavState.update {
                    it.copy(
                        bottomNavigatorState = _bottomNavState.value.bottomNavigatorState.apply {
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
            }

            is BottomNavigatorClickAct.ClickHomePage -> {
                _bottomNavState.update {
                    it.copy(
                        bottomNavigatorState = _bottomNavState.value.bottomNavigatorState.apply {
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
            }

            BottomNavigatorClickAct.ClickMessagePage -> {
                _bottomNavState.update {
                    it.copy(
                        bottomNavigatorState = _bottomNavState.value.bottomNavigatorState.apply {
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
            }

            BottomNavigatorClickAct.ClickMinePage -> {
                _bottomNavState.update {
                    it.copy(
                        bottomNavigatorState = _bottomNavState.value.bottomNavigatorState.apply {
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
    }

}