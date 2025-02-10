package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class PauseAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.pauseAudio()
}


class PlayAudioUseCase @Inject constructor(private val repository: AudioRepository) {
    suspend operator fun invoke(resourceId: Int) = repository.playAudio(resourceId)
}

class RewindAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.rewind()
}

class FastForwardAudioUseCase @Inject constructor(private val repository: AudioRepository){
    suspend operator fun invoke() = repository.fastForward()
}