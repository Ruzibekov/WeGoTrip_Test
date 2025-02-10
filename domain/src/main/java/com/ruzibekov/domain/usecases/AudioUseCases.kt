package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class InitializeMediaPlayerUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(
        resourceId: Int,
        onCompletion: () -> Unit,
        onDuration: (millis: Int) -> Unit
    ) = repository.initial(resourceId, onDuration, onCompletion)
}

class GetAudioDefaultValuesUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke() = repository.getDefaultValues()
}

class PlayAudioUseCase @Inject constructor(private val repository: AudioRepository) {

    suspend operator fun invoke(speed: AudioSpeed, onUpdatePosition: (Float) -> Unit) = repository.play(speed, onUpdatePosition)
}

class GetAudioPositionUseCase @Inject constructor(private val repository: AudioRepository) {
    operator fun invoke() = repository.getCurrentPosition()
}

class PauseAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke() = repository.pause()
}

class RewindAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke() = repository.rewind()
}

class FastForwardAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke() = repository.fastForward()
}

class ChangeAudioSpeedUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(current: AudioSpeed) = repository.getNextSpeed(current)
}

class ChangeAudioPositionUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(newValue: Float) = repository.changePosition(newValue)
}