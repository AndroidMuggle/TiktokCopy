package com.muggle.tiktokcopy.business.login.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.LoginByCaptchaCodeEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.LoginByCaptchaCodeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2025/12/7 13:32
 * @author muggle
 * @desc
 */
@HiltViewModel
class LoginByCaptchaCodeVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _loginByCaptchaCodeUiState = MutableStateFlow(LoginByCaptchaCodeUiState())
    val loginByCaptchaCodeUiState: StateFlow<LoginByCaptchaCodeUiState> = _loginByCaptchaCodeUiState

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
                _loginByCaptchaCodeUiState.update {
                    it.copy(isPrivacySelect = event.isSelect)
                }
            }

            is LoginByCaptchaCodeEvent.InputPhoneNumber -> {
                _loginByCaptchaCodeUiState.update {
                    it.copy(phoneNumber = event.phoneNumber)
                }
            }
        }
    }
}