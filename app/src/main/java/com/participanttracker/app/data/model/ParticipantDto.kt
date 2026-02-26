package com.participanttracker.app.data.model

import com.google.gson.annotations.SerializedName

data class ParticipantDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("event") val event: String,
    @SerializedName("category") val category: String,
    @SerializedName("bibNumber") val bibNumber: String,
    @SerializedName("kitCollected") val kitCollected: Boolean
)

data class ParticipantsResponse(
    @SerializedName("participants") val participants: List<ParticipantDto>
)
