package org.smartmuseum.fortnitecompanion.ui.molecules

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max

@Composable
fun SimpleFloatGraph(
    dataPoints: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Blue,
    pointColor: Color = Color.Red,
    axisColor: Color = Color.Gray,
    backgroundColor: Color = Color.Transparent
) {
    val graphHeight = 200.dp
    val graphPadding = 16.dp
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(graphHeight)
            .padding(graphPadding)
            .background(color = backgroundColor)
    ) {
        if (dataPoints.isEmpty()) return@Canvas

        val graphPaddingPx = graphPadding.toPx()
        val graphHeightPx = graphHeight.toPx()
        val graphWidthPx = size.width
        val maxDataPoint = dataPoints.maxOrNull() ?: 0f
        val minDataPoint = 0f
        val dataRange = max(maxDataPoint - minDataPoint, 1f)
        val pointSpacing = (graphWidthPx - 2 * graphPaddingPx) / (dataPoints.size - 1)

        val path = Path()

        dataPoints.forEachIndexed { index, dataPoint ->
            val x = graphPaddingPx + index * pointSpacing
            val y =
                graphHeightPx - graphPaddingPx - ((dataPoint - minDataPoint) / dataRange) * (graphHeightPx - 2 * graphPaddingPx)

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }

            drawCircle(
                color = pointColor,
                center = Offset(x, y),
                radius = 4f
            )
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 2f)
        )

        // Draw X-axis
        drawLine(
            color = axisColor,
            start = Offset(graphPaddingPx, graphHeightPx - graphPaddingPx),
            end = Offset(graphWidthPx - graphPaddingPx, graphHeightPx - graphPaddingPx),
            strokeWidth = 2f
        )

        // Draw Y-axis
        drawLine(
            color = axisColor,
            start = Offset(graphPaddingPx, graphHeightPx - graphPaddingPx),
            end = Offset(graphPaddingPx, graphPaddingPx),
            strokeWidth = 2f
        )
        val labelFontSize = 12.sp
        val measuredTextHeight =
            textMeasurer.measure("Wins", style = TextStyle(fontSize = labelFontSize)).size.height
        drawText(
            textMeasurer = textMeasurer,
            text = maxDataPoint.toString(),
            topLeft = Offset(
                x = 0f,
                y = 0f
            ),
            style = TextStyle(color = axisColor, fontSize = labelFontSize)
        )

        // Draw X-axis labels
        val labelSpacing = pointSpacing
        val labelYOffset = 20f // Adjust this value to control the distance below the X-axis

        for (i in 0..dataPoints.size step 5) {
            val x = graphPaddingPx + i * labelSpacing
            val label = if (i == 0) "Wins" else "Top $i"
            drawText(
                textMeasurer = textMeasurer,
                text = label,
                topLeft = Offset(
                    x = x - 10,
                    y = 0f + graphHeight.toPx() - graphPadding.toPx() - measuredTextHeight - labelYOffset * 2
                ),
                style = TextStyle(color = axisColor, fontSize = labelFontSize)
            )
        }
    }
}