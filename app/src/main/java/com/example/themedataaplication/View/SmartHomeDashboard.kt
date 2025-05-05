package com.example.themedataaplication.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SmartHomeDashboard(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B2A38))
            .padding(16.dp)
    ) {
        // 🟦 Coluna 1 - Menu Lateral
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
                .background(Color(0xFF223344), RoundedCornerShape(16.dp))
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IconWithLabel(Icons.Default.Power, "Power")
            IconWithLabel(Icons.Default.Air, "Air purifier")
            IconWithLabel(Icons.Default.Security, "Security")
            IconWithLabel(Icons.Default.Lightbulb, "Light")
            IconWithLabel(Icons.Default.Thermostat, "Temperature")
            IconWithLabel(Icons.Default.PlayCircleFilled, "Media")
        }

        Spacer(modifier = Modifier.width(24.dp))

        // 🟨 Coluna 2 - Controle Central
        Column(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxHeight()
                .background(Color(0xFF24394D), RoundedCornerShape(24.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(Icons.Default.Home, contentDescription = null, tint = Color.White, modifier = Modifier.size(48.dp))
            Text("26°C | 35%", color = Color.White, fontSize = 20.sp)

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF76DC5D)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("ECO MODE", color = Color.White)
            }

            Text("26°C", fontSize = 32.sp, color = Color.White)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {}, shape = RoundedCornerShape(8.dp)) { Text("+") }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}, shape = RoundedCornerShape(8.dp)) { Text("-") }
            }

            Icon(Icons.Default.Air, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                repeat(4) {
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(10.dp)
                            .padding(2.dp)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("OFF", color = Color.White)
                Button(onClick = {}, shape = RoundedCornerShape(50)) {
                    Text("ON")
                }
                Icon(Icons.Default.AcUnit, contentDescription = null, tint = Color.White)
            }

            IconButton(onClick = { }) {
                Icon(Icons.Default.Mic, contentDescription = "Voice Command", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 💡 Controle de luzes por ambiente
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Light Controls", color = Color.White, fontSize = 18.sp)
                RoomLightCard("Living Room")
                RoomLightCard("Bathroom")
                RoomLightCard("Kitchen")
                RoomLightCard("Bedroom")
                RoomLightCard("TV")
                RoomLightCard("Window")
                RoomLightCard("Hallway")
                RoomLightCard("Garage")
                RoomLightCard("Garden")
                RoomLightCard("Balcony")
            }
        }

        Spacer(modifier = Modifier.width(24.dp))

        // 🟥 Coluna 3 - Consumo com gráfico
        Column(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Power Realtime", color = Color.White, fontSize = 16.sp)
            Text("1466 W", color = Color.Cyan, fontSize = 24.sp)

            Canvas(modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(150.dp)) {
                val barWidth = size.width / 6
                val spacing = barWidth / 2
                val values = listOf(0.6f, 0.8f, 0.4f, 0.7f)

                values.forEachIndexed { index, value ->
                    drawRoundRect(
                        color = Color.DarkGray,
                        topLeft = Offset(x = index * (barWidth + spacing), y = 0f),
                        size = Size(barWidth, size.height),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                    drawRoundRect(
                        color = Color.Cyan,
                        topLeft = Offset(x = index * (barWidth + spacing), y = size.height * (1 - value)),
                        size = Size(barWidth, size.height * value),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                }
            }

            repeat(4) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Device ${it + 1}", color = Color.White)
                    LinearProgressIndicator(
                        progress = 0.6f,
                        modifier = Modifier
                            .width(100.dp)
                            .height(8.dp),
                        color = Color.Cyan,
                        trackColor = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun IconWithLabel(icon: ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(icon, contentDescription = label, tint = Color.White)
        Text(label, color = Color.White)
    }
}

@Composable
fun RoomLightCard(roomName: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A3B4C))
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(roomName, color = Color.White)
            Switch(checked = true, onCheckedChange = {}, colors = SwitchDefaults.colors(checkedThumbColor = Color.Cyan))
        }
    }
}