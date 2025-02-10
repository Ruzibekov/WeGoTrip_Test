package com.ruzibekov.presentation.screens.main

import androidx.compose.animation.Crossfade
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruzibekov.domain.constatns.Constants
import com.ruzibekov.presentation.components.ErrorScreen
import com.ruzibekov.presentation.components.LoadingScreen
import com.ruzibekov.presentation.components.MainBottomContent
import com.ruzibekov.presentation.components.MainContent
import com.ruzibekov.presentation.components.MainMiniBottomContent
import com.ruzibekov.presentation.extension.rememberNavigationBarHeight
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.tour == null && state.error == null -> {
                LoadingScreen()
            }

            state.error != null -> {
                ErrorScreen(state.error ?: "")
            }

            else -> {
                Content(state, viewModel::sendAction)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(state: MainState, sendAction: (MainAction) -> Unit) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    val navigationHeight = rememberNavigationBarHeight()

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetContent = {
            Crossfade(
                targetState = sheetState.targetValue,
                modifier = Modifier.fillMaxSize()
            ) { sheetValue ->
                if (sheetValue == SheetValue.Expanded)
                    MainBottomContent(state, sendAction, onHideClick = {
                        scope.launch { sheetState.partialExpand() }
                    })
                else
                    MainMiniBottomContent(state, sendAction, onOptionsClick = {
                        scope.launch { sheetState.expand() }
                    })
            }
        },
        sheetDragHandle = {},
        sheetShape = RoundedCornerShape(0.dp),
        sheetPeekHeight = Constants.MINI_CONTROLLER_HEIGHT.dp + navigationHeight,
        sheetContainerColor = Color.White,
        containerColor = Color.White
    ) {
        MainContent(state, sendAction)
    }
}