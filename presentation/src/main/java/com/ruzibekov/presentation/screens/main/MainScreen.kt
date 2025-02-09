package com.ruzibekov.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruzibekov.domain.constatns.Constants
import com.ruzibekov.domain.mock.MockData
import com.ruzibekov.presentation.components.ErrorScreen
import com.ruzibekov.presentation.components.LoadingScreen
import com.ruzibekov.presentation.components.MainBottomBarContent
import com.ruzibekov.presentation.components.MainContent
import com.ruzibekov.presentation.extension.rememberNavigationBarHeight
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            MainState.Loading -> LoadingScreen()
            is MainState.Error -> ErrorScreen((state as MainState.Error).message)
            is MainState.Success -> Content(state, viewModel::sendAction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(state: MainState, sendAction: (MainAudioAction) -> Unit) {
    val data = state as MainState.Success
    val sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    val navigationHeight = rememberNavigationBarHeight()

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetContent = {
            MainBottomBarContent(sheetState)
        },
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(0.dp),
        sheetPeekHeight = Constants.MINI_CONTROLLER_HEIGHT.dp + navigationHeight,
        sheetContainerColor = Color.White,
        containerColor = Color.White
    ) {
        MainContent(data.tour.steps[0], sendAction)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    WeGoTrip_TestTheme {
        Content(
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