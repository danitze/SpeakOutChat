package com.coursework.speakoutchat.repository_in_memory.impl

import com.coursework.speakoutchat.menu_ui.topic_choose.DialogTopic
import com.coursework.speakoutchat.menu_ui.topic_choose.TopicChosenEventApi
import com.coursework.speakoutchat.repository_in_memory.EventRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class TopicChosenEventImpl @Inject constructor(
    private val eventRepository: EventRepository
) : TopicChosenEventApi {

    override val topicChosenEvent: StateFlow<DialogTopic?> = eventRepository
        .topicChosenEvent.asStateFlow()

    override fun onTopicChosen(dialogTopic: DialogTopic) {
        eventRepository.topicChosenEvent.value = dialogTopic
    }

    override fun consumeTopicChosenEvent() {
        eventRepository.topicChosenEvent.value = null
    }

}