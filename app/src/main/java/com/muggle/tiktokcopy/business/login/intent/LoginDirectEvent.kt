package com.muggle.tiktokcopy.business.login.intent

sealed interface LoginDirectEvent {
    /**
     * 点击返回按钮
     */
    object ClickBackBtn : LoginDirectEvent

    /**
     * 点击帮助按钮
     */
    object ClickHelpBtn : LoginDirectEvent

    /**
     * 点击隐私按钮
     */
    data class ClickPrivacyBtn(val isSelected: Boolean) : LoginDirectEvent

    /**
     * 点击确认按钮
     */
    object ClickConfirmBtn : LoginDirectEvent

    /**
     * 点击切换登录账号
     */
    object ClickChangeAccount : LoginDirectEvent

}