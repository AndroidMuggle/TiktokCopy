package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/18 0:42
 * @author muggle
 * @desc
 */
sealed interface VideoBottomWidgetType {

    /**
     * 汽水听音乐
     */
    class ListenMusic(val musicName: String, val musicAuthor: String) : VideoBottomWidgetType

    /**
     * 相关搜索
     */
    class RelativeSearch(val searchHintStr: String) : VideoBottomWidgetType

    /**
     * 视频合集
     */
    class VideoCollection(val collectionName: String) : VideoBottomWidgetType
}