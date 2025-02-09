package com.ruzibekov.presentation.screens.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruzibekov.domain.mock.MockData
import com.ruzibekov.presentation.components.CloseButton
import com.ruzibekov.presentation.components.ErrorScreen
import com.ruzibekov.presentation.components.LoadingScreen
import com.ruzibekov.presentation.components.MainAudioController
import com.ruzibekov.presentation.components.MiniAudioController
import com.ruzibekov.presentation.components.OptionsButton
import com.ruzibekov.presentation.components.ProgressBar
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            MainState.Loading -> LoadingScreen()
            is MainState.Error -> ErrorScreen((state as MainState.Error).message)
            is MainState.Success -> MainContent(state, viewModel::sendAction)
        }
    }
}

private val baseModifier = Modifier.padding(horizontal = 16.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(state: MainState, sendAction: (MainAudioAction) -> Unit) {
    val scope = rememberCoroutineScope()
    val data = state as MainState.Success
    val sheetState1 = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)

    val density = LocalDensity.current
    val sheetState =
        rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    val navigationBarHeight =
        with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }
    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetContent = {
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
                    MiniAudioController()
            }
        },
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(0.dp),
        sheetPeekHeight = 60.dp + navigationBarHeight
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            ProgressBar(0.2f)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = baseModifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CloseButton {}

                OptionsButton {}
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Step 3/10",
                modifier = baseModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    WeGoTrip_TestTheme {
        MainContent(
            state = MainState.Success(
                tour = MockData().tour,
                currentStep = MockData().tourStep,
                isPlaying = false,
                audioProgress = 0f,
                totalSteps = 5
            )
        ) {}
    }
}