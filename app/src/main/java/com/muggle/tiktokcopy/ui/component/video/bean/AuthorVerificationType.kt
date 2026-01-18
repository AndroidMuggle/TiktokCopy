package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/17 22:06
 * @author muggle
 * @desc
 */
sealed interface AuthorVerificationType {

    /**
     * 政府账号
     */
    object Government : AuthorVerificationType

    /**
     * 个人实名认证
     */
    object CompanyVerified : AuthorVerificationType

    /**
     * 名人认证
     */
    object FamousPersonVerified : AuthorVerificationType
}