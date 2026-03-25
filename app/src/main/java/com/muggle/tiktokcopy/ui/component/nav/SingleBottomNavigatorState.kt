package com.muggle.tiktokcopy.ui.component.nav

import androidx.annotation.DrawableRes

/**
 * @date 2025/12/8 22:59
 * @author muggle
 * @desc 底部导航按钮状态
 */
data class SingleBottomNavigatorState(
    val navName: String = "",
    @DrawableRes val navIcon: Int = -1,
    val isSelected: Boolean = false,
    val newMessageCount: Int = 0,
    val avatarIcon: String = ""
)
