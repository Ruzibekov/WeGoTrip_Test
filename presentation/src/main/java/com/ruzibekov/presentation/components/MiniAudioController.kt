package com.ruzibekov.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@Composable
fun MiniAudioController(onOptionsClick: () -> Unit) {
    Column(
        modifier = Modifier.height(Constants.MINI_CONTROLLER_HEIGHT.dp),
    ) {
        LinearProgressIndicator(progress = { 0.4f })

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onOptionsClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_options_vertical),
                    contentDescription = "vertical options button",
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pause),
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
    WeGoTrip_TestTheme { MiniAudioController({}) }
}