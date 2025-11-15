package com.bigappfactory.testapp.presentation.composable.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.bigappfactory.testapp.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import testapp.composeapp.generated.resources.Res
import testapp.composeapp.generated.resources.compose_multiplatform

@Composable
fun AvatarBubble(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.compose_multiplatform),
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(AppTheme.dimensions.iconSize)
            .clip(CircleShape)
            .border(AppTheme.dimensions.borderStroke, Color.White, CircleShape)
    )
}
