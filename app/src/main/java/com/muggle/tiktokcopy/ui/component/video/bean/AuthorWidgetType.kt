package com.muggle.tiktokcopy.ui.component.video.bean

import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean

/**
 * @date 2026/1/17 21:40
 * @author muggle
 * @desc 作者名称后面的组件
 */
sealed interface AuthorWidgetType {

    /**
     * 视频创作日期
     */
    class VideoCreateDate(val timeStamp: String) : AuthorWidgetType

    /**
     * 作者认证
     */
    class AuthorVerification(val verificationType: AuthorVerificationType) : AuthorWidgetType

    /**
     * 作者共创
     */
    class CreateTogether(val authorList: List<LoginResponseBean>) : AuthorWidgetType

    /**
     * 视频章节
     */
    object VideoChapter : AuthorWidgetType

    /**
     * 动态图
     */
    object LivePhoto : AuthorWidgetType

    /**
     * 文章
     */
    object Article : AuthorWidgetType
}