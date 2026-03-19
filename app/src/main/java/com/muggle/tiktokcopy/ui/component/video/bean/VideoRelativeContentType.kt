package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/8 0:01
 * @author muggle
 * @desc 视频相关内容（精选、定位、图文）
 */
sealed interface VideoRelativeContentType {
    /**
     * 抖音精选
     */
    object SpecialSelect : VideoRelativeContentType

    /**
     * 定位
     */
    class Location(
        val title: String,
        val locationName: String?,
        val subDescriptions: List<String>?
    ) : VideoRelativeContentType

    /**
     * 图文
     */
    class ImageWithDescription(
        val imgUrl: String,
        val title: String,
        val typeName: String?,
        val subDescriptions: List<String>?
    ) : VideoRelativeContentType
}