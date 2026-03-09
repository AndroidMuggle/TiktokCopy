package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/5 1:01
 * @desc 视频作者头像点击事件
 **/
sealed interface VideoAuthorAvatarClickType {
    /**
     * 关注
     */
    object Subscribe : VideoAuthorAvatarClickType

    /**
     * 发送私信
     */
    object SendMessage : VideoAuthorAvatarClickType

    /**
     * 作者详情页
     */
    object AuthorDetail : VideoAuthorAvatarClickType
}