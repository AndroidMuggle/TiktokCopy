package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.FindPasswordEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.FindPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindPasswordPageVm @Inject constructor(val repo: LoginRepo) : ViewModel() {
    private val _findPasswordUiState = mutableStateOf(FindPasswordUiState())
    val findPasswordUiState: State<FindPasswordUiState> = _findPasswordUiState

    fun onReceiveEvent(event: FindPasswordEvent) {
        when (event) {
            FindPasswordEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            FindPasswordEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮
            }

            is FindPasswordEvent.ClickPrivacyBtn -> {
                _findPasswordUiState.value =
                    _findPasswordUiState.value.copy(isPrivacySelect = event.isSelect)
            }

            FindPasswordEvent.ClickResendBtn -> {
                // TODO:  点击重试按钮
            }

            is FindPasswordEvent.InputCaptchaCode -> {
                _findPasswordUiState.value = _findPasswordUiState.value.copy(captchaCode = event.code)
            }
        }
    }
}