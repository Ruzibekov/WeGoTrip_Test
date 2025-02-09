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

sealed interface MainAction {
    data object OnBackClick : MainAction
    data object OnPlayClick : MainAction
    data object OnNextStep : MainAction
    data object OnPreviousStep : MainAction
    data class OnSeekAudio(val position: Float) : MainAction
}