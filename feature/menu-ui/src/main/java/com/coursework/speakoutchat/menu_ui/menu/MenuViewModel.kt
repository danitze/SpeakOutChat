package com.coursework.speakoutchat.menu_ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.speakoutchat.auth_domain_data.use_case.LogoutUseCase
import com.coursework.speakoutchat.auth_domain_data.use_case.ObserveUserNameUseCase
import com.coursework.speakoutchat.menu_ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    observeUserNameUseCase: ObserveUserNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState = _uiState.asStateFlow()

    private val userNameFlow = observeUserNameUseCase.observeUserName()
        .onEach { userName ->
            _uiState.update { it.copy(userName = userName) }
        }

    init {
        observeIntermediateFlows()
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
                .onSuccess {
                    _uiState.update { it.copy(logoutSuccessEvent = Unit) }
                }.onFailure {
                    _uiState.update { state ->
                        state.copy(
                            logoutFailureEvent = LogoutFailureEvent(R.string.menu_could_not_logout)
                        )
                    }
                }
        }
    }

    private fun observeIntermediateFlows() {
        viewModelScope.launch {
            userNameFlow.launchIn(this)
        }
    }

    fun logoutSuccessEventConsumed() {
        _uiState.update { it.copy(logoutSuccessEvent = null) }
    }

    fun logoutFailureEventConsumed() {
        _uiState.update { it.copy(logoutFailureEvent = null) }
    }

}