package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/7 20:10
 * @desc 视频相关内容点击类型
 **/
sealed interface VideoRelativeWidgetClickType {
    /**
     * 位置定位
     */
    object Location : VideoRelativeWidgetClickType

    /**
     * 图文描述
     */
    object ImageWithDescription : VideoRelativeWidgetClickType

    /**
     * 抖音精选
     */
    object SpecialSelect : VideoRelativeWidgetClickType
}