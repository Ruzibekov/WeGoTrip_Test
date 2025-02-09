package com.ruzibekov.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        TopAppBar(
//            title = {
//                Text("ШАГ ${state.currentStep}/${state.totalSteps}")
//            },
//            navigationIcon = {
//                IconButton(onClick = { /* навигация назад */ }) {
//                    Icon(Icons.Default.Close, contentDescription = "Закрыть")
//                }
//            },
//            actions = {
//                IconButton(onClick = { /* меню */ }) {
//                    Icon(Icons.Default.MoreVert, contentDescription = "Меню")
//                }
//            }
//        )
//
//        if (state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//        }
//
//        Text(
//            text = state.title,
//            style = MaterialTheme.typography.h4,
//            modifier = Modifier.padding(vertical = 16.dp)
//        )
//
//        Text(
//            text = state.description,
//            style = MaterialTheme.typography.bodyMedium
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(state.totalSteps) { step ->
//                Box(
//                    modifier = Modifier
//                        .size(8.dp)
//                        .padding(horizontal = 4.dp)
//                        .background(
//                            if (step == state.currentStep - 1)
//                                MaterialTheme.colorScheme.primary
//                            else
//                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
//                            CircleShape
//                        )
//                )
//            }
//        }
//    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    WeGoTrip_TestTheme {
        MainScreen()
    }
}