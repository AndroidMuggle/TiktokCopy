package com.muggle.tiktokcopy.business.login.state

import androidx.core.text.isDigitsOnly

/**
 * @date 2025/12/7 15:54
 * @author muggle
 * @desc 输入新密码的界面状态
 */
data class NewPasswordUiState(
    val newPassword: String = "",
    val phoneNumber: String = ""
) {
    /**
     * 确认按钮是否可以点击
     */
    val isConfirmEnable: Boolean
        get() = !newPassword.isDigitsOnly() && (newPassword.length in 8..20)
}