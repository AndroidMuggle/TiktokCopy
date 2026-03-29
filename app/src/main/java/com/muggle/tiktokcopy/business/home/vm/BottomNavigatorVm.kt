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
                _bottomNavState.update { currentState ->
                    val newList = currentState.bottomNavigatorState.mapIndexed { index, state ->
                        when (index) {
                            0 -> state.copy(navName = "首页", isSelected = false)
                            1 -> state.copy(isSelected = true, newMessageCount = 0)
                            else -> state.copy(isSelected = false)
                        }
                    }
                    currentState.copy(bottomNavigatorState = newList)
                }
            }

            is BottomNavigatorClickAct.ClickHomePage -> {
                _bottomNavState.update { currentState ->
                    val isHomeSelected = currentState.bottomNavigatorState.firstOrNull()?.isSelected == true
                    val newList = currentState.bottomNavigatorState.mapIndexed { index, state ->
                        if (index == 0) {
                            if (isHomeSelected) {
                                when (bottomNaviClickAct.homePageType) {
                                    HomePageClickType.SingleVideo -> state.copy(
                                        navIcon = R.drawable.common_nav_switch,
                                        navName = "首页"
                                    )
                                    HomePageClickType.VideoList -> state.copy(
                                        navIcon = R.drawable.common_nav_left,
                                        navName = "返回"
                                    )
                                }
                            } else {
                                when (bottomNaviClickAct.homePageType) {
                                    HomePageClickType.SingleVideo -> state.copy(
                                        navIcon = R.drawable.common_nav_switch,
                                        navName = "首页",
                                        isSelected = true
                                    )
                                    HomePageClickType.VideoList -> state.copy(
                                        navIcon = R.drawable.common_nav_left,
                                        navName = "返回",
                                        isSelected = true
                                    )
                                }
                            }
                        } else {
                            state.copy(isSelected = false)
                        }
                    }
                    currentState.copy(
                        bottomNavigatorState = newList,
                        curHomePageVideoType = bottomNaviClickAct.homePageType
                    )
                }
            }

            BottomNavigatorClickAct.ClickMessagePage -> {
                _bottomNavState.update { currentState ->
                    val newList = currentState.bottomNavigatorState.mapIndexed { index, state ->
                        when (index) {
                            0 -> state.copy(navName = "首页", isSelected = false)
                            3 -> state.copy(isSelected = true)
                            else -> state.copy(isSelected = false)
                        }
                    }
                    currentState.copy(bottomNavigatorState = newList)
                }
            }

            BottomNavigatorClickAct.ClickMinePage -> {
                _bottomNavState.update { currentState ->
                    val newList = currentState.bottomNavigatorState.mapIndexed { index, state ->
                        when (index) {
                            0 -> state.copy(navName = "首页", isSelected = false)
                            4 -> state.copy(isSelected = true)
                            else -> state.copy(isSelected = false)
                        }
                    }
                    currentState.copy(bottomNavigatorState = newList)
                }
            }
        }
    }

}