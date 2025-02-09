package com.ruzibekov.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomBarContent(sheetState: SheetState) {
    val scope = rememberCoroutineScope()

    Crossfade(
        targetState = sheetState.targetValue,
        modifier = Modifier.fillMaxSize()
    ) { state ->
        if (state == SheetValue.Expanded)
            MainAudioController(
                title = "Русский музей-импрессионисты",
                currentPosition = 0.45f,
                duration = "0:30",
                isPlaying = false,
                onHideClick = {
                    scope.launch { sheetState.partialExpand() }
                },
                onSliderValueChange = {}
            )
        else
            MiniAudioController(onOptionsClick = {
                scope.launch { sheetState.expand() }
            })
    }
}