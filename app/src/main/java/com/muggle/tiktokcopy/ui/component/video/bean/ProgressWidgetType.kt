package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/23 21:16
 * @author muggle
 * @desc
 */
sealed interface ProgressWidgetType {

    /**
     * 播放中
     */
    object Playing : ProgressWidgetType

    /**
     * 暂停
     */
    object Pause : ProgressWidgetType

    /**
     * 拖拽中
     */
    object Dragging : ProgressWidgetType

    /**
     *
     */
    object Hide : ProgressWidgetType
}