package com.ruzibekov.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ruzibekov.domain.model.TourStep
import com.ruzibekov.presentation.screens.main.MainAction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomBarContent(
    sheetState: SheetState,
    tour: TourStep,
    isPlaying: Boolean,
    sendAction: (MainAction) -> Unit
) {
    val scope = rememberCoroutineScope()

    Crossfade(
        targetState = sheetState.targetValue,
        modifier = Modifier.fillMaxSize()
    ) { sheetValue ->
        if (sheetValue == SheetValue.Expanded)
            MainAudioController(
                title = "Russkiy Muzey",
                currentPosition = 0.45f,
                duration = "0:30",
                isPlaying = false,
                onHideClick = {
                    scope.launch { sheetState.partialExpand() }
                },
                onSliderValueChange = {}
            )
        else
            MiniAudioController(
                isPlaying = isPlaying,
                onOptionsClick = {
                    scope.launch { sheetState.expand() }
                },
                onPlayClick = {
                    sendAction(MainAction.OnPlayClick(tour.audio))
                },
                onPauseClick = {
                    sendAction(MainAction.OnPauseClick)
                }
            )
    }
}