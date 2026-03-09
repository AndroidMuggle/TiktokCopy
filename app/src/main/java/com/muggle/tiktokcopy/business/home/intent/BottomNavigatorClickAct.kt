package com.muggle.tiktokcopy.business.home.intent

import com.muggle.tiktokcopy.business.home.bean.HomePageClickType

/**
 * @author Muggle
 * @date 2026/3/1 23:35
 * @desc 底部导航栏点击事件
 **/
sealed interface BottomNavigatorClickAct {
    /**
     * 底部导航首页点击
     */
    class ClickHomePage(val homePageType: HomePageClickType) : BottomNavigatorClickAct

    /**
     * 底部导航朋友点击
     */
    object ClickFriendPage : BottomNavigatorClickAct

    /**
     * 底部导航创建视频点击
     */
    object ClickCreateVideoPage : BottomNavigatorClickAct

    /**
     * 底部导航消息页点击
     */
    object ClickMessagePage : BottomNavigatorClickAct

    /**
     * 底部导航我的页面点击
     */
    object ClickMinePage : BottomNavigatorClickAct
}