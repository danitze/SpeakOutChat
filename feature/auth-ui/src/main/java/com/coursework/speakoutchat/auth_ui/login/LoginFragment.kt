package com.coursework.speakoutchat.auth_ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.auth_ui.R
import com.coursework.speakoutchat.auth_ui.databinding.FragmentLoginBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe loginViewModel") { scope ->
            viewModel.uiState.onEach { uiState ->

            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
    }

    override fun setupUiListeners() {
        with(binding) {
            buttonSignUp.setOnClickListener {
                navigateToSignUp()
            }

            buttonLogin.setOnClickListener {
                val name = editTextLogin.text?.toString() ?: ""
                val password = editTextPassword.text?.toString() ?: ""
                viewModel.login(name, password)
            }
        }
    }

    private fun navigateToSignUp() {
        findNavController().navigate(R.id.action_login_to_sign_up)
    }

    private fun navigateToMenu() {
        findNavController().navigate(R.id.action_login_to_menu)
    }
}