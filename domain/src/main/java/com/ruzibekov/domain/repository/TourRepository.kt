package com.ruzibekov.domain.repository

import com.ruzibekov.domain.model.BaseTour

interface TourRepository {

    suspend fun getTour(): BaseTour
}