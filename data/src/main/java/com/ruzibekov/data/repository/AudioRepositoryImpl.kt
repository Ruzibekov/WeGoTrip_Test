package com.ruzibekov.data.repository

import android.content.Context
import android.media.MediaPlayer
import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.repository.AudioRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioRepositoryImpl @Inject constructor(
    private val audioPlayer: MediaPlayer,
    @ApplicationContext private val context: Context
) : AudioRepository {

    override suspend fun getDefaultValues() =
        if (audioPlayer.isPlaying)
            Pair(
                audioPlayer.isPlaying,
                AudioSpeed.entries.firstOrNull { it.speed == audioPlayer.playbackParams.speed }
                    ?: AudioSpeed.NORMAL
            )
    else null

    private var currentResourceId: Int? = null
    private var positionTrackingJob: Job? = null

    override suspend fun play(
        resourceId: Int,
        speed: AudioSpeed
    ) = withContext(Dispatchers.IO) {

        if (currentResourceId != resourceId) {
            currentResourceId = resourceId
            audioPlayer.reset()

            context.resources.openRawResourceFd(resourceId)?.use { fd ->
                audioPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            }

            audioPlayer.prepare()
        }

        audioPlayer.playbackParams = audioPlayer.playbackParams.setSpeed(speed.speed)
        audioPlayer.start()
    }

    override fun getCurrentPosition() = flow {
        while (true) {
            if (audioPlayer.isPlaying)
                emit((audioPlayer.currentPosition.toFloat() / audioPlayer.duration.toFloat()))
            delay(500)
        }
    }.flowOn(Dispatchers.Default)

    override suspend fun pause() {
        positionTrackingJob?.cancel()
        if (audioPlayer.isPlaying) audioPlayer.pause()
    }

    override suspend fun rewind() {
        val newPosition = (audioPlayer.currentPosition - 5000).coerceAtLeast(0)
        audioPlayer.seekTo(newPosition)
    }

    override suspend fun fastForward() {
        val newPosition = (audioPlayer.currentPosition + 5000).coerceAtMost(audioPlayer.duration)
        audioPlayer.seekTo(newPosition)
    }

    override suspend fun getNextSpeed(current: AudioSpeed): AudioSpeed {
        val new = current.next()
        if (audioPlayer.isPlaying)
            audioPlayer.playbackParams = audioPlayer.playbackParams.setSpeed(new.speed)
        return new
    }
}