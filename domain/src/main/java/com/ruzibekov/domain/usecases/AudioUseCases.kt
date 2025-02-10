package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class PauseAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.pauseAudio()
}


class PlayAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(resourceId: Int, speed: AudioSpeed) = repository.playAudio(resourceId, speed)
}

class RewindAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.rewind()
}

class FastForwardAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.fastForward()
}

class ChangeAudioSpeedUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke(current: AudioSpeed) = repository.getNextAudioSpeed(current)
}