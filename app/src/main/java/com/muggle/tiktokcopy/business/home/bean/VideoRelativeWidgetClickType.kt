package com.muggle.tiktokcopy.business.home.bean

import com.muggle.tiktokcopy.ui.component.video.bean.VideoRelativeContentType

/**
 * @author Muggle
 * @date 2026/3/7 20:10
 * @desc 视频相关内容点击类型
 **/
sealed interface VideoRelativeWidgetClickType {
    /**
     * 位置定位
     */
    class Location(val videoRelativeContentType: VideoRelativeContentType) : VideoRelativeWidgetClickType

    /**
     * 图文描述
     */
    class ImageWithDescription(val videoRelativeContentType: VideoRelativeContentType) : VideoRelativeWidgetClickType

    /**
     * 抖音精选
     */
    object SpecialSelect : VideoRelativeWidgetClickType
}