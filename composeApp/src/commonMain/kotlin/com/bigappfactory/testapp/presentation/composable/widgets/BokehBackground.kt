package com.bigappfactory.testapp.presentation.composable.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

/**
 * Bokeh-like background composed of semi-transparent radial gradients (soft circles).
 *
 * @param modifier Modifier applied to the container (typically fillMaxSize()).
 * @param backgroundColor The solid background color (default #022345).
 * @param blobColor Base color for the bokeh blobs (default semi-transparent white).
 * @param seed Deterministic seed for blob positions so you get the same pattern each run.
 * @param bigBlobCount Number of large/broad blobs.
 * @param smallBlobCount Number of smaller/bright blobs.
 */
@Composable
fun BokehBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF022345),
    blobColor: Color = Color.White.copy(alpha = 0.12f),
    seed: Int = 42,
    bigBlobCount: Int = 8,
    smallBlobCount: Int = 18
) {
    val rememberedBlobs = remember(seed, bigBlobCount, smallBlobCount) {
        val rnd = Random(seed)
        val big = List(bigBlobCount) {
            object {
                val x = 0.05f + 0.9f * rnd.nextFloat()
                val y = 0.05f + 0.9f * rnd.nextFloat()
                val rFactor = rnd.nextFloat()
                val aFactor = rnd.nextFloat()
                val animDirectionX = (rnd.nextFloat() - 0.5f) * 2
                val animDirectionY = (rnd.nextFloat() - 0.5f) * 2
                val animRadiusMultiplier = 0.8f + rnd.nextFloat() * 0.4f
            }
        }
        val small = List(smallBlobCount) {
            object {
                val x = 0.02f + 0.96f * rnd.nextFloat()
                val y = 0.02f + 0.96f * rnd.nextFloat()
                val rFactor = rnd.nextFloat()
                val aFactor = rnd.nextFloat()
                val animDirectionX = (rnd.nextFloat() - 0.5f) * 2
                val animDirectionY = (rnd.nextFloat() - 0.5f) * 2
                val animRadiusMultiplier = 0.9f + rnd.nextFloat() * 0.2f
            }
        }
        big to small
    }

    val infiniteTransition = rememberInfiniteTransition(label = "bokeh_transition")
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bokeh_progress"
    )

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Fill background
            drawRect(color = backgroundColor)

            val (bigBlobs, smallBlobs) = rememberedBlobs
            val travelDistance = size.minDimension * 0.1f

            // Helper to draw a radial blob (soft circle) using a radial gradient brush
            fun DrawScope.drawBlob(center: Offset, radius: Float, innerAlpha: Float) {
                val brush = Brush.radialGradient(
                    colors = listOf(blobColor.copy(alpha = innerAlpha), Color.Transparent),
                    center = center,
                    radius = radius
                )
                drawCircle(brush = brush, center = center, radius = radius, alpha = 1f, style = Fill)
            }

            // Large, soft blobs for atmosphere
            val minBig = size.minDimension * 0.18f
            val maxBig = size.minDimension * 0.45f
            bigBlobs.forEach { blob ->
                val cx = blob.x * size.width + blob.animDirectionX * animationProgress * travelDistance
                val cy = blob.y * size.height + blob.animDirectionY * animationProgress * travelDistance
                val baseRadius = minBig + (maxBig - minBig) * blob.rFactor
                val radius = baseRadius * (1f + (animationProgress * 0.2f * blob.animRadiusMultiplier))
                val alpha = 0.06f + 0.08f * blob.aFactor
                drawBlob(Offset(cx.toFloat(), cy.toFloat()), radius, alpha)
            }

            // Smaller, brighter bokeh highlights
            val minSmall = size.minDimension * 0.03f
            val maxSmall = size.minDimension * 0.12f
            smallBlobs.forEach { blob ->
                val cx = blob.x * size.width + blob.animDirectionX * animationProgress * travelDistance
                val cy = blob.y * size.height + blob.animDirectionY * animationProgress * travelDistance
                val baseRadius = minSmall + (maxSmall - minSmall) * blob.rFactor
                val radius = baseRadius * (1f + (animationProgress * 0.3f * blob.animRadiusMultiplier))
                val alpha = 0.10f + 0.25f * blob.aFactor
                drawBlob(Offset(cx.toFloat(), cy.toFloat()), radius, alpha)
            }

            // Add a subtle vignette (darken edges slightly) to focus center
            val vignette = Brush.radialGradient(
                colors = listOf(Color.Transparent, Color(0xFF001726).copy(alpha = 0.35f)),
                center = Offset(size.width * 0.5f, size.height * 0.5f),
                radius = size.maxDimension * 0.9f
            )
            drawIntoCanvas {
                drawRect(brush = vignette, topLeft = Offset.Zero, size = size)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun BokehPreview() {
    BokehBackground(modifier = Modifier.fillMaxSize())
}
