package com.muggle.tiktokcopy.ui.screen

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
     *
     */
    object LocalVideoTab : HomeScreenTabType

    /**
     *
     */
    object ExperienceTab : HomeScreenTabType

    /**
     *
     */
    object SpecialSelectTab : HomeScreenTabType

    /**
     *
     */
    object SpecialActivityTab : HomeScreenTabType

    /**
     *
     */
    object LiveStreamTab : HomeScreenTabType

    /**
     *
     */
    object HotNewsTab : HomeScreenTabType

    /**
     *
     */
    object SubscribedTab : HomeScreenTabType

    /**
     *
     */
    object ShopMarketTab : HomeScreenTabType

    /**
     *
     */
    object RecommendTab : HomeScreenTabType
}