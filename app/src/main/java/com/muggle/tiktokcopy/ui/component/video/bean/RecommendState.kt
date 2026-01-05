package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2026/1/4 23:10
 * @author muggle
 * @desc
 */
sealed interface RecommendState {

    /**
     * 推荐人数
     */
    class RecommendCount(val count: Int) : RecommendState

    /**
     * 用户推荐
     */
    class OtherUserRecommend(val userName: String, val userAvatar: String) : RecommendState

    /**
     * 自己推荐
     */
    class SelfRecommend(val userName: String, val userAvatar: String) : RecommendState
}