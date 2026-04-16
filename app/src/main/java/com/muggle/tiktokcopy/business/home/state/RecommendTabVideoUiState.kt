package com.muggle.tiktokcopy.business.home.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import com.muggle.tiktokcopy.business.home.bean.VideoOrientationType
import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean
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

/**
 * @date 2026/4/2 0:13
 * @author muggle
 * @desc
 */
data class SingleVideoUiState(
    val videoUrl: String = "",
    val videoCoverUrl: String = "",
    val author: LoginResponseBean,
    val subscribeState: SubscribeState = SubscribeState.Unsubscribe,
    val likeCountStr: String = "0",
    val likeState: LikeState = LikeState.Unlike,
    val commentCountStr: String = "0",
    val isCommentBoardShowing: Boolean = false,
    val collectState: CollectState = CollectState.Idle,
    val shareCountStr: String = "0",
    val shareBoardShowing: Boolean = false,
    val musicAlbumState: VideoAlbumState = VideoAlbumState.AlbumImage,
    val isShowDanmakuEntrance: Boolean = false,
    // todo 弹幕库列表
    val recommendState: RecommendState? = null,
    val videoRelativeContentType: VideoRelativeContentType? = null,
    val authorWidgetType: AuthorWidgetType? = null,
    val videoContentDesc: String = "",
    val videoContentWarningType: VideoContentWarningType? = null,
    val videoBottomWidgetType: VideoBottomWidgetType? = null,
    val progressWidgetType: ProgressWidgetType = ProgressWidgetType.Hide,
    val playBackState: Int = Player.STATE_IDLE,
    val isPlaying: Boolean = false,
    val errMsg: String = "",
    val videoSize: VideoSize = VideoSize.UNKNOWN,
    val currentPositionMs: Long = 0L,
    val totalDurationMs: Long = 0L,
    val videoOrientationType: VideoOrientationType = VideoOrientationType.Landscape
)

data class RecommendTabVideoUiState(
    val videoUiStateList: SnapshotStateList<SingleVideoUiState> = mutableStateListOf()
)