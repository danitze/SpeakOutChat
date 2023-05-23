package com.coursework.speakoutchat.auth_ui.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.speakoutchat.auth_domain_data.use_case.SignUpUseCase
import com.coursework.speakoutchat.auth_ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    fun signUp(name: String, password: String) {

        if (name.isBlank()) {
            _uiState.update { state ->
                state.copy(signUpErrorEvent = SignUpErrorEvent(R.string.blank_username))
            }
            return
        }

        if (password.isBlank()) {
            _uiState.update { state ->
                state.copy(signUpErrorEvent = SignUpErrorEvent(R.string.blank_password))
            }
            return
        }

        viewModelScope.launch {
            signUpUseCase.signUp(name, password).onSuccess {
                _uiState.update { it.copy(signUpSuccessEvent = Unit) }
            }.onFailure { exception ->
                _uiState.update { state ->
                    state.copy(
                        signUpErrorEvent = SignUpErrorEvent(R.string.unknown_failure)
                    )
                }
            }
        }
    }

    fun signUpSuccessEventConsumed() {
        _uiState.update { it.copy(signUpSuccessEvent = null) }
    }

    fun signUpErrorEventConsumed() {
        _uiState.update { it.copy(signUpErrorEvent = null) }
    }
}