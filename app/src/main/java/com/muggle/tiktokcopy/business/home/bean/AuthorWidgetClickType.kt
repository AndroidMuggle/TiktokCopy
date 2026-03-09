package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/7 20:46
 * @desc 作者名字后面的插件点击类型
 **/
sealed interface AuthorWidgetClickType {
    /**
     * 视频共创
     */
    object CreateTogether : AuthorWidgetClickType

    /**
     * 视频章节详情列表
     */
    object VideoChapterDetailList : AuthorWidgetClickType

    /**
     * 下一章
     */
    object VideoNextChapter : AuthorWidgetClickType
}