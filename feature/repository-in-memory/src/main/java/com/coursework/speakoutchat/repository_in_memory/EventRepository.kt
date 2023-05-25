package com.coursework.speakoutchat.repository_in_memory

import com.coursework.speakoutchat.menu_ui.topic_choose.DialogTopic
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor() {

    internal val topicChosenEvent = MutableStateFlow<DialogTopic?>(null)

    internal val navigateToLoginEvent = MutableStateFlow<Unit?>(null)

    internal val navigateToMenuEvent = MutableStateFlow<Unit?>(null)

}