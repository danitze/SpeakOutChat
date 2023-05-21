package com.coursework.speakoutchat.auth_ui.sign_up

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.auth_ui.R
import com.coursework.speakoutchat.auth_ui.databinding.FragmentSignUpBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe signUpViewModel") { scope ->
            viewModel.uiState.onEach { uiState ->
                if (uiState.signUpSuccessEvent != null) {
                    navigateBack()
                    viewModel.signUpSuccessEventConsumed()
                }

                if (uiState.signUpErrorEvent != null) {
                    showErrorSnackbar(binding, uiState.signUpErrorEvent.messageId)
                    viewModel.signUpErrorEventConsumed()
                }
            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
    }

    override fun setupUiListeners() {
        with(binding) {
            buttonSignUp.setOnClickListener {
                val name = editTextLogin.text?.toString() ?: ""
                val password = editTextPassword.text?.toString() ?: ""
                viewModel.signUp(name, password)
            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }
}