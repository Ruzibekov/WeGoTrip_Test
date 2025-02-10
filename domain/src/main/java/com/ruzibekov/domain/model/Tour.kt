package com.ruzibekov.domain.model

data class Tour(
    val title: String,
    val description: String,
    val images: List<String>,
    val audio: Int,
)