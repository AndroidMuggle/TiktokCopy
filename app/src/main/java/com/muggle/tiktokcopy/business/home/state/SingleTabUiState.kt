package com.muggle.tiktokcopy.business.home.state

import com.muggle.tiktokcopy.ui.component.video.bean.TabItemState

/**
 * @date 2026/3/27 1:04
 * @author muggle
 * @desc
 */
data class SingleTabUiState(
    val tabName: String,
    val hasRedDot: Boolean,
    val message: String,
    val isSpecialActivity: Boolean,
    val isSelected: Boolean,
    val tabItemState: TabItemState
)