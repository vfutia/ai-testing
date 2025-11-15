package com.bigappfactory.testapp.presentation.composable.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bigappfactory.testapp.presentation.theme.AppTheme

@Composable
fun FilterBar(
    title: String,
    filters: List<String>,
    selectedFilters: List<String>,
    onFilterClick: (String) -> Unit
) {
    Text(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .padding(start = AppTheme.dimensions.paddingLarge),
        text = title,
        color = AppTheme.colors.onPrimary,
        style = AppTheme.typography.headlineLarge
    )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Spacer(Modifier.width(AppTheme.dimensions.paddingLarge))
        filters.forEach { filter ->
            val isSelected = selectedFilters.contains(filter)
            Button(
                onClick = { onFilterClick(filter) },
                modifier = Modifier.animateContentSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color.White else AppTheme.colors.primary,
                    contentColor = if (isSelected) AppTheme.colors.background else Color.White
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = expandHorizontally(expandFrom = Alignment.Start),
                        exit = shrinkHorizontally(shrinkTowards = Alignment.Start)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                modifier = Modifier.size(16.dp),
                                tint = AppTheme.colors.background
                            )
                            Spacer(Modifier.width(4.dp))
                        }
                    }
                    Text(filter)
                }
            }
        }
    }
}
