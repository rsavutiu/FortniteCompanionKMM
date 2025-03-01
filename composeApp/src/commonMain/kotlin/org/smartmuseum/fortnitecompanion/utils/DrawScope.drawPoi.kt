package org.smartmuseum.fortnitecompanion.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

fun DrawScope.drawPoi(
    textColor: Color,
    x: Float, y: Float, name: String, textMeasurer: TextMeasurer,
    fontSize: TextUnit,
    imageHeight: Float,
) {
    if (y < 300) {
        drawCircle(
            color = Color.Red,
            center = Offset(x, y + 20f),
            radius = 10.dp.toPx()
        )
    } else if (y > imageHeight - 300) {
        drawCircle(
            color = Color.Red,
            center = Offset(x, y - 20f),
            radius = 10.dp.toPx()
        )
    } else {
        drawCircle(
            color = Color.Red,
            center = Offset(x, y),
            radius = 10.dp.toPx()
        )
    }
    val measuredTextHeight =
        textMeasurer.measure(name, style = TextStyle(fontSize = fontSize)).size.height
    try {
        drawText(
            textMeasurer = textMeasurer,
            text = name,
            topLeft = Offset(
                x = x,
                y = y - measuredTextHeight
            ),
            style = TextStyle(color = textColor, fontSize = fontSize, fontWeight = FontWeight.Bold)
        )
    } catch (_: Exception) {
    }
}