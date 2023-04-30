package com.coursework.speakoutchat.menu_ui.topic_choose

import kotlinx.coroutines.flow.StateFlow

interface TopicChosenEventApi {

    val topicChosenEvent: StateFlow<DialogTopic?>

    fun onTopicChosen(dialogTopic: DialogTopic)

    fun consumeTopicChosenEvent()
}