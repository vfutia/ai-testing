package com.bigappfactory.testapp.presentation.composable.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bigappfactory.testapp.presentation.theme.AppTheme
import com.bigappfactory.testapp.presentation.theme.mediumText

@Composable
fun AccountBadge() {
    Row (verticalAlignment = Alignment.CenterVertically) {
        AvatarBubble(
            modifier = Modifier
                .padding(
                    start = AppTheme.dimensions.paddingLarge,
                    end = AppTheme.dimensions.padding
                )
        )

        Column {
            Text(
                text = "Bob Smith",
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.onPrimary,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "Some Guy",
                style = AppTheme.typography.bodySmall,
                color = mediumText
            )
        }
    }
}