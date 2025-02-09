package com.ruzibekov.data.repository

import com.ruzibekov.domain.model.Tour
import com.ruzibekov.domain.model.TourStep
import com.ruzibekov.domain.repository.TourRepository
import javax.inject.Inject

class TourRepositoryImpl @Inject constructor() : TourRepository {

    override suspend fun getTour(): Tour {
        return Tour(
            title = "Алматы; погружение в жизнь \"южной столицы\" Казахстана",
            steps = getSteps()
        )
    }

    private fun getSteps() = listOf(
        TourStep(
            title = "Свято-вознесенский Кафедральный собор",
            description = "Впечатляйтесь деревянным Свято-Вознесенским Кафедральным собором, одним из ста чудес света",
            images = listOf(
                "https://ibb.co/Jwtz7Rrj",
                "https://ibb.co/gM53vcDV",
                "https://ibb.co/BVBnTFxT",
                "https://ibb.co/w87n064",
                "https://ibb.co/bRJS2pDZ"
            ),
            audioUrl = ""
        )
    )
}