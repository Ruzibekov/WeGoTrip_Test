package com.ruzibekov.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ruzibekov.presentation.R
import com.ruzibekov.presentation.theme.WeGoTripColors

@Composable
fun CloseButton(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close icon",
            modifier = Modifier.padding(8.dp).size(24.dp)
        )
    }
}

@Composable
fun OptionsButton(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_option_horizontal),
            contentDescription = "close icon",
            modifier = Modifier.padding(8.dp).size(24.dp),
            tint = WeGoTripColors.Primary
        )
    }
}