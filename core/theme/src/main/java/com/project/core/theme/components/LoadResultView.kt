package com.project.core.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core.theme.MediumVerticalSpace
import com.project.essentials.LoadResult
import com.project.essentials.exceptions.ConnectionException
import com.project.essentials.exceptions.ExceptionToMessageMapper

@Composable
fun <T> LoadResultView(
    loadResult: LoadResult<T>,
    content: @Composable (T) -> Unit,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    onTryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (loadResult) {
            LoadResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }

            is LoadResult.Success -> {
                content(loadResult.data)
            }

            is LoadResult.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val errorMessage =
                        exceptionToMessageMapper.getLocalizedMessage(loadResult.exception)
                    Text(
                        text = errorMessage,
                        fontSize = 24.sp
                    )
                    MediumVerticalSpace()
                    Button(
                        onClick = onTryAgain
                    ) {
                        Text(text = "Try again")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadLoadResultView() {
    LoadResultView(
        loadResult = LoadResult.Loading,
        onTryAgain = {},
        content = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun SuccessLoadResultView() {
    LoadResultView(
        loadResult = LoadResult.Success("Hello"),
        onTryAgain = {},
        content = { value ->
            Text(text = value)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorLoadResultView() {
    LoadResultView(
        loadResult = LoadResult.Error(ConnectionException()),
        onTryAgain = {},
        content = {}
    )

}