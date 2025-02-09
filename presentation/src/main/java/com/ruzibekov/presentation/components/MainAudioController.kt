package com.ruzibekov.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruzibekov.presentation.R
import com.ruzibekov.presentation.theme.WeGoTripColors

@Composable
fun MainAudioController(
    title: String,
    currentPosition: Float,
    duration: String,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    onSliderValueChange: (Float) -> Unit,
    onHideClick: () -> Unit
) {
    var show by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            title, !show, onHideClick, onMenuClick = {
                show = !show
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(show) {
            if (!it)
                MainAudioContent1(
                    currentPosition,
                    duration,
                    isPlaying,
                    modifier,
                    onSliderValueChange,
                    onHideClick
                )
            else
                MainAudioContent2()
        }
    }
}

@Composable
fun MainAudioContent1(
    currentPosition: Float,
    duration: String,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    onSliderValueChange: (Float) -> Unit,
    onHideClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Slider(
            value = currentPosition,
            onValueChange = onSliderValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentPosition),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = duration,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Rewind 10 seconds"
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Delete else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Forward 10 seconds"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text Long text "
        )
    }
}

@Composable
fun MainAudioContent2() {

}

@Composable
private fun TopBar(
    title: String,
    showMenu: Boolean,
    onHideClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = onHideClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = "Rewind 10 seconds"
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.audio_tour),
                fontSize = 12.sp,
                color = WeGoTripColors.Gray,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = onMenuClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(if (showMenu) R.drawable.ic_menu else R.drawable.ic_close),
                contentDescription = "Rewind 10 seconds"
            )
        }
    }
}

private fun formatTime(position: Float): String {
    val totalSeconds = (position * 30).toInt() // Assuming 30 seconds total duration
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}