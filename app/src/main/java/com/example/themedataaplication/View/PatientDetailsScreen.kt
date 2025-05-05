import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController

@Composable
fun PatientDetailsScreen(navController: NavController) {
    var selectedGraph by rememberSaveable { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F6FF))
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocalHospital,
                contentDescription = "Avatar",
                tint = Color(0xFF0066CC),
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(20.dp)
            )

            Text(
                text = "Luiz Fernando De Luca",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF072448)
            )
            Text(
                text = "Idade 52 | Quarto 204 | 5 dias internado",
                fontSize = 16.sp,
                color = Color.Gray
            )

            InfoCardSection(
                title = "Exames Recentes",
                entries = listOf(
                    "Hemograma Completo" to "22 de Abril de 2024",
                    "Ressonância Magnética" to "16 de Abril de 2024",
                    "Tomografia Abdominal" to "6 de Abril de 2024",
                    "Tomografia de Tórax" to "25 de Março de 2024",
                    "Raio-X (Tórax)" to "12 de Março de 2024"
                ),
                iconColor = Color(0xFF4A90E2),
                icon = Icons.Default.MedicalServices
            )

            InfoCardSection(
                title = "Frequência Cardíaca",
                entries = listOf(
                    "Média Noturna" to "59 bpm",
                    "Média Diária" to "74 bpm",
                    "Pico Hoje" to "102 bpm",
                    "Mínima Hoje" to "53 bpm"
                ),
                iconColor = Color(0xFFFF8A80),
                icon = Icons.Default.MonitorHeart,
                onItemClick = { selectedGraph = it }
            )

            selectedGraph?.let {
                HeartRateGraph(title = it)
            }

            InfoCardSection(
                title = "Medicações",
                entries = listOf(
                    "Losartana 50 mg" to "08:00",
                    "Sinvastatina 20 mg" to "21:00",
                    "Metformina 850 mg" to "08:00 / 20:00",
                    "Omeprazol 20 mg" to "Antes do café da manhã",
                    "AAS 100 mg" to "10:00",
                    "Vitamina D3 2000 UI" to "12:00",
                    "Amoxicilina 500 mg" to "A cada 8 horas"
                ),
                iconColor = Color(0xFF81C784),
                icon = Icons.Default.Favorite
            )

            Text(
                text = "Ver histórico completo",
                color = Color(0xFF0051A2),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {}
            )

            Spacer(modifier = Modifier.height(24.dp))

            FloatingActionButton(
                onClick = {},
                containerColor = Color(0xFF0051A2)
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun InfoCardSection(
    title: String,
    entries: List<Pair<String, String>>,
    iconColor: Color,
    icon: ImageVector,
    onClick: (() -> Unit)? = null,
    onItemClick: ((String) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF072448)
            )

            Spacer(modifier = Modifier.height(12.dp))

            entries.forEach { (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = onItemClick != null) { onItemClick?.invoke(label) },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(iconColor.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = label, fontSize = 14.sp, color = Color(0xFF333333))
                    }
                    Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}