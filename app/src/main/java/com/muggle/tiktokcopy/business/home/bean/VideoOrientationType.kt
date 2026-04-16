package com.muggle.tiktokcopy.business.home.bean

/**
 * @date 2026/4/9 0:53
 * @author muggle
 * @desc
 */
sealed interface VideoOrientationType {

    /**
     * 横屏
     */
    object Landscape : VideoOrientationType

    /**
     * 竖屏
     */
    object Portrait : VideoOrientationType
}