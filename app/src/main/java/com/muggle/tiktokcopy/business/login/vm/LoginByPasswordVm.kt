package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muggle.tiktokcopy.business.login.bean.LoginRequestBean
import com.muggle.tiktokcopy.business.login.intent.LoginByPasswordEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.LoginByPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginByPasswordVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private var _loginByPasswordState = mutableStateOf<LoginByPasswordState>(LoginByPasswordState())
    val loginByPasswordState: State<LoginByPasswordState> = _loginByPasswordState


    fun onReceiveEvent(event: LoginByPasswordEvent) {
        when (event) {
            is LoginByPasswordEvent.ClearPhoneNumber -> {
                _loginByPasswordState.value = _loginByPasswordState.value.copy(curPhoneNumber = "")
            }

            is LoginByPasswordEvent.InputPhoneNumber -> {
                _loginByPasswordState.value =
                    _loginByPasswordState.value.copy(curPhoneNumber = event.phoneNumber)
            }

            is LoginByPasswordEvent.ClearPassword -> {
                _loginByPasswordState.value = _loginByPasswordState.value.copy(curPassword = "")
            }

            is LoginByPasswordEvent.InputPassword -> {
                _loginByPasswordState.value =
                    _loginByPasswordState.value.copy(curPassword = event.password)
            }

            is LoginByPasswordEvent.ClickChangePasswordVisibility -> {
                _loginByPasswordState.value =
                    _loginByPasswordState.value.copy(isPasswordVisible = event.isPasswordVisible)
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
                    repo.loginByPassword(
                        LoginRequestBean(
                            avatar = "",
                            captchaCode = "",
                            password = _loginByPasswordState.value.curPassword,
                            phoneNumber = _loginByPasswordState.value.curPhoneNumber,
                            username = ""
                        )
                    )
                }
            }

            is LoginByPasswordEvent.ClickConfirmPrivacy -> {
                _loginByPasswordState.value =
                    _loginByPasswordState.value.copy(isPrivacySelected = event.isSelected)
            }

            is LoginByPasswordEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            is LoginByPasswordEvent.ClickHelpBtn -> {
                // TODO: 点击帮助按钮
            }
        }
    }


    companion object {
    }
}