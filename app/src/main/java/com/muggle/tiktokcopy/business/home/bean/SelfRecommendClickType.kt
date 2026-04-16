package com.muggle.tiktokcopy.business.home.bean

/**
 * @date 2026/4/15 23:59
 * @author muggle
 * @desc
 */
sealed interface SelfRecommendClickType {

    /**
     * 点击用户头像区域
     */
    object UserAvatarBoard : SelfRecommendClickType

    /**
     * 点击编辑入口
     */
    object EditEntrance : SelfRecommendClickType
}