package com.ruzibekov.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruzibekov.domain.usecases.FastForwardAudioUseCase
import com.ruzibekov.domain.usecases.GetTourUseCase
import com.ruzibekov.domain.usecases.PauseAudioUseCase
import com.ruzibekov.domain.usecases.PlayAudioUseCase
import com.ruzibekov.domain.usecases.RewindAudioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTourUseCase: GetTourUseCase,
    private val playAudioUseCase: PlayAudioUseCase,
    private val pauseAudioUseCase: PauseAudioUseCase,
    private val rewindAudioUseCase: RewindAudioUseCase,
    private val fastForwardAudioUseCase: FastForwardAudioUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadTour()
    }

    private fun loadTour() {
        viewModelScope.launch {
            try {
                val tour = getTourUseCase()
                _state.update { it.copy(tour = tour) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun sendAction(action: MainAction) {
        viewModelScope.launch {
            try {
                when (action) {
                    is MainAction.OnPlayClick -> {
                        playAudioUseCase(action.resourceId)
                        _state.update { it.copy(isPlaying = true, error = null) }
                    }

                    MainAction.OnPauseClick -> {
                        pauseAudioUseCase()
                        _state.update { it.copy(isPlaying = false, error = null) }
                    }

                    MainAction.OnFastForwardClick -> {
                        fastForwardAudioUseCase()
                    }

                    MainAction.OnRewindAudioClick -> {
                        rewindAudioUseCase()
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
}