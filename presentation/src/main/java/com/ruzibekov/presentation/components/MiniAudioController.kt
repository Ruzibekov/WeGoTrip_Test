package com.ruzibekov.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruzibekov.domain.constatns.Constants
import com.ruzibekov.presentation.R
import com.ruzibekov.presentation.theme.WeGoTripColors
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@Composable
fun MiniAudioController(
    isPlaying: Boolean,
    onOptionsClick: () -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(Constants.MINI_CONTROLLER_HEIGHT.dp)
            .clickable(onClick = onOptionsClick),
    ) {
        LinearProgressIndicator(
            progress = { 0.1f },
            modifier = Modifier.fillMaxWidth(),
            trackColor = WeGoTripColors.Stroke,
            color = WeGoTripColors.Primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_options_vertical),
                contentDescription = "vertical options button",
                modifier = Modifier.size(24.dp)
            )

            IconButton(
                onClick = if(isPlaying) onPauseClick else onPlayClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                    contentDescription = "vertical options button",
                )
            }

            Text(
                text = "Long text Long text Long text Long text Long text ",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_rewind),
                    contentDescription = "vertical options button",
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_audio_speed),
                    contentDescription = "vertical options button",
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_rewind),
                    contentDescription = "vertical options button",
                    modifier = Modifier.rotate(180f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MiniAudioControllerPreview() {
    WeGoTrip_TestTheme { MiniAudioController(true, {}, {}, {}) }
}