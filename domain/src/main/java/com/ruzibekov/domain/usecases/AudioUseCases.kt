package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class GetAudioDefaultValuesUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.getDefaultValues()
}

class PlayAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(resourceId: Int, speed: AudioSpeed) = repository.play(resourceId, speed)
}

class GetAudioPositionUseCase @Inject constructor(private val repository: AudioRepository) {
    operator fun invoke() = repository.getCurrentPosition()
}

class PauseAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.pause()
}

class RewindAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.rewind()
}

class FastForwardAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.fastForward()
}

class ChangeAudioSpeedUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke(current: AudioSpeed) = repository.getNextSpeed(current)
}