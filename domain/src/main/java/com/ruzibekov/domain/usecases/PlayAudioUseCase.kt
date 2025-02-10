package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.repository.AudioRepository
import javax.inject.Inject

class PlayAudioUseCase @Inject constructor(private val repository: AudioRepository) {

    suspend operator fun invoke(resourceId: Int) = repository.playAudio(resourceId)
}