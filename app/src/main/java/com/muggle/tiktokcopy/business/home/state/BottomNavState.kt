package com.muggle.tiktokcopy.business.home.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.ui.component.nav.SingleBottomNavigatorState
import com.muggle.tiktokcopy.ui.component.nav.NAVIGATOR_DEFAULT_LIST

/**
 * @date 2026/3/25 23:12
 * @author muggle
 * @desc
 */
data class BottomNavState(
    val bottomNavigatorState: SnapshotStateList<SingleBottomNavigatorState> = NAVIGATOR_DEFAULT_LIST,
    val curHomePageVideoType: HomePageClickType = HomePageClickType.SingleVideo
)