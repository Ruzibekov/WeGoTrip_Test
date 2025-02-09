package com.ruzibekov.domain.mock

import com.ruzibekov.domain.model.Tour
import com.ruzibekov.domain.model.TourStep

class MockData {

    val tourStep = TourStep(
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

    val tour = Tour(
        title = "Алматы; погружение в жизнь \"южной столицы\" Казахстана",
        steps = listOf(tourStep, tourStep, tourStep)
    )
}