package com.ruzibekov.domain.repository

import com.ruzibekov.domain.model.Tour

interface TourRepository {

    suspend fun getTour(): Tour
}