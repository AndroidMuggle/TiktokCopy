package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.InputCaptchaCodeEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.InputCaptchaCodeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2025/12/7 15:14
 * @author muggle
 * @desc
 */
@HiltViewModel
class InputCaptchaCodeVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {
    private val _inputCaptchaCodeUiState = mutableStateOf(InputCaptchaCodeUiState())
    val inputCaptchaCodeUiState: State<InputCaptchaCodeUiState> = _inputCaptchaCodeUiState

    fun onReceiveEvent(event: InputCaptchaCodeEvent) {
        when (event) {
            InputCaptchaCodeEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            InputCaptchaCodeEvent.ClickCannotReceiveCode -> {
                // TODO: 点击无法收到验证码
            }

            InputCaptchaCodeEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮
            }

            InputCaptchaCodeEvent.ClickHelpBtn -> {
                // TODO: 点击帮助按钮
            }

            InputCaptchaCodeEvent.ClickResendCode -> {
                // TODO: 点击重试按钮
            }

            is InputCaptchaCodeEvent.InputCaptchaCode -> {
                _inputCaptchaCodeUiState.value =
                    _inputCaptchaCodeUiState.value.copy(captchaCode = event.code)
            }
        }
    }
}