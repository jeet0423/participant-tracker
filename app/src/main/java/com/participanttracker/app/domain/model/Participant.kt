package com.participanttracker.app.domain.model

data class Participant(
    val id: String,
    val name: String,
    val event: String,
    val category: String,
    val bibNumber: String,
    val kitCollected: Boolean
)
