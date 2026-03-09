package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/5 0:38
 * @desc 首页点击事件类型
 **/
sealed interface HomePageClickType {

    /**
     * 单个视频展示
     */
    object SingleVideo : HomePageClickType

    /**
     * 视频列表
     */
    object VideoList : HomePageClickType
}