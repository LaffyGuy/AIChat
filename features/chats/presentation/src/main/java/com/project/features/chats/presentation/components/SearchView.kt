package com.project.features.chats.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.features.chats.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                color = Color.Gray.copy(alpha = 0.5f)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Gray.copy(alpha = 0.1f)
        )

    )

}

@Preview(showSystemUi = true)
@Composable
fun SearchViewPreview() {
    SearchView(
        query = "Hello",
        onQueryChange = {}
    )
}