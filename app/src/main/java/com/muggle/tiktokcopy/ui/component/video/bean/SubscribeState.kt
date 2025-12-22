package com.muggle.tiktokcopy.ui.component.video.bean


/**
 * @date 2025/12/15 22:15
 * @author muggle
 * @desc
 */
sealed interface SubscribeState {
    /**
     * 未关注
     */
    object Unsubscribe : SubscribeState

    /**
     * 互相关注
     */
    object MutualFollowed : SubscribeState

    /**
     * 已关注
     */
    object Subscribed : SubscribeState

    /**
     * 关注确认
     */
    object SubscribeChecked : SubscribeState
}