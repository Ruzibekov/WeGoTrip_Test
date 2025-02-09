package com.ruzibekov.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ruzibekov.presentation.theme.WeGoTripColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollablePageIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val indicatorScrollState = rememberLazyListState()

    LaunchedEffect(key1 = pagerState.currentPage){
        val currentPage = pagerState.currentPage
        val size = indicatorScrollState.layoutInfo.visibleItemsInfo.size
        val lastVisibleIndex = indicatorScrollState.layoutInfo.visibleItemsInfo.last().index
        val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

        if (currentPage > lastVisibleIndex - 1)
            indicatorScrollState.animateScrollToItem(currentPage - size + 2)
        else if (currentPage <= firstVisibleItemIndex + 1)
            indicatorScrollState.animateScrollToItem((currentPage - 1).coerceAtLeast(0))
    }

    LazyRow(
        state = indicatorScrollState,
        modifier = modifier.width(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val color = if (pagerState.currentPage == iteration)
                WeGoTripColors.Primary
            else
                Color.LightGray

            item(key = "item$iteration") {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .background(color, CircleShape)
                        .size(6.dp)
                )
            }
        }
    }
}