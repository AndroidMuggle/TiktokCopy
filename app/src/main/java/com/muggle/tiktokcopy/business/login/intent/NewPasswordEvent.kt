package com.muggle.tiktokcopy.business.login.intent

/**
 * @date 2025/12/7 15:46
 * @author muggle
 * @desc 新密码对应的事件
 */
sealed interface NewPasswordEvent {
    /**
     * 点击返回按钮
     */
    object ClickBackBtn : NewPasswordEvent

    /**
     * 输入新密码
     */
    class InputPassword(val password: String) : NewPasswordEvent

    /**
     * 点击确认按钮
     */
    object ClickConfirmBtn : NewPasswordEvent
}