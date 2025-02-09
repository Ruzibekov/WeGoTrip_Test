package com.ruzibekov.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

@Composable
fun ErrorScreen(message: String) {

}

@Preview
@Composable
private fun ErrorScreenPreview() {
    WeGoTrip_TestTheme { ErrorScreen("Not found 404") }
}