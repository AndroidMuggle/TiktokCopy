package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.NewPasswordEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.NewPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2025/12/7 15:59
 * @author muggle
 * @desc
 */
@HiltViewModel
class NewPasswordVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _newPasswordUiState = mutableStateOf(NewPasswordUiState())
    val newPasswordUiState: State<NewPasswordUiState> = _newPasswordUiState

    fun onReceiveEvent(event: NewPasswordEvent) {
        when (event) {
            NewPasswordEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            NewPasswordEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮
            }

            is NewPasswordEvent.InputPassword -> {
                _newPasswordUiState.value = _newPasswordUiState.value.copy(newPassword = event.password)
            }
        }
    }
}