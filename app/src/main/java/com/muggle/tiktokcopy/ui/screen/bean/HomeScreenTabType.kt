package com.muggle.tiktokcopy.ui.screen.bean

/**
 * @author Muggle
 * @date 2026/2/16 21:21
 * @desc
 **/
sealed interface HomeScreenTabType {
    /**
     * 团购
     */
    object GroupCouponTab : HomeScreenTabType

    /**
     * 本地
     */
    object LocalVideoTab : HomeScreenTabType

    /**
     * 经验
     */
    object ExperienceTab : HomeScreenTabType

    /**
     * 精选
     */
    object SpecialSelectTab : HomeScreenTabType

    /**
     * 特殊活动
     */
    object SpecialActivityTab : HomeScreenTabType

    /**
     * 直播
     */
    object LiveStreamTab : HomeScreenTabType

    /**
     * 热点
     */
    object HotNewsTab : HomeScreenTabType

    /**
     * 关注
     */
    object SubscribedTab : HomeScreenTabType

    /**
     * 商城
     */
    object ShopMarketTab : HomeScreenTabType

    /**
     * 推荐
     */
    object RecommendTab : HomeScreenTabType
}