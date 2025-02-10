package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.AudioSpeed
import com.ruzibekov.domain.repository.AudioRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AudioUseCasesTest {

    @Mock
    private lateinit var repository: AudioRepository

    private lateinit var initializeUseCase: InitializeMediaPlayerUseCase
    private lateinit var playUseCase: PlayAudioUseCase
    private lateinit var pauseUseCase: PauseAudioUseCase
    private lateinit var changeSpeedUseCase: ChangeAudioSpeedUseCase

    @Before
    fun setup() {
        initializeUseCase = InitializeMediaPlayerUseCase(repository)
        playUseCase = PlayAudioUseCase(repository)
        pauseUseCase = PauseAudioUseCase(repository)
        changeSpeedUseCase = ChangeAudioSpeedUseCase(repository)
    }

    @Test
    fun `initialize should call repository and invoke callbacks`() = runTest {
        val resourceId = 123
        val onDuration: (Int) -> Unit = mock()
        val onCompletion: () -> Unit = mock()

        initializeUseCase(resourceId, onCompletion, onDuration)

        verify(repository).initial(resourceId, onDuration, onCompletion)
    }

    @Test
    fun `play should start playback with correct speed`() = runTest {
        val speed = AudioSpeed.NORMAL
        val onPositionChange: (Int) -> Unit = mock()

        playUseCase(speed, onPositionChange)

        verify(repository).play(speed, onPositionChange)
    }

    @Test
    fun `pause should stop playback`() = runTest {
        pauseUseCase()

        verify(repository).pause()
    }

    @Test
    fun `change speed should return next speed value`() = runTest {
        val currentSpeed = AudioSpeed.NORMAL
        val expectedSpeed = AudioSpeed.FAST
        whenever(repository.getNextSpeed(currentSpeed)).thenReturn(expectedSpeed)

        val result = changeSpeedUseCase(currentSpeed)

        assertEquals(expectedSpeed, result)
    }
}