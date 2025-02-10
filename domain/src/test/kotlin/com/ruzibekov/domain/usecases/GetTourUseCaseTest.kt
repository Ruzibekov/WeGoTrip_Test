package com.ruzibekov.domain.usecases

import com.ruzibekov.domain.model.Tour
import com.ruzibekov.domain.model.TourStep
import com.ruzibekov.domain.repository.TourRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetTourUseCaseTest {

    @Mock
    private lateinit var repository: TourRepository

    private lateinit var useCase: GetTourUseCase

    @Before
    fun setup() {
        useCase = GetTourUseCase(repository)
    }

    @Test
    fun `should return tour data from repository`() = runTest {
        val expectedTour = Tour(
            title = "Test Tour",
            steps = listOf(
                TourStep(
                    title = "Step 1",
                    description = "Description",
                    images = listOf("url1", "url2"),
                    audio = 123
                )
            )
        )
        whenever(repository.getTour()).thenReturn(expectedTour)

        val result = useCase()

        assertEquals(expectedTour, result)
        verify(repository).getTour()
    }

    @Test
    fun `should handle repository error`() = runTest {
        whenever(repository.getTour()).thenThrow(RuntimeException("Network error"))

        assertThrows<RuntimeException> { useCase() }
    }
}