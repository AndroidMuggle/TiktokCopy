package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/9 18:44
 * @desc 视频内容提示点击类型
 **/
sealed interface VideoContentWarningWidgetClickType {
    /**
     * 作者声明
     */
    object AuthorWarning : VideoContentWarningWidgetClickType

    /**
     * 内容声明
     */
    object ContentWarning : VideoContentWarningWidgetClickType
}