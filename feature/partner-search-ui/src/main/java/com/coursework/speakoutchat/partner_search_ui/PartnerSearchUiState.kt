package com.coursework.speakoutchat.partner_search_ui

data class PartnerSearchUiState(
    val stompErrorEvent: Unit? = null,
    val partnerFoundEvent: PartnerFoundEvent? = null
)

data class PartnerFoundEvent(val partnerId: String)
