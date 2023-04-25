package com.coursework.speakoutchat.menu_ui.topic_choose

import com.coursework.speakoutchat.menu_ui.R

enum class DialogTopic(val topic: String, val title: Int) {
    WORK_STUDY("WORK_STUDY", R.string.topic_choose_dialog_work_study),
    RELATIONSHIP("RELATIONSHIP", R.string.topic_choose_dialog_relationship),
    FAMILY("FAMILY", R.string.topic_choose_dialog_family),
    HEALTH("HEALTH", R.string.topic_choose_dialog_health),
    SPIRITUAL_PROBLEMS("SPIRITUAL_PROBLEMS", R.string.topic_choose_dialog_spiritual_problems),
    LEGAL_PROBLEMS("LEGAL_PROBLEMS", R.string.topic_choose_dialog_legal_problems)
}