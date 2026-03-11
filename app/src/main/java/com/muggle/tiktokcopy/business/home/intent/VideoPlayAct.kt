package com.muggle.tiktokcopy.business.home.intent

/**
 * @date 2026/3/11 0:37
 * @author muggle
 * @desc 视频播放相关
 */
sealed interface VideoPlayAct {

    /**
     * 播放视频
     */
    object PlayVideo : VideoPlayAct

    /**
     * 暂停视频
     */
    object PauseVideo : VideoPlayAct

    /**
     * 切换横竖屏
     */
    class SwitchOrientation(val isFullScreen: Boolean) : VideoPlayAct

    /**
     * 修改播放速度
     */
    class ChangePlaySpeed(val speed: Float) : VideoPlayAct

    /**
     * 修改视频音量
     */
    class ChangeVolumeValue(val volume: Int) : VideoPlayAct

    /**
     * 修改屏幕亮度
     */
    class ChangeScreenIntensity(val intensity: Int) : VideoPlayAct

    /**
     * 修改控制层可见性
     */
    class ChangeControlLayerVisibility(val isVisible: Boolean) : VideoPlayAct

    /**
     * 点击横屏倍速文案
     */
    object ClickSpeedText : VideoPlayAct

    /**
     * 点击更多
     */
    object ClickMore : VideoPlayAct

    /**
     * 点击视频投屏
     */
    object ClickTvMirror : VideoPlayAct

    /**
     * 点击弹幕开关
     */
    class ClickDanmakuSwitch(val isOpenDanmaku: Boolean) : VideoPlayAct

    /**
     * 点击不感兴趣
     */
    object ClickUninterest : VideoPlayAct

    /**
     * 点击举报
     */
    object ClickFeedback : VideoPlayAct

    /**
     * 点击稍后再看
     */
    object ClickWatchLater : VideoPlayAct

    /**
     * 点击缓存视频
     */
    object ClickCacheVideo : VideoPlayAct

    /**
     * 点击在其他设备打开视频
     */
    object ClickOpenOnOtherDevice : VideoPlayAct

    /**
     * 点击视频关闭计时器
     */
    object ClickCloseTimer : VideoPlayAct

    /**
     * 点击自动连播
     */
    object ClickAutoPlayNext : VideoPlayAct

    /**
     * 点击重新播放方式
     */
    object ClickReplayOnFinish : VideoPlayAct

    /**
     * 点击播完暂停方式
     */
    object ClickPauseWhenFinish : VideoPlayAct

    /**
     * 点击弹幕设置
     */
    object ClickDanmakuSetting : VideoPlayAct

    /**
     * 点击后台音频设置
     */
    object ClickBackendAudioSetting : VideoPlayAct

    /**
     * 点击后台小窗设置
     */
    object ClickBackendWindowSetting : VideoPlayAct

    /**
     * 点击自动旋转
     */
    class ClickAutoRotate(val isAutoRotate: Boolean) : VideoPlayAct
}