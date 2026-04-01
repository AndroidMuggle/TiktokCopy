package com.muggle.tiktokcopy.business.login.state

import androidx.core.text.isDigitsOnly

/**
 * @date 2025/12/7 13:21
 * @author muggle
 * @desc 验证码手机号输入页面状态
 */
data class LoginByCaptchaCodeUiState(
    val phoneNumber: String = "",
    val regionCode: String = "+86",
    val isPrivacySelect: Boolean = false,
) {
    /**
     * 确认按钮状态
     */
    val isConfirmBtnEnable: Boolean
        get() = phoneNumber.isDigitsOnly() && phoneNumber.length == 11 && isPrivacySelect
}