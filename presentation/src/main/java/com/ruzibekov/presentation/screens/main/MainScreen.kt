package com.ruzibekov.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ruzibekov.presentation.components.ErrorScreen
import com.ruzibekov.presentation.components.LoadingScreen
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tour") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.sendAction(MainAction.OnBackClick)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                MainState.Loading -> LoadingScreen()
                is MainState.Error -> ErrorScreen((state as MainState.Error).message)
                is MainState.Success -> Content(state, viewModel::sendAction)
            }
        }
    }
}

@Composable
fun Content(state: MainState, sendAction: (MainAction) -> Unit) {
    val content = state as MainState.Success

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "STEP ${content.currentStep}/${content.totalSteps}",
            modifier = Modifier.padding(16.dp)
        )

        // Image
        AsyncImage(
            model = content.currentStep.images,
            contentDescription = content.currentStep.title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Audio controls
        AudioControls(
            isPlaying = content.isPlaying,
            progress = content.audioProgress,
            onPlayClick = { sendAction(MainAction.OnPlayClick) },
            onSeek = { sendAction(MainAction.OnSeekAudio(it)) }
        )
    }
}

@Composable
fun AudioControls(
    isPlaying: Boolean,
    progress: Float,
    onPlayClick: () -> Unit,
    onSeek: (Float) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPlayClick) {
            Icon(
                if (isPlaying) Icons.Default.PlayArrow else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play"
            )
        }

        Slider(
            value = progress,
            onValueChange = onSeek,
            modifier = Modifier.weight(1f)
        )

        Text(text = formatTime(progress))
    }
}

private fun formatTime(seconds: Float): String {
    val minutes = (seconds / 60).toInt()
    val remainingSeconds = (seconds % 60).toInt()
    return "%d:%02d".format(minutes, remainingSeconds)
}

@Preview
@Composable
private fun MainScreenPreview() {
    WeGoTrip_TestTheme {
        MainScreen()
    }
}