import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HeartRateGraph(title: String) {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Gráfico: $title", fontWeight = FontWeight.Bold, color = Color(0xFF072448))
            Spacer(modifier = Modifier.height(8.dp))
            Canvas(modifier = Modifier.fillMaxSize()) {
                val gridSize = 20f
                val gridLinesX = (0..size.width.toInt() step gridSize.toInt())
                val gridLinesY = (0..size.height.toInt() step gridSize.toInt())

                gridLinesX.forEach { x ->
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(x.toFloat(), 0f),
                        end = Offset(x.toFloat(), size.height),
                        strokeWidth = 1f
                    )
                }

                gridLinesY.forEach { y ->
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y.toFloat()),
                        end = Offset(size.width, y.toFloat()),
                        strokeWidth = 1f
                    )
                }

                // Batimentos com mais picos e mais densos
                val waveform = listOf(
                    0f, 25f, -60f, 90f, -60f, 25f, 0f, 0f, 25f, -50f, 80f, -50f, 25f, 0f,
                    0f, 30f, -70f, 100f, -70f, 30f, 0f, 0f, 0f, 25f, -60f, 90f, -60f, 25f, 0f, 0f, 25f, -50f, 80f, -50f, 25f, 0f,
                    0f, 30f, -70f, 100f, -70f, 30f, 0f, 0f, 0f, 25f, -60f, 90f, -60f, 25f, 0f, 0f, 25f, -50f, 80f, -50f, 25f, 0f,
                    0f, 30f, -70f, 100f, -70f, 30f, 0f, 0f
                )

                val spacing = size.width / (waveform.size * 0.7f)  // Picos mais juntos
                val centerY = size.height / 2
                val totalLength = waveform.size * spacing
                val offsetX = (animatedOffset % 1f) * totalLength

                var startX = -offsetX
                while (startX < size.width) {
                    for (i in 0 until waveform.size - 1) {
                        val x1 = startX + i * spacing
                        val x2 = startX + (i + 1) * spacing
                        if (x2 < 0 || x1 > size.width) continue

                        val y1 = centerY - waveform[i]
                        val y2 = centerY - waveform[i + 1]
                        drawLine(
                            color = Color.Black,
                            start = Offset(x1, y1),
                            end = Offset(x2, y2),
                            strokeWidth = 2f
                        )
                    }
                    startX += totalLength
                }
            }
        }
    }
}
