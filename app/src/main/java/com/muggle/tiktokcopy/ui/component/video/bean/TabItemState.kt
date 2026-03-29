package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/2/8 23:27
 * @author muggle
 * @desc 滑动TabItem的状态
 */
sealed interface TabItemState {
    /**
     * 普通态(todo 特殊活动的时候，实际切换tab到经验时，特殊活动tab栏的颜色会变)
     */
    object NormalTab : TabItemState

    /**
     * 刷新状态
     */
    object Refreshing : TabItemState

}