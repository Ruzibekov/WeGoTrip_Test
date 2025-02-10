package com.ruzibekov.domain.repository

import com.ruzibekov.domain.model.AudioSpeed
import kotlinx.coroutines.flow.Flow

interface AudioRepository {

    suspend fun getDefaultValues(): Pair<Boolean, AudioSpeed>?

    suspend fun play(resourceId: Int, speed: AudioSpeed)

    fun getCurrentPosition(): Flow<Float>

    suspend fun pause()

    suspend fun rewind()

    suspend fun fastForward()

    suspend fun getNextSpeed(current: AudioSpeed): AudioSpeed
}