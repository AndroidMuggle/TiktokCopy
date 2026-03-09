package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/9 17:49
 * @desc 底部视频插件点击类型
 **/
sealed interface BottomWidgetClickType {
    /**
     * 听音乐
     */
    object ListenMusic : BottomWidgetClickType

    /**
     * 相关搜索
     */
    object RelativeSearch : BottomWidgetClickType

    /**
     * 进入视频合集
     */
    object VideoCollection : BottomWidgetClickType

    /**
     * 集合下一个视频
     */
    object CollectionNextVideo : BottomWidgetClickType
}