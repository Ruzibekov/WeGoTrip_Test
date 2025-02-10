package com.ruzibekov.presentation.screens.main

import androidx.annotation.RawRes
import com.ruzibekov.domain.model.Tour

data class MainState(
    val tour: Tour? = null,
    val isPlaying: Boolean = false,
    val error: String? = null
)

sealed interface MainAction {
    data class OnPlayClick(@RawRes val resourceId: Int) : MainAction
    data object OnPauseClick : MainAction
    data object OnRewindAudioClick : MainAction
    data object OnFastForwardClick : MainAction
}