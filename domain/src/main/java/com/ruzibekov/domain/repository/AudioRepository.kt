package com.ruzibekov.domain.repository

interface AudioRepository {

    suspend fun playAudio(resourceId: Int)
}