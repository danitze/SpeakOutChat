package com.coursework.speakoutchat.partner_search_ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.partner_search_ui.databinding.FragmentPartnerSearchBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PartnerSearchFragment : BaseFragment(R.layout.fragment_partner_search) {

    private val binding by viewBinding(FragmentPartnerSearchBinding::bind)

    private val args: PartnerSearchFragmentArgs by navArgs()

    private val viewModel: PartnerSearchViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe PartnerSearchFragment data") { scope ->
            viewModel.uiState.onEach { uiState ->

                if (uiState.partnerFoundEvent != null) {
                    navigateToChat(uiState.partnerFoundEvent.partnerId)
                    viewModel.partnerFoundEventConsumed()
                }

            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
        with(binding) {
            val topicTitle = requireContext().getString(args.topicTitle)
            textPartnerSearchDescription.text = requireContext().getString(
                R.string.partner_search_fragment_search_description,
                topicTitle
            )
        }
    }

    override fun setupUiListeners() {
    }

    private fun navigateToChat(partnerId: String) {
        val action = PartnerSearchFragmentDirections.actionPartnerSearchToChat(partnerId)
        findNavController().navigate(action)
    }

}