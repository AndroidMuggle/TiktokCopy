package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muggle.tiktokcopy.business.login.bean.LoginRequestBean
import com.muggle.tiktokcopy.business.login.intent.LoginByPasswordEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.LoginByPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginByPasswordVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private var _loginByPasswordUiState = mutableStateOf<LoginByPasswordUiState>(LoginByPasswordUiState())
    val loginByPasswordUiState: State<LoginByPasswordUiState> = _loginByPasswordUiState


    fun onReceiveEvent(event: LoginByPasswordEvent) {
        when (event) {
            is LoginByPasswordEvent.ClearPhoneNumber -> {
                _loginByPasswordUiState.value = _loginByPasswordUiState.value.copy(curPhoneNumber = "")
            }

            is LoginByPasswordEvent.InputPhoneNumber -> {
                _loginByPasswordUiState.value =
                    _loginByPasswordUiState.value.copy(curPhoneNumber = event.phoneNumber)
            }

            is LoginByPasswordEvent.ClearPassword -> {
                _loginByPasswordUiState.value = _loginByPasswordUiState.value.copy(curPassword = "")
            }

            is LoginByPasswordEvent.InputPassword -> {
                _loginByPasswordUiState.value =
                    _loginByPasswordUiState.value.copy(curPassword = event.password)
            }

            is LoginByPasswordEvent.ClickChangePasswordVisibility -> {
                _loginByPasswordUiState.value =
                    _loginByPasswordUiState.value.copy(isPasswordVisible = event.isPasswordVisible)
            }

            is LoginByPasswordEvent.ClickCaptchaLogin -> {
                // TODO: 跳转验证码登录页面
            }

            is LoginByPasswordEvent.ClickForgetPassword -> {
                // TODO: 跳转忘记密码页面
            }

            is LoginByPasswordEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮，调用登录接口
                viewModelScope.launch {
                    val response = repo.loginByPassword(
                        LoginRequestBean(
                            avatar = "",
                            captchaCode = "",
                            password = _loginByPasswordUiState.value.curPassword,
                            phoneNumber = _loginByPasswordUiState.value.curPhoneNumber,
                            username = ""
                        )
                    )
                }
            }

            is LoginByPasswordEvent.ClickConfirmPrivacy -> {
                _loginByPasswordUiState.value =
                    _loginByPasswordUiState.value.copy(isPrivacySelected = event.isSelected)
            }

            is LoginByPasswordEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            is LoginByPasswordEvent.ClickHelpBtn -> {
                // TODO: 点击帮助按钮
            }

            is LoginByPasswordEvent.ClickRegionCode -> {
                // TODO: 点击切换区域编码
            }
        }
    }


    companion object {
    }
}