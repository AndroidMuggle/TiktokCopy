package com.muggle.tiktokcopy.business.login.intent

sealed interface LoginByPasswordEvent {
    /**
     * 清除电话号码
     */
    object ClearPhoneNumber : LoginByPasswordEvent

    /**
     * 清除密码
     */
    object ClearPassword : LoginByPasswordEvent

    /**
     * 点击修改密码显隐
     */
    class ClickChangePasswordVisibility(val isPasswordVisible: Boolean) : LoginByPasswordEvent

    /**
     * 点击返回按钮
     */
    object ClickBackBtn : LoginByPasswordEvent

    /**
     * 点击帮助按钮
     */
    object ClickHelpBtn : LoginByPasswordEvent

    /**
     * 输入手机号码
     */
    class InputPhoneNumber(val phoneNumber: String) : LoginByPasswordEvent

    /**
     * 输入密码
     */
    class InputPassword(val password: String) : LoginByPasswordEvent

    /**
     * 点击验证码登录
     */
    object ClickCaptchaLogin : LoginByPasswordEvent

    /**
     * 点击忘记密码
     */
    object ClickForgetPassword : LoginByPasswordEvent

    /**
     * 点击确认按钮
     */
    object ClickConfirmBtn : LoginByPasswordEvent

    /**
     * 点击隐私确认
     */
    class ClickConfirmPrivacy(val isSelected: Boolean) : LoginByPasswordEvent

    /**
     * 点击切换区号
     */
    object ClickRegionCode : LoginByPasswordEvent
}