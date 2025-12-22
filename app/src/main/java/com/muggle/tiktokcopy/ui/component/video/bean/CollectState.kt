package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2025/12/17 23:41
 * @author muggle
 * @desc 收藏入口的状态
 */
sealed interface CollectState {
    /**
     * 未收藏状态
     */
    object Idle : CollectState

    /**
     * 收藏点击后的动画状态
     */
    object CollectChecking : CollectState

    /**
     * 已收藏
     */
    object Collected : CollectState
}