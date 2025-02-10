package com.ruzibekov.data.repository

import android.content.Context
import android.media.MediaPlayer
import com.ruzibekov.domain.repository.AudioRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioRepositoryImpl @Inject constructor(
    private val audioPlayer: MediaPlayer,
    @ApplicationContext private val context: Context
) : AudioRepository {

    private var currentResourceId: Int? = null

    override suspend fun playAudio(resourceId: Int) = withContext(Dispatchers.IO) {
        if (currentResourceId != resourceId) {
            currentResourceId = resourceId
            audioPlayer.reset()

            context.resources.openRawResourceFd(resourceId)?.use { fd ->
                audioPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            }

            audioPlayer.prepare()
            audioPlayer.start()
        } else
            audioPlayer.start()
    }

    override suspend fun pauseAudio() {
        if (audioPlayer.isPlaying) audioPlayer.pause()
    }
}