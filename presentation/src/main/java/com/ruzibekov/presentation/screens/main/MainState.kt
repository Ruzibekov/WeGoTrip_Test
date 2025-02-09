package com.ruzibekov.presentation.screens.main

import com.ruzibekov.domain.model.Tour
import com.ruzibekov.domain.model.TourStep

sealed interface MainState {
    data object Loading : MainState

    data class Success(
        val tour: Tour,
        val currentStep: TourStep,
        val isPlaying: Boolean,
        val audioProgress: Float,
        val totalSteps: Int
    ) : MainState

    data class Error(val message: String) : MainState
}

sealed interface MainAudioAction : MainAudioControllerAction {
    data object OnBackClick : MainAudioAction
    data object OnPlayClick : MainAudioAction
    data object OnNextStep : MainAudioAction
    data object OnPreviousStep : MainAudioAction
    data class OnSeekAudio(val position: Float) : MainAudioAction
}

sealed interface MainAudioControllerAction {
    data object HideAudioController : MainAudioControllerAction
}