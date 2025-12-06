package com.muggle.tiktokcopy.business.login.state

data class DirectLoginState(
    val phoneNumber: String = "18214839999",
    val userAvatar: String = "",
    val userName: String = "tiktok",
    val isPrivacySelected: Boolean = false
) {

    val isConfirmBtnEnable: Boolean
        get() = userName.isNotEmpty() && isPrivacySelected && phoneNumber.isNotEmpty()

}