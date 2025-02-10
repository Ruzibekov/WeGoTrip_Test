package com.ruzibekov.domain.model

enum class AudioSpeed(val speed: Float) {
    NORMAL(1f),
    FAST(1.5f),
    FASTER(2.0f),
    SLOW(0.5f);

    fun next() = entries[(ordinal + 1) % entries.size]
}