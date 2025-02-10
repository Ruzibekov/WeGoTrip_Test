package com.ruzibekov.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruzibekov.presentation.R
import com.ruzibekov.presentation.extension.formatSpeed
import com.ruzibekov.presentation.extension.formatToTime
import com.ruzibekov.presentation.screens.main.MainAction
import com.ruzibekov.presentation.screens.main.MainState
import com.ruzibekov.presentation.theme.WeGoTripColors
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainBottomContent(
    state: MainState,
    sendAction: (MainAction) -> Unit,
    onHideClick: () -> Unit
) {
    val tour = state.tour!!
    var show by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            title = tour.title,
            showMenu = !show,
            onHideClick = onHideClick,
            onMenuClick = { show = !show }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(show) {
            if (!it)
                MainAudioContentPlayer(state, sendAction)
            else
                MainAudioContent2()
        }
    }
}

private var positionChangeJob: Job? = null

@Composable
fun MainAudioContentPlayer(
    state: MainState,
    sendAction: (MainAction) -> Unit
) {
    val scope = rememberCoroutineScope()
    val tour = state.tour!!
    Column(modifier = Modifier.fillMaxSize()) {

        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {

                Text(
                    text = tour.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                var position by remember {
                    mutableFloatStateOf(state.getPositionForSlider())
                }

                LaunchedEffect(state) {
                    position = if (positionChangeJob?.isActive == true) position else state.getPositionForSlider()
                }

                Slider(
                    value = position,
                    onValueChange = {
                        position = it
                        positionChangeJob?.cancel()
                        positionChangeJob = scope.launch {
                            delay(100)
                            sendAction(MainAction.ChangePosition(it))
                        }
                    },
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
                        text = (position * state.durationInMillis).toInt().formatToTime(),
                        fontSize = 12.sp
                    )
                    Text(
                        text = state.durationInMillis.formatToTime(),
                        fontSize = 12.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(modifier = Modifier.size(48.dp, 24.dp)) //start padding

                    IconButton(
                        onClick = {
                            sendAction(MainAction.OnRewindAudioClick)
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_rewind),
                            contentDescription = "Forward 10 seconds",
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Surface(
                        onClick = {
                            sendAction(
                                if (state.isPlaying)
                                    MainAction.OnPauseClick
                                else
                                    MainAction.OnPlayClick(tour.audio)
                            )
                        },
                        color = WeGoTripColors.Primary,
                        shape = CircleShape
                    ) {
                        Icon(
                            painter = painterResource(if (state.isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                            contentDescription = if (state.isPlaying) "Pause" else "Play",
                            modifier = Modifier
                                .padding(13.dp)
                                .offset(x = if (state.isPlaying) 0.dp else 3.dp)
                                .size(48.dp),
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            sendAction(MainAction.OnFastForwardClick)
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_rewind),
                            contentDescription = "Forward 10 seconds",
                            modifier = Modifier
                                .size(48.dp)
                                .rotate(180f)
                        )
                    }

                    Surface(
                        onClick = {
                            sendAction(MainAction.ChangeSpeed(state.audioSpeed))
                        },
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "${state.audioSpeed.speed.formatSpeed()}x",
                            modifier = Modifier.size(48.dp, 24.dp),
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = tour.description,
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

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.audio_tour),
                fontSize = 12.sp,
                color = WeGoTripColors.Gray,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = title,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
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