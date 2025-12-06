package com.muggle.tiktokcopy.business.login.intent

/**
 * 找回密码页面event
 */
sealed interface FindPasswordEvent {
    /**
     * 点击返回按钮
     */
    object ClickBackBtn : FindPasswordEvent

    /**
     * 点击隐私选择
     */
    class ClickPrivacyBtn(val isSelect: Boolean) : FindPasswordEvent

    /**
     * 点击重新发送验证码
     */
    object ClickResendBtn : FindPasswordEvent

    /**
     * 点击确认按钮
     */
    object ClickConfirmBtn : FindPasswordEvent

    /**
     * 输入验证码
     */
    class InputCaptchaCode(val code: String) : FindPasswordEvent
}
