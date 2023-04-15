package com.coursework.speakoutchat.auth_ui.sign_up

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.auth_ui.R
import com.coursework.speakoutchat.auth_ui.databinding.FragmentSignUpBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe signUpViewModel") {
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