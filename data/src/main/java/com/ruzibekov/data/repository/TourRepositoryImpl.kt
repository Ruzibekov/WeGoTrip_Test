package com.ruzibekov.data.repository

import com.ruzibekov.data.mock.MockData
import com.ruzibekov.domain.model.BaseTour
import com.ruzibekov.domain.repository.TourRepository
import javax.inject.Inject

class TourRepositoryImpl @Inject constructor() : TourRepository {

    override suspend fun getTour(): BaseTour {
        return MockData().tour
    }
}