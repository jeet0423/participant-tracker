package com.participanttracker.app.domain.usecase

import com.participanttracker.app.domain.model.Participant
import com.participanttracker.app.domain.repository.ParticipantRepository
import javax.inject.Inject

class GetParticipantByIdUseCase @Inject constructor(
    private val repository: ParticipantRepository
) {
    suspend operator fun invoke(id: String): Participant? {
        return repository.getParticipantById(id)
    }
}
