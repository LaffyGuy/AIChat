package com.project.navigation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import com.project.navigation.TOP_LEVEL_DESTINATION

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {

    BottomAppBar(
        modifier = modifier
    ) {
        TOP_LEVEL_DESTINATION.forEach { (topLevelDestination, data) ->
            NavigationBarItem(
                selected = topLevelDestination == selectedKey,
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = data.icon,
                        contentDescription = stringResource(data.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(data.title)
                    )
                }
            )
        }
    }

}