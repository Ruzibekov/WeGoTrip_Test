package com.ruzibekov.domain.repository

import com.ruzibekov.domain.model.AudioSpeed

interface AudioRepository {

    suspend fun playAudio(resourceId: Int, speed: AudioSpeed)

    suspend fun pauseAudio()

    suspend fun rewind()

    suspend fun fastForward()

    suspend fun getNextAudioSpeed(current: AudioSpeed): AudioSpeed
}