package com.ruzibekov.domain.model

data class TourState(
    val currentStep: Int = 1,
    val totalSteps: Int = 10,
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
