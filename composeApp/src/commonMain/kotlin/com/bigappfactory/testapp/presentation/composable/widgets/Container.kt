package com.bigappfactory.testapp.presentation.composable.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bigappfactory.testapp.presentation.theme.AppTheme

@Composable
fun Container(
    modifier: Modifier = Modifier,
    isClosable: Boolean = false,
    isExpandable: Boolean = false,
    content: @Composable () -> Unit = { }
) {
    var isExpanded by remember { mutableStateOf(!isExpandable) }
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = modifier
                .background(
                    color = AppTheme.colors.primary,
                    shape = RoundedCornerShape(AppTheme.dimensions.paddingLarge)
                )
                .padding(AppTheme.dimensions.paddingLarge)
                .animateContentSize(animationSpec = tween(durationMillis = 300))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1.0f),
                    text = "Title",
                    style = AppTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                if (isClosable) {
                    Box {
                        RoundIconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            icon = Icons.Default.Close,
                            onClick = { isVisible = false },
                            useSmallSize = true
                        )
                    }
                }

                if (isExpandable) {
                    Box {
                        RoundIconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            onClick = { isExpanded = !isExpanded },
                            useSmallSize = true
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300)) + shrinkVertically(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        }
    }
}
