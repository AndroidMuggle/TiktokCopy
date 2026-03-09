package com.muggle.tiktokcopy.business.home.intent

import com.muggle.tiktokcopy.business.home.bean.AuthorWidgetClickType
import com.muggle.tiktokcopy.business.home.bean.BottomWidgetClickType
import com.muggle.tiktokcopy.business.home.bean.MusicAlbumEntranceClickType
import com.muggle.tiktokcopy.business.home.bean.RecommendEntranceClickType
import com.muggle.tiktokcopy.business.home.bean.VideoAuthorAvatarClickType
import com.muggle.tiktokcopy.business.home.bean.VideoContentWarningWidgetClickType
import com.muggle.tiktokcopy.business.home.bean.VideoRelativeWidgetClickType

/**
 * @author Muggle
 * @date 2026/3/5 0:52
 * @desc
 **/
sealed interface VideoWidgetClickAct {
    /**
     * 点击作者图像
     */
    class ClickAuthorAvatar(
        val videoAuthorAvatarClickType: VideoAuthorAvatarClickType
    ) : VideoWidgetClickAct

    /**
     * 点击喜欢按钮
     */
    class ClickLikeWidget(val isCurLike: Boolean) : VideoWidgetClickAct

    /**
     * 点击视频评论
     */
    object ClickVideoCommentEntrance : VideoWidgetClickAct

    /**
     * 点击添加收藏按钮
     */
    class ClickAddToClickWidget(val isCurCollect: Boolean) : VideoWidgetClickAct

    /**
     * 点击分享按钮
     */
    object ClickShareWidget : VideoWidgetClickAct

    /**
     * 点击音乐入口点击
     */
    class ClickMusicAlbumEntrance(
        val musicEntranceClickType: MusicAlbumEntranceClickType
    ) : VideoWidgetClickAct

    /**
     * 点击弹幕入口
     */
    object ClickDanmakuEditEntrance : VideoWidgetClickAct

    /**
     * 点击推荐入口
     */
    class ClickRecommendEntrance(
        val recommendEntranceClickType: RecommendEntranceClickType
    ) : VideoWidgetClickAct

    /**
     * 点击视频相关组件（定位、精选或icon图文）
     */
    class ClickVideoRelativeWidget(
        val videoRelativeWidgetClickType: VideoRelativeWidgetClickType
    ) : VideoWidgetClickAct

    /**
     * 点击作者名称
     */
    object ClickAuthorName : VideoWidgetClickAct

    /**
     * 点击作者名后面的小插件
     */
    class ClickAuthorWidget(
        val authorWidgetClickType: AuthorWidgetClickType
    ) : VideoWidgetClickAct

    /**
     * 点击共创作者头像 todo: 作者信息
     */
    class ClickCreateTogetherAuthorAvatar() : VideoWidgetClickAct

    /**
     * 点击共创作者列表后面的更多按钮
     */
    object ClickCreateTogetherMore : VideoWidgetClickAct

    /**
     * 点击视频底部插件
     */
    class ClickVideoBottomWidget(
        val bottomWidgetClickType: BottomWidgetClickType
    ) : VideoWidgetClickAct

    /**
     * 点击视频内容声明
     */
    class ClickVideoContentWarningWidget(
        val contentWarningWidgetClickType: VideoContentWarningWidgetClickType
    ) : VideoWidgetClickAct

    /**
     * 长按视频进度条插件区域
     */
    class LongClickVideoProgressWidget(val progress: Long) : VideoWidgetClickAct
}