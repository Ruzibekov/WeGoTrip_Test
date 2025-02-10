package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.Tour
import com.ruzibekov.domain.repository.TourRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetTourUseCase @Inject constructor(
    private val repository: TourRepository
) {

    suspend operator fun invoke(): Tour {
        delay(2000)
        return repository.getTour()
    }
}