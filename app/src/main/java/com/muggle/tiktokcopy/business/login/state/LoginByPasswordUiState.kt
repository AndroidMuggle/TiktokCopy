package com.muggle.tiktokcopy.business.login.state

import androidx.core.text.isDigitsOnly

/**
 * 密码登录页面状态
 */
data class LoginByPasswordUiState(
    val curPhoneNumber: String = "",
    val curPassword: String = "",
    val isPrivacySelected: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val regionCode: String = "+86"
) {
    /**
     * 确认按钮状态
     */
    val isConfirmEnable: Boolean
        get() = (curPhoneNumber.length == PHONE_NUMBER_LENGTH && curPhoneNumber.isDigitsOnly())
                && (!curPassword.isDigitsOnly() && curPassword.length >= PASSWORD_LENGTH_AT_LEAST)
                && isPrivacySelected


    companion object {
        private const val TAG = "LoginByPasswordState"

        private const val PHONE_NUMBER_LENGTH = 11

        private const val PASSWORD_LENGTH_AT_LEAST = 8
    }
}