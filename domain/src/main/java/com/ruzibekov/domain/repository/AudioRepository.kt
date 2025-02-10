package com.ruzibekov.domain.repository

import com.ruzibekov.domain.model.AudioSpeed
import kotlinx.coroutines.flow.Flow

interface AudioRepository {

    suspend fun initial(
        resourceId: Int,
        onDuration: (millis: Int) -> Unit,
        onCompleted: () -> Unit,
    )

    suspend fun getDefaultValues(): Pair<Boolean, AudioSpeed>?

    suspend fun play(speed: AudioSpeed, onUpdatePosition: (Float) -> Unit)

    fun getCurrentPosition(): Flow<Float>

    suspend fun pause()

    suspend fun rewind()

    suspend fun fastForward()

    suspend fun getNextSpeed(current: AudioSpeed): AudioSpeed

    suspend fun changePosition(newValue: Float)
}