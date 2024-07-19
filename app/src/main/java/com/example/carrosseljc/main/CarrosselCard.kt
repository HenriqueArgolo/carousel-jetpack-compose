package com.example.carrosseljc.main


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarrosselCard(
    imageList: List<String>
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 2)
    val images = imageList
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    //Card com as imagens ----------------------------------------------------
        HorizontalPager(
            count = imageList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 85.dp),
            modifier = Modifier.height(350.dp)
        ) { page ->
            Card(shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        val scale = lerp(
                            start = 0.80f,
                            stop = 1.2f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1.2f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageList[page])
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null
                )
            }
        }
        //Círculos ----------------------------------------
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.Center){
            repeat(imageList.size){
                it ->
                val color = if(pagerState.currentPage == it) Color.DarkGray else Color.LightGray
                val size = if(pagerState.currentPage == it) 18.dp else 15.dp
                Box(
                    modifier =Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .size(15.dp)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                )
            }
        }
        // Botões -----------------------------------------
        Spacer(modifier = Modifier.height(40.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            IconButton(
                enabled = pagerState.currentPage > 0,
                onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage-1)
                }
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)

            }
            Spacer(modifier = Modifier.width(60.dp))
            IconButton(
                enabled = pagerState.currentPage < pagerState.pageCount-1,
                onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }
            }) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)

            }
        }

    }
}


