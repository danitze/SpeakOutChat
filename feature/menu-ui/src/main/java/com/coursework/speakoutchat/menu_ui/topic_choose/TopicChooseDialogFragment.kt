package com.coursework.speakoutchat.menu_ui.topic_choose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.menu_ui.R
import com.coursework.speakoutchat.menu_ui.databinding.DialogFragmentTopicChooseBinding
import javax.inject.Inject

class TopicChooseDialogFragment : DialogFragment(R.layout.dialog_fragment_topic_choose) {

    private val binding by viewBinding(DialogFragmentTopicChooseBinding::bind)

    @Inject
    lateinit var topicChooseAdapter: TopicChooseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUiComponents()
        setupUiListeners()
    }

    override fun onDestroyView() {
        binding.recyclerDialogTopics.adapter = null
        super.onDestroyView()
    }

    fun setupObservers() {
    }

    fun setupUiComponents() {
        setupRecyclerView()
    }

    fun setupUiListeners() {
    }

    private fun setupRecyclerView() {
        with(binding.recyclerDialogTopics) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = topicChooseAdapter
            topicChooseAdapter.onItemClicked = { dialogTopic ->

            }
        }

    }

}