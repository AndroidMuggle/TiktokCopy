package com.muggle.tiktokcopy.business.login.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.NewPasswordEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.NewPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2025/12/7 15:59
 * @author muggle
 * @desc
 */
@HiltViewModel
class NewPasswordVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _newPasswordUiState = MutableStateFlow(NewPasswordUiState())
    val newPasswordUiState: StateFlow<NewPasswordUiState> = _newPasswordUiState

    fun onReceiveEvent(event: NewPasswordEvent) {
        when (event) {
            NewPasswordEvent.ClickBackBtn -> {
                // TODO: 点击返回按钮
            }

            NewPasswordEvent.ClickConfirmBtn -> {
                // TODO: 点击确认按钮
            }

            is NewPasswordEvent.InputPassword -> {
                _newPasswordUiState.update {
                    it.copy(
                        newPassword = event.password
                    )
                }
            }
        }
    }
}