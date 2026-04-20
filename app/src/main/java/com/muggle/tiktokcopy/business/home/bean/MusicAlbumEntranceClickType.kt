package com.muggle.tiktokcopy.business.home.bean

/**
 * @author Muggle
 * @date 2026/3/6 0:23
 * @desc 音乐专辑入口点击事件类型
 **/
sealed interface MusicAlbumEntranceClickType {
    /**
     * 听抖音
     */
    object ListenVideo : MusicAlbumEntranceClickType

    /**
     * 音乐详情
     */
    object MusicDetail : MusicAlbumEntranceClickType

    /**
     * 拍同款
     */
    object CreateSameVideo : MusicAlbumEntranceClickType

    /**
     * 听合集
     */
    object ListVideoCollection: MusicAlbumEntranceClickType
}