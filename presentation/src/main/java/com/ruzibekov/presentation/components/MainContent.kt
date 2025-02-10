package com.ruzibekov.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ruzibekov.domain.constatns.Constants
import com.ruzibekov.domain.model.TourStep
import com.ruzibekov.presentation.extension.rememberNavigationBarHeight
import com.ruzibekov.presentation.screens.main.MainAction


private val baseModifier = Modifier.padding(horizontal = 16.dp)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(tour: TourStep, sendAction: (MainAction) -> Unit) {
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Step 3/10",
            modifier = baseModifier
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = tour.title,
            modifier = baseModifier,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        val pagerState = rememberPagerState { tour.images.size }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            HorizontalPager(state = pagerState) { page ->
                AsyncImage(
                    model = tour.images[page],
                    contentDescription = "tour image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Surface(
                modifier = Modifier.padding(20.dp),
                shape = RoundedCornerShape(20.dp),
                color = Color.Black.copy(alpha = 0.5f)
            ) {
                Text(
                    text = "${pagerState.currentPage+1}/${tour.images.size}",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        ScrollablePageIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = tour.description,
            modifier = baseModifier
        )

        val navigationHeight = rememberNavigationBarHeight()

        Spacer(modifier = Modifier.height(30.dp + Constants.MINI_CONTROLLER_HEIGHT.dp + navigationHeight))
    }
}