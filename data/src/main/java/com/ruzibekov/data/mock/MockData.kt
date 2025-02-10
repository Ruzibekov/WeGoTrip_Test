package com.ruzibekov.data.mock

import com.ruzibekov.data.R
import com.ruzibekov.domain.model.BaseTour
import com.ruzibekov.domain.model.Tour

class MockData {

    private val tourStep = Tour(
        title = "Свято-вознесенский Кафедральный собор",
        description = "Впечатляйтесь деревянным Свято-Вознесенским Кафедральным собором, одним из ста чудес света",
        images = listOf(
            "https://i.ibb.co/tTKbsMYp/feed-photo.jpg",
            "https://i.ibb.co/HDWVN9rd/1.jpg",
            "https://i.ibb.co/RpcjQdmQ/125913.jpg",
            "https://i.ibb.co/8GbyK89/Almatinskiy-Svyato-Voznese.jpg",
            "https://i.ibb.co/KzwMLCSk/wr-750.webp"
        ),
        audio = R.raw.svyato_voznesenskiy
    )

    val tour = BaseTour(
        title = "Алматы; погружение в жизнь \"южной столицы\" Казахстана",
        steps = listOf(tourStep, tourStep, tourStep)
    )
}