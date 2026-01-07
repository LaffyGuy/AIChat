package com.project.features.init.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.project.core.theme.Dimens
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
    modifier: Modifier = Modifier,
    configuration: Configuration = LocalConfiguration.current
) {

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
       KeyFeaturesLandscapePager(
           modifier = modifier,
           keyFeatures = keyFeatures,
           onLetsGoAction = onLetsGoAction
       )
    } else {
        KeyFeaturePortraitPager(
            modifier = modifier,
            keyFeatures = keyFeatures,
            onLetsGoAction = onLetsGoAction
        )
    }


}

@Composable
fun KeyFeaturePortraitPager(
    keyFeatures: List<KeyFeature>,
    onLetsGoAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            keyFeatures.size
        }
    )

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
            Text(text = feature.title)
            MediumVerticalSpace()
            ImageView(
                imageSource = feature.image,
                modifier = Modifier.size(Dimens.LargePadding)
            )
            MediumVerticalSpace()
            Text(
                text = feature.description,
                textAlign = TextAlign.Center
            )
            if(pagerState.currentPage == keyFeatures.lastIndex) {
                Button(
                    onClick = onLetsGoAction
                ) {
                    Text(text = stringResource(R.string.let_s_go))
                }
            }
        }
    }
}

@Composable
fun KeyFeaturesLandscapePager(
    keyFeatures: List<KeyFeature>,
    onLetsGoAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            keyFeatures.size
        }
    )

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState
    ) { page ->

        val feature = keyFeatures[page]

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ImageView(
                imageSource = feature.image,
                modifier = Modifier.size(Dimens.LargeImageSize)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.MediumPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = feature.title)
                MediumVerticalSpace()
                Text(
                    text = feature.description,
                    textAlign = TextAlign.Center
                )
                MediumVerticalSpace()
                if(pagerState.currentPage == keyFeatures.lastIndex) {
                    Button(
                        onClick = onLetsGoAction
                    ) {
                        Text(text = stringResource(R.string.let_s_go))
                    }
                }
            }
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
            onLetsGoAction = {}
        )
    }

}