package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2025/12/16 23:15
 * @author muggle
 * @desc
 */
sealed interface LikeState {

    /**
     * 为点赞
     */
    object Unlike : LikeState

    /**
     * 已点赞
     */
    object Liked : LikeState

    /**
     * 点赞确认状态
     */
    object LikeChecking : LikeState
}