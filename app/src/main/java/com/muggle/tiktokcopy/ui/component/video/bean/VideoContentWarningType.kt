package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/17 23:11
 * @author muggle
 * @desc
 */
sealed interface VideoContentWarningType {
    /**
     * 作者声明
     */
    class AuthorWarning(val authorWarningStr: String) : VideoContentWarningType

    /**
     * 内容警告
     */
    class ContentWarning(val contentWarningStr: String) : VideoContentWarningType
}