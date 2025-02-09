package com.ruzibekov.domain.model

data class Tour(
    val id: String,
    val title: String,
    val steps: List<TourStep>
)