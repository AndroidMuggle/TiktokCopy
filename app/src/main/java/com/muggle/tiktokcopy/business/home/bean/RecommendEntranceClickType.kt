package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/7 19:52
 * @desc 推荐入口点击事件
 **/
sealed interface RecommendEntranceClickType {
    /**
     * 推荐人数
     */
    object RecommendCount : RecommendEntranceClickType

    /**
     * 点击推荐给朋友
     */
    class SelfRecommend(val selfRecommendClickType: SelfRecommendClickType) : RecommendEntranceClickType

    /**
     * 其他人点击推荐
     */
    object OtherRecommend : RecommendEntranceClickType
}