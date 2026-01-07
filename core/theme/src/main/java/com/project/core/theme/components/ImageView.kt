package com.project.core.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.project.core.theme.Dimens
import com.project.core.theme.Shapes
import com.project.core.theme.previews.PreviewScreenContent
import com.project.core.theme.previews.ScreenPreview
import com.project.essentials.entities.ImageSource

@Composable
fun ImageView(
    imageSource: ImageSource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    when (imageSource) {
        ImageSource.Empty -> EmptyImageView(
            modifier = modifier,
            contentDescription = contentDescription
        )
        is ImageSource.Remote -> {
            RemoteImageView(
                url = imageSource.url,
                modifier = modifier,
                contentDescription = contentDescription
            )
        }
        is ImageSource.Local -> {
            LocalImageView(
                path = imageSource.path,
                modifier = modifier,
                contentDescription = contentDescription
            )
        }
        is ImageSource.DrawableRes -> {
            DrawableResImageView(
                resId = imageSource.resId,
                modifier = modifier,
                contentDescription = contentDescription
            )
        }
    }
}


@Composable
fun EmptyImageView(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        imageVector = Icons.Default.AccountBox,
        contentDescription = contentDescription,
        modifier = modifier
            .size(250.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = Shapes.LargeRoundedCornerShape
            )
    )
}

@Composable
fun RemoteImageView(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {

    SubcomposeAsyncImage(
        model = url,
        modifier = modifier,
        contentDescription = contentDescription,
        error = {
            EmptyImageView(
                modifier = modifier.matchParentSize(),
                contentDescription = contentDescription
            )
        }
    )
}

@Composable
fun LocalImageView(
    path: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AsyncImage(
        model = path,
        modifier = modifier,
        contentDescription = contentDescription
    )
}

@Composable
fun DrawableResImageView(
    resId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@ScreenPreview
@Composable
private fun EmptyImageViewPreview() {
    PreviewScreenContent {
        ImageView(
            imageSource = ImageSource.Empty,
            modifier = Modifier.size(Dimens.LargeImageSize)
        )
    }
}