package com.muggle.tiktokcopy.business.home.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorWidgetType
import com.muggle.tiktokcopy.ui.component.video.bean.CollectState
import com.muggle.tiktokcopy.ui.component.video.bean.LikeState
import com.muggle.tiktokcopy.ui.component.video.bean.ProgressWidgetType
import com.muggle.tiktokcopy.ui.component.video.bean.RecommendState
import com.muggle.tiktokcopy.ui.component.video.bean.SubscribeState
import com.muggle.tiktokcopy.ui.component.video.bean.VideoAlbumState
import com.muggle.tiktokcopy.ui.component.video.bean.VideoBottomWidgetType
import com.muggle.tiktokcopy.ui.component.video.bean.VideoContentWarningType
import com.muggle.tiktokcopy.ui.component.video.bean.VideoRelativeContentType
import com.muggle.tiktokcopy.ui.screen.getTabItemList

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