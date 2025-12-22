package com.muggle.tiktokcopy.ui.component.video.bean

/**
 * @date 2025/12/22 23:29
 * @author muggle
 * @desc
 */
sealed interface VideoAlbumState {
    /**
     * 听合集
     */
    object ListenVideoCollection : VideoAlbumState

    /**
     * 拍同款
     */
    object FilmSameVideo : VideoAlbumState

    /**
     * 听视频
     */
    object ListenVideo : VideoAlbumState

    /**
     * 视频封面
     */
    object AlbumImage : VideoAlbumState
}