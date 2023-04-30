package com.coursework.speakoutchat.menu_ui.topic_choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.coursework.speakoutchat.menu_ui.databinding.DialogTopicChooseBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TopicChooseBottomSheetDialog(
    private val topicChosenEventApi: TopicChosenEventApi
) : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(topicChosenEventApi: TopicChosenEventApi): TopicChooseBottomSheetDialog {
            return TopicChooseBottomSheetDialog(topicChosenEventApi)
        }
    }

    private lateinit var binding: DialogTopicChooseBinding

    private val topicChooseAdapter = TopicChooseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTopicChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    private fun setupObservers() {
    }

    private fun setupUiComponents() {
        setupRecyclerView()
    }

    private fun setupUiListeners() {
        topicChooseAdapter.onItemClicked = { dialogTopic ->
            topicChosenEventApi.onTopicChosen(dialogTopic)
            dismiss()
        }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerDialogTopics) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = topicChooseAdapter
        }
    }

}