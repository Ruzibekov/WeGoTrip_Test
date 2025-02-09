package com.ruzibekov.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruzibekov.domain.usecases.GetTourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTourUseCase: GetTourUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

    init {
        loadTour()
    }

    private fun loadTour() {
        viewModelScope.launch {
            try {
                val tour = getTourUseCase()
                _state.value = MainState.Success(
                    tour = tour,
                    currentStep = tour.steps.first(),
                    isPlaying = false,
                    audioProgress = 0f,
                    totalSteps = tour.steps.size
                )
            } catch (e: Exception) {
                _state.value = MainState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun sendAction(action: MainAction) {
        when(action){
            MainAction.OnBackClick -> TODO()
            MainAction.OnNextStep -> TODO()
            MainAction.OnPlayClick -> TODO()
            MainAction.OnPreviousStep -> TODO()
            is MainAction.OnSeekAudio -> TODO()
        }
    }
}