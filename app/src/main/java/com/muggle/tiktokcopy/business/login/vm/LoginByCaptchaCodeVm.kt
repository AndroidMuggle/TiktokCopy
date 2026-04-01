package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.LoginByCaptchaCodeEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.LoginByCaptchaCodeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2025/12/7 13:32
 * @author muggle
 * @desc
 */
@HiltViewModel
class LoginByCaptchaCodeVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _loginByCaptchaCodeUiState = mutableStateOf(LoginByCaptchaCodeUiState())
    val loginByCaptchaCodeUiState: State<LoginByCaptchaCodeUiState> = _loginByCaptchaCodeUiState

    fun onReceiveEvent(event: LoginByCaptchaCodeEvent) {
        when (event) {
            LoginByCaptchaCodeEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            LoginByCaptchaCodeEvent.ClickChangeRegionCode -> {
                // TODO: 点击切换区号
            }

            LoginByCaptchaCodeEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮
            }

            LoginByCaptchaCodeEvent.ClickHelpBtn -> {
                // TODO: 点击帮助按钮
            }

            LoginByCaptchaCodeEvent.ClickLoginByPassword -> {
                // TODO: 切换至密码登录
            }

            is LoginByCaptchaCodeEvent.ClickPrivacySelect -> {
                _loginByCaptchaCodeUiState.value =
                    _loginByCaptchaCodeUiState.value.copy(isPrivacySelect = event.isSelect)
            }

            is LoginByCaptchaCodeEvent.InputPhoneNumber -> {
                _loginByCaptchaCodeUiState.value =
                    _loginByCaptchaCodeUiState.value.copy(phoneNumber = event.phoneNumber)
            }
        }
    }
}