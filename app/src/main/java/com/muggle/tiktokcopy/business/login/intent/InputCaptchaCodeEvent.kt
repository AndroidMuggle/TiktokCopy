package com.muggle.tiktokcopy.business.login.intent

/**
 * @date 2025/12/7 15:03
 * @author muggle
 * @desc
 */
sealed interface InputCaptchaCodeEvent {
    /**
     * 点击返回按钮
     */
    object ClickBackBtn : InputCaptchaCodeEvent

    /**
     * 点击帮助按钮
     */
    object ClickHelpBtn : InputCaptchaCodeEvent

    /**
     * 输入验证码
     */
    class InputCaptchaCode(val code: String) : InputCaptchaCodeEvent

    /**
     * 点击确认按钮
     */
    object ClickConfirmBtn : InputCaptchaCodeEvent

    /**
     * 点击收不到验证码
     */
    object ClickCannotReceiveCode : InputCaptchaCodeEvent

    /**
     * 点击重新发送验证码
     */
    object ClickResendCode : InputCaptchaCodeEvent
}