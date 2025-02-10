package com.ruzibekov.presentation.screens.main

import androidx.annotation.RawRes
import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.model.Tour

data class MainState(
    val tour: Tour? = null,
    val isPlaying: Boolean = false,
    val audioSpeed: AudioSpeed = AudioSpeed.NORMAL,
    val position: Float = 0f,
    val durationInMillis: Int = 0,
    val error: String? = null
)

sealed interface MainAction {
    data class OnPlayClick(@RawRes val resourceId: Int) : MainAction
    data object OnPauseClick : MainAction
    data object OnRewindAudioClick : MainAction
    data object OnFastForwardClick : MainAction
    data class ChangeSpeed(val currentSpeed: AudioSpeed) : MainAction
    data class ChangePosition(val value: Float) : MainAction
}

