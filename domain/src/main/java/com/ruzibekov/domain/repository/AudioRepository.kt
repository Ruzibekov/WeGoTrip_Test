package com.ruzibekov.domain.repository

interface AudioRepository {

    suspend fun playAudio(resourceId: Int)

    suspend fun pauseAudio()

    suspend fun rewind()

    suspend fun fastForward()
}