package com.participanttracker.app.domain.repository

import com.participanttracker.app.domain.model.Participant

interface ParticipantRepository {
    suspend fun getParticipantById(id: String): Participant?
    suspend fun getAllParticipants(): List<Participant>
}
