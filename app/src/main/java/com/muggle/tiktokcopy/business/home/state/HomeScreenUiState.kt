package com.muggle.tiktokcopy.business.home.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.muggle.tiktokcopy.ui.screen.home.getTabItemList

/**
 * @date 2026/3/13 0:57
 * @author muggle
 * @desc
 */
data class HomeScreenUiState(
    val moreMenuDotCount: Int = 0,
    val tabItemList: SnapshotStateList<SingleTabUiState> = getTabItemList(),
) {
}