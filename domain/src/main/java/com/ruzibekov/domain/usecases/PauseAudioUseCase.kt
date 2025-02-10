package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class PauseAudioUseCase @Inject constructor(private val repository: AudioRepository){

    suspend operator fun invoke() = repository.pauseAudio()
}