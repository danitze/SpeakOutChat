package com.coursework.speakoutchat.auth_ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.speakoutchat.auth_domain_data.use_case.LoginUseCase
import com.coursework.speakoutchat.auth_ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun login(name: String, password: String) {

        if (name.isBlank()) {
            _uiState.update { state ->
                state.copy(loginErrorEvent = LoginErrorEvent(R.string.blank_username))
            }
            return
        }

        if (password.isBlank()) {
            _uiState.update { state ->
                state.copy(loginErrorEvent = LoginErrorEvent(R.string.blank_password))
            }
            return
        }

        viewModelScope.launch {
            loginUseCase.login(name, password).onSuccess {
                _uiState.update { it.copy(loginSuccessEvent = Unit) }
            }.onFailure {
                _uiState.update { state ->
                    state.copy(
                        loginErrorEvent = LoginErrorEvent(R.string.unknown_failure)
                    )
                }
            }
        }
    }

    fun loginSuccessEventConsumed() {
        _uiState.update { it.copy(loginSuccessEvent = null) }
    }

    fun loginErrorEventConsumed() {
        _uiState.update { it.copy(loginErrorEvent = null) }
    }

}