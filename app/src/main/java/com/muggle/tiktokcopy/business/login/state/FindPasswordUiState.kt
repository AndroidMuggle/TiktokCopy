package com.muggle.tiktokcopy.business.login.state

import androidx.core.text.isDigitsOnly

data class FindPasswordUiState(
    val phoneNumber: String = "",
    val captchaCode: String = "",
    val isPrivacySelect: Boolean = false
) {

    /**
     * 确认按钮状态
     */
    val isConfirmBtnEnable: Boolean
        get() = captchaCode.isDigitsOnly() && captchaCode.length == 4 && isPrivacySelect
}