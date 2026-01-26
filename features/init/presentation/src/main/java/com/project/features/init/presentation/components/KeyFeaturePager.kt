package com.project.features.init.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.core.theme.Dimens
import com.project.core.theme.FontSize
import com.project.core.theme.MediumVerticalSpace
import com.project.core.theme.components.ImageView
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.essentials.entities.ImageSource
import com.project.features.init.domain.entities.KeyFeature
import com.project.features.init.presentation.R
import java.time.ZonedDateTime

@Composable
fun KeyFeaturePager(
    keyFeatures: List<KeyFeature>,
    onLetsGoAction: () -> Unit,
    currentPage: Int,
    onPageChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    configuration: Configuration = LocalConfiguration.current
) {

    val pagerState = rememberPagerState(
      initialPage = currentPage,
      pageCount = {
            keyFeatures.size
      }
    )

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
       KeyFeaturesLandscapePager(
           modifier = modifier,
           keyFeatures = keyFeatures,
           pagerState = pagerState,
           onLetsGoAction = onLetsGoAction,
           onPageChanged = onPageChanged
       )
    } else {
        KeyFeaturePortraitPager(
            modifier = modifier,
            keyFeatures = keyFeatures,
            pagerState = pagerState,
            onLetsGoAction = onLetsGoAction,
            onPageChanged = onPageChanged
        )
    }


}

@Composable
fun KeyFeaturePortraitPager(
    keyFeatures: List<KeyFeature>,
    pagerState: PagerState,
    onLetsGoAction: () -> Unit,
    onPageChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->

        val feature = keyFeatures[page]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.MediumPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = feature.title,
                fontSize = FontSize.LargeFontSize
            )
            MediumVerticalSpace()
            ImageView(
                imageSource = feature.image,
                modifier = Modifier.size(Dimens.LargeImageSize)
            )
            MediumVerticalSpace()
            Text(
                text = feature.description,
                textAlign = TextAlign.Center,
                fontSize = FontSize.MediumFontSize
            )
            MediumVerticalSpace()
            PagerIndicator(
                pageCount = keyFeatures.size,
                currentPage = pagerState.currentPage
            )
            MediumVerticalSpace()
            if(pagerState.currentPage == keyFeatures.lastIndex) {
                PagerButton(onLetsGoAction = onLetsGoAction)
            }
        }
    }
}

@Composable
fun KeyFeaturesLandscapePager(
    keyFeatures: List<KeyFeature>,
    pagerState: PagerState,
    onLetsGoAction: () -> Unit,
    onPageChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->

        val feature = keyFeatures[page]

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ImageView(
                    imageSource = feature.image,
                    modifier = Modifier.size(Dimens.LargeImageSize)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(Dimens.MediumPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = feature.title,
                        fontSize = FontSize.LargeFontSize
                    )
                    MediumVerticalSpace()
                    Text(
                        text = feature.description,
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.MediumFontSize
                    )
                    MediumVerticalSpace()
                    if(pagerState.currentPage == keyFeatures.lastIndex) {
                        PagerButton(onLetsGoAction = onLetsGoAction)
                    }
                }
            }
            MediumVerticalSpace()
            PagerIndicator(
                pageCount = keyFeatures.size,
                currentPage = pagerState.currentPage
            )
        }
    }
}

@Composable
fun PagerButton(
    onLetsGoAction: () -> Unit
) {
    Button(
        onClick = onLetsGoAction,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(text = stringResource(R.string.init_let_s_go))
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primaryContainer,
    inactiveColor: Color = activeColor.copy(alpha = 0.3f)
) {


    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
           repeat(pageCount) { index ->
               Box(
                   modifier = Modifier
                       .padding(4.dp)
                       .size(if (index == currentPage) 10.dp else 8.dp)
                       .clip(CircleShape)
                       .background(if (index == currentPage) activeColor else inactiveColor)
               )
           }
    }

}

@ScreenPreview
@Composable
private fun KeyFeaturePagerPreview() {
    PreviewScreenContent {
        KeyFeaturePager(
            keyFeatures = listOf(
                KeyFeature(
                    id = 1L,
                    title = "Feature 1",
                    description = "Description for feature 1",
                    image = ImageSource.DrawableRes(R.drawable.feature_ai),
                    lastDisplayTime = ZonedDateTime.now()
                ),
                KeyFeature(
                    id = 2L,
                    title = "Feature 2",
                    description = "Description for feature 2",
                    image = ImageSource.DrawableRes(R.drawable.feature_smart),
                    lastDisplayTime = ZonedDateTime.now()
                ),
                KeyFeature(
                    id = 2L,
                    title = "Feature 2",
                    description = "Description for feature 2",
                    image = ImageSource.DrawableRes(R.drawable.feature_answers),
                    lastDisplayTime = ZonedDateTime.now()
                )
            ),
            onLetsGoAction = {},
            currentPage = 1,
            onPageChanged = {}
        )
    }

}