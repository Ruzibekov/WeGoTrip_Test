package com.ruzibekov.domain.audio

interface AudioPlayer {
    fun play(url: String)
    fun pause()
    fun seek(offsetMs: Int)
    fun stop()
    fun setOnProgressChanged(listener: (Float) -> Unit)
}