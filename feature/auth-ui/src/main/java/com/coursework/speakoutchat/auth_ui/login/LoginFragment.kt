package com.coursework.speakoutchat.auth_ui.login

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.auth_ui.R
import com.coursework.speakoutchat.auth_ui.databinding.FragmentLoginBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe loginViewModel") {
            TODO("Not yet implemented")
        }
    }

    override fun setupUiComponents() {
        TODO("Not yet implemented")
    }

    override fun setupUiListeners() {
        TODO("Not yet implemented")
    }
}