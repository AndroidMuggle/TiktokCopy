package com.muggle.tiktokcopy.business.home.state

import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.ui.component.nav.NAVIGATOR_DEFAULT_LIST
import com.muggle.tiktokcopy.ui.component.nav.SingleBottomNavigatorState

/**
 * @date 2026/3/25 23:12
 * @author muggle
 * @desc
 */
data class BottomNavUiState(
    val bottomNavigatorState: List<SingleBottomNavigatorState> = NAVIGATOR_DEFAULT_LIST,
    val curHomePageVideoType: HomePageClickType = HomePageClickType.SingleVideo
)