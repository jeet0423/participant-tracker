package com.participanttracker.app.data.repository

import android.content.Context
import com.google.gson.Gson
import com.participanttracker.app.data.model.ParticipantDto
import com.participanttracker.app.data.model.ParticipantsResponse
import com.participanttracker.app.domain.model.Participant
import com.participanttracker.app.domain.repository.ParticipantRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParticipantRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : ParticipantRepository {

    private var cachedParticipants: List<Participant>? = null

    override suspend fun getParticipantById(id: String): Participant? {
        val participants = getAllParticipants()
        return participants.find { it.id == id }
    }

    override suspend fun getAllParticipants(): List<Participant> {
        cachedParticipants?.let { return it }

        val jsonString = loadJsonFromAsset("participants.json")
        val response = gson.fromJson(jsonString, ParticipantsResponse::class.java)
        cachedParticipants = response.participants.map { it.toDomain() }
        return cachedParticipants!!
    }

    private fun loadJsonFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun ParticipantDto.toDomain(): Participant {
        return Participant(
            id = id,
            name = name,
            event = event,
            category = category,
            bibNumber = bibNumber,
            kitCollected = kitCollected
        )
    }
}
