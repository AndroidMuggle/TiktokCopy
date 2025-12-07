package com.muggle.tiktokcopy.business.login.intent

/**
 * @date
 * @author muggle
 * @desc 登录验证码事件
 */
sealed interface LoginByCaptchaCodeEvent {
    /**
     * 点击返回按钮
     */
    object ClickBackBtn : LoginByCaptchaCodeEvent

    /**
     * 点击帮助按钮
     */
    object ClickHelpBtn : LoginByCaptchaCodeEvent

    /**
     * 点击切换区号
     */
    object ClickChangeRegionCode : LoginByCaptchaCodeEvent

    /**
     * 点击密码登录
     */
    object ClickLoginByPassword : LoginByCaptchaCodeEvent

    /**
     * 输入电话号码
     */
    class InputPhoneNumber(val phoneNumber: String) : LoginByCaptchaCodeEvent

    /**
     * 点击确认阿牛
     */
    object ClickConfirmBtn : LoginByCaptchaCodeEvent

    /**
     * 点击隐私选择
     */
    class ClickPrivacySelect(val isSelect: Boolean) : LoginByCaptchaCodeEvent
}