package com.coursework.speakoutchat.partner_search_ui

import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.partner_search_ui.databinding.FragmentPartnerSearchBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnerSearchFragment : BaseFragment(R.layout.fragment_partner_search) {

    private val binding by viewBinding(FragmentPartnerSearchBinding::bind)

    private val args: PartnerSearchFragmentArgs by navArgs()

    override fun setupObservers() {
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


}