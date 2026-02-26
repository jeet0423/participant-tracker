package com.participanttracker.app.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.participanttracker.app.domain.model.Participant
import com.participanttracker.app.domain.usecase.GetParticipantByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val participantId: String = "",
    val participant: Participant? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getParticipantByIdUseCase: GetParticipantByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onParticipantIdChange(id: String) {
        _uiState.value = _uiState.value.copy(participantId = id, error = null)
    }

    fun searchParticipant() {
        val id = _uiState.value.participantId
        if (id.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Please enter a Participant ID")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val participant = getParticipantByIdUseCase(id)
                _uiState.value = _uiState.value.copy(
                    participant = participant,
                    isLoading = false,
                    error = if (participant == null) "Participant not found" else null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An error occurred"
                )
            }
        }
    }

    fun setParticipantFromQR(participantId: String) {
        _uiState.value = _uiState.value.copy(participantId = participantId)
        searchParticipant()
    }
}
