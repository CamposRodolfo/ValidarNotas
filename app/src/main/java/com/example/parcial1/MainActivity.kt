package com.example.parcial1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial1.ui.theme.Parcial1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial1Theme {
                AppUI()
            }
        }
    }
}

@Composable
fun AppUI() {
    val context = LocalContext.current
    var nota by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF99CCFF))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Parcial #1", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Rodolfo Campos 8-905-2179", fontSize = 18.sp)
        Text("Adriana Achurra 8-990-123", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))
        Text("Ingrese la nota a validar", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nota,
            onValueChange = { nota = it },
            label = { Text("Nota") }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                val num = nota.toDoubleOrNull()
                val mensaje = when {
                    nota.isBlank() -> "Por favor ingrese una nota"
                    num == null -> "Ingrese un número válido"
                    num < 0 || num > 100 -> "La nota debe estar entre 0 y 100"
                    num in 91.0..100.0 -> "A (Excelente)"
                    num in 81.0..90.99 -> "B (Bueno)"
                    num in 71.0..80.99 -> "C (Regular)"
                    num in 61.0..70.99 -> "D (Más o menos regular)"
                    else -> "No Aprobado"
                }
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Validar")
        }
    }
}
