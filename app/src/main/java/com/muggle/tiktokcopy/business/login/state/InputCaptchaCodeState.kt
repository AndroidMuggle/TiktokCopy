package com.muggle.tiktokcopy.business.login.state

import androidx.core.text.isDigitsOnly

/**
 * @date 2025/12/7 15:11
 * @author muggle
 * @desc
 */
data class InputCaptchaCodeState(
    val phoneNumber: String = "",
    val captchaCode: String = "",
) {
    /**
     * 确认按钮是否可以点击
     */
    val isConfirmBtnEnable: Boolean
        get() = phoneNumber.isDigitsOnly() && phoneNumber.length == 11
                && captchaCode.isDigitsOnly() && captchaCode.length == 4
}