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

sealed interface MainScreenAction {
    data object OnBackClick : MainScreenAction
    data object OnPlayClick : MainScreenAction
    data object OnNextStep : MainScreenAction
    data object OnPreviousStep : MainScreenAction
    data class OnSeekAudio(val position: Float) : MainScreenAction
}