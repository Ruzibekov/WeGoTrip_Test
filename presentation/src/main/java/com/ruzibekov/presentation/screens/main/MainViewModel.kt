package com.ruzibekov.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruzibekov.domain.usecases.ChangeAudioPositionUseCase
import com.ruzibekov.domain.usecases.ChangeAudioSpeedUseCase
import com.ruzibekov.domain.usecases.FastForwardAudioUseCase
import com.ruzibekov.domain.usecases.GetAudioDefaultValuesUseCase
import com.ruzibekov.domain.usecases.GetAudioPositionUseCase
import com.ruzibekov.domain.usecases.GetTourUseCase
import com.ruzibekov.domain.usecases.InitializeMediaPlayerUseCase
import com.ruzibekov.domain.usecases.PauseAudioUseCase
import com.ruzibekov.domain.usecases.PlayAudioUseCase
import com.ruzibekov.domain.usecases.RewindAudioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTourUseCase: GetTourUseCase,
    private val initMediaPlayerUseCase: InitializeMediaPlayerUseCase,
    private val getAudioDefaultValuesUseCase: GetAudioDefaultValuesUseCase,
    private val playAudioUseCase: PlayAudioUseCase,
    private val pauseAudioUseCase: PauseAudioUseCase,
    private val rewindAudioUseCase: RewindAudioUseCase,
    private val fastForwardAudioUseCase: FastForwardAudioUseCase,
    private val changeAudioSpeedUseCase: ChangeAudioSpeedUseCase,
    private val changeAudioPositionUseCase: ChangeAudioPositionUseCase,
    getAudioPositionUseCase: GetAudioPositionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadTour()

        viewModelScope.launch {
            getAudioDefaultValuesUseCase()?.run {
                _state.update { it.copy(isPlaying = first, audioSpeed = second) }
            }
        }

        getAudioPositionUseCase()
            .onEach { new ->
                _state.update { it.copy(currentPositionInMillis = new) }
            }
            .launchIn(viewModelScope)
    }

    private fun loadTour() {
        viewModelScope.launch {
            try {
                val tour = getTourUseCase()
                _state.update { it.copy(tour = tour) }

                _state.value.getCurrentTourStep()?.let { tourStep ->
                    initMediaPlayerUseCase(
                        tourStep.audio,
                        onCompletion = { sendAction(MainAction.OnPauseClick) },
                        onDuration = { millis ->
                            _state.update { it.copy(durationInMillis = millis) }
                        }
                    )
                }
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

                        playAudioUseCase(
                            speed = _state.value.audioSpeed,
                            onUpdatePosition = { position ->
                                _state.update {
                                    it.copy(
                                        currentPositionInMillis = position
                                    )
                                }
                            }
                        )

                        _state.update {
                            it.copy(isPlaying = true, error = null)
                        }
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

                    is MainAction.ChangeSpeed -> {
                        val newSpeed = changeAudioSpeedUseCase(action.currentSpeed)
                        _state.update { it.copy(audioSpeed = newSpeed) }
                    }

                    is MainAction.ChangePosition -> {
                        changeAudioPositionUseCase(action.value)
                    }
                }
            } catch (e: Exception) {
                Log.e("RRR", e.message.toString())
            }
        }
    }
}