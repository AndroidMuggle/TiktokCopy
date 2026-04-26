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
    val author: LoginResponseBean? = null,
    val subscribeState: SubscribeState = SubscribeState.Unsubscribe,
    val likeCountStr: String = "0",
    val likeState: LikeState = LikeState.Unlike,
    val commentCountStr: String = "0",
    val isCommentBoardShowing: Boolean = false,
    val collectCountStr: String = "",
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
    val videoUiStateList: SnapshotStateList<SingleVideoUiState> = mutableStateListOf<SingleVideoUiState>().apply {
        add(SingleVideoUiState(videoUrl = "https://vdept3.bdstatic.com/mda-sd1s1rn16s4r67xd/cae_h264/1775154451958807049/mda-sd1s1rn16s4r67xd.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1777233439-0-0-62ea64b41446927b86e6da99ba10be27&bcevod_channel=searchbox_feed&cr=0&cd=0&pd=1&pt=3&logid=3439709830&vid=132199266457344010&klogid=3439709830&abtest="))
        add(SingleVideoUiState(videoUrl = "https://vdept3.bdstatic.com/mda-sd1s1rn16s4r67xd/cae_h264/1775154451958807049/mda-sd1s1rn16s4r67xd.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1777233439-0-0-62ea64b41446927b86e6da99ba10be27&bcevod_channel=searchbox_feed&cr=0&cd=0&pd=1&pt=3&logid=3439709830&vid=132199266457344010&klogid=3439709830&abtest="))
        add(SingleVideoUiState(videoUrl = "https://vdept3.bdstatic.com/mda-sd1s1rn16s4r67xd/cae_h264/1775154451958807049/mda-sd1s1rn16s4r67xd.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1777233439-0-0-62ea64b41446927b86e6da99ba10be27&bcevod_channel=searchbox_feed&cr=0&cd=0&pd=1&pt=3&logid=3439709830&vid=132199266457344010&klogid=3439709830&abtest="))
        add(SingleVideoUiState(videoUrl = "https://vdept3.bdstatic.com/mda-sd1s1rn16s4r67xd/cae_h264/1775154451958807049/mda-sd1s1rn16s4r67xd.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1777233439-0-0-62ea64b41446927b86e6da99ba10be27&bcevod_channel=searchbox_feed&cr=0&cd=0&pd=1&pt=3&logid=3439709830&vid=132199266457344010&klogid=3439709830&abtest="))
        add(SingleVideoUiState(videoUrl = "https://vdept3.bdstatic.com/mda-sd1s1rn16s4r67xd/cae_h264/1775154451958807049/mda-sd1s1rn16s4r67xd.mp4?v_from_s=hkapp-haokan-nanjing&auth_key=1777233439-0-0-62ea64b41446927b86e6da99ba10be27&bcevod_channel=searchbox_feed&cr=0&cd=0&pd=1&pt=3&logid=3439709830&vid=132199266457344010&klogid=3439709830&abtest="))
    },
    val selectIndex: Int = 0
)