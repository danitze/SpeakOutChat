package com.coursework.speakoutchat.menu_ui.menu

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.menu_ui.R
import com.coursework.speakoutchat.menu_ui.databinding.FragmentMenuBinding
import com.coursework.speakoutchat.menu_ui.topic_choose.DialogTopic
import com.coursework.speakoutchat.menu_ui.topic_choose.TopicChooseBottomSheetDialog
import com.coursework.speakoutchat.menu_ui.topic_choose.TopicChosenEventApi
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TOPIC_CHOOSE_DIALOG_FRAGMENT_TAG = "TOPIC_CHOOSE_DIALOG_FRAGMENT_TAG"

@AndroidEntryPoint
class MenuFragment : BaseFragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    private val menuViewModel: MenuViewModel by viewModels()

    @Inject
    lateinit var topicChosenEventApi: TopicChosenEventApi

    override fun setupObservers() {
        launchWhenStarted("Observe menuViewModel") { scope ->
            topicChosenEventApi.topicChosenEvent.filterNotNull().onEach { topic ->
                navigateToPartnerChoose(topic)
                topicChosenEventApi.consumeTopicChosenEvent()
            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
    }

    override fun setupUiListeners() {
        with(binding) {
            buttonStartChat.setOnClickListener {
                showTopicChooseDialog()
            }
        }
    }

    private fun showTopicChooseDialog() {
        TopicChooseBottomSheetDialog.newInstance(topicChosenEventApi)
            .show(childFragmentManager, TOPIC_CHOOSE_DIALOG_FRAGMENT_TAG)
    }

    private fun navigateToPartnerChoose(topic: DialogTopic) {
        val action = MenuFragmentDirections.actionMenuToPartnerSearch(topic.topic, topic.title)
        findNavController().navigate(action)
    }
}