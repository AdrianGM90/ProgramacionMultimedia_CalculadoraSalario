package com.example.calculadorasalarioneto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadorasalarioneto.ui.theme.CalculadoraSalarioNetoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraSalarioNetoTheme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "pantalla_datos"
    ) {
        composable("pantalla_datos") {
            PantallaDatos(navController)
        }
        composable(
            "pantalla_resultados/{edad}/{estadoCivil}/{numHijos}/{sectorLaboral}/{salarioBA}/{numPagas}/{gradoDisc}"
        ) { backStackEntry ->
            PantallaResultados(
                edad = backStackEntry.arguments?.getString("edad") ?: "",
                estadoCivil = backStackEntry.arguments?.getString("estadoCivil") ?: "",
                numHijos = backStackEntry.arguments?.getString("numHijos") ?: "",
                sectorLaboral = backStackEntry.arguments?.getString("sectorLaboral") ?: "",
                salarioBA = backStackEntry.arguments?.getString("salarioBA") ?: "",
                numPagas = backStackEntry.arguments?.getString("numPagas") ?: "",
                gradoDisc = backStackEntry.arguments?.getString("gradoDisc") ?: ""
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDatos(navController: NavHostController) {

    var edad by rememberSaveable {
        mutableStateOf("")
    }
    var estadoCivil by rememberSaveable {
        mutableStateOf("")
    }
    var numHijos by rememberSaveable {
        mutableStateOf("")
    }
    var sectorLaboral by rememberSaveable {
        mutableStateOf("")
    }
    var salarioBA by rememberSaveable {
        mutableStateOf("")
    }
    var numPagas by rememberSaveable {
        mutableStateOf("")
    }
    var gradoDisc by rememberSaveable {
        mutableStateOf("")
    }
    var mensajeError by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "PETICIÓN DE DATOS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Edad: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad...") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Spacer(modifier = Modifier.padding(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Estado civil: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = estadoCivil,
                    onValueChange = { estadoCivil = it },
                    label = { Text("Soltero/Casado/...") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Número de hijos: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = numHijos,
                    onValueChange = { numHijos = it },
                    label = { Text("Número de hijos...") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Grupo profesional: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = sectorLaboral,
                    onValueChange = { sectorLaboral = it },
                    label = { Text("Sector laboral") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Salario bruto anual: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = salarioBA,
                    onValueChange = { salarioBA = it },
                    label = { Text("Salario bruto") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Número de pagas: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = numPagas,
                    onValueChange = { numPagas = it },
                    label = { Text("12 o 14") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.45f),
                    color = Color.Black,
                    text = "Grado discapacidad: ",
                    fontSize = 20.sp
                )
                TextField(
                    value = gradoDisc,
                    onValueChange = { gradoDisc = it },
                    label = { Text("En %") },
                    modifier = Modifier.weight(0.55f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Button(
                onClick = {
                    if (edad.isEmpty() || estadoCivil.isEmpty() || numHijos.isEmpty()
                        || sectorLaboral.isEmpty() || salarioBA.isEmpty() || numPagas.isEmpty()
                        || gradoDisc.isEmpty()
                    ) {
                        mensajeError = true
                    } else {
                        mensajeError = false
                        navController.navigate("pantalla_resultados/$edad/$estadoCivil/$numHijos/$sectorLaboral/$salarioBA/$numPagas/$gradoDisc")
                    }
                },
                modifier = Modifier.padding(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Calcular",
                    fontSize = 26.sp
                )
            }
            if (mensajeError) {
                Text(
                    text = "Por favor, rellena todos los campos antes de continuar",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaResultados(
    edad: String,
    estadoCivil: String,
    numHijos: String,
    sectorLaboral: String,
    salarioBA: String,
    numPagas: String,
    gradoDisc: String
) {
    val salario = salarioBA.toDouble()
    val irpf = retencionIRPF(salario)
    val neto = salarioNetoMensual(salario, irpf, numPagas.toInt())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "RESULTADOS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Salario Bruto: ",
                    fontSize = 20.sp
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Cyan,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = String.format("%.2f €", salario / numPagas.toInt()),
                        modifier = Modifier.padding(12.dp),
                        fontSize = 26.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Salario Neto: ",
                    fontSize = 20.sp
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Cyan,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = String.format(
                            "%.2f €", neto
                        ),
                        modifier = Modifier.padding(12.dp),
                        fontSize = 26.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Retención de IRPF: ",
                    fontSize = 20.sp
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Cyan,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = String.format("%.0f %%", irpf * 100),
                        modifier = Modifier.padding(12.dp),
                        fontSize = 26.sp
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Posibles deducciones: ",
                    fontSize = 20.sp
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Cyan,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "${
                            deducciones(
                                estadoCivil,
                                numHijos,
                                sectorLaboral,
                                edad.toInt(),
                                gradoDisc
                            )
                        }",
                        modifier = Modifier.padding(12.dp),
                        fontSize = 26.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PreviewPantallaResultados() {
    PantallaResultados(
        edad = "35",
        estadoCivil = "Soltero",
        numHijos = "2",
        sectorLaboral = "Administrativo",
        salarioBA = "30000",
        numPagas = "12",
        gradoDisc = "0"
    )
}

fun salarioNetoMensual(salarioBA: Double, retencionIRPF: Double, numPagas: Int): Double {
    return ((salarioBA - (salarioBA * retencionIRPF)) / numPagas)
}

fun retencionIRPF(salarioBA: Double): Double {
    val irpf = when {
        salarioBA < 12450 -> 0.19
        salarioBA <= 20200 -> 0.24
        salarioBA <= 35200 -> 0.3
        salarioBA <= 60000 -> 0.37
        salarioBA <= 300000 -> 0.45
        else -> 0.47
    }
    return irpf
}

fun deducciones(
    estadoCivil: String,
    numHijos: String,
    sectorLaboral: String,
    edad: Int,
    gradoDisc: String
): String {
    val deducciones = mutableListOf<String>()

    when (estadoCivil) {
        "Soltero", "Soltera" -> deducciones.add("Deducción para solteros")
        "Casado", "Casada" -> deducciones.add("Deducción por matrimonio")
        "Divorciado", "Divorciada" -> deducciones.add("Deducción por situación de divorcio")
        "Viudo", "Viuda" -> deducciones.add("Deducción por viudedad")
    }

    val hijos = numHijos.toInt()
    when {
        hijos == 1 -> deducciones.add("Deducción por 1 hijo")
        hijos == 2 -> deducciones.add("Deducción por 2 hijos")
        hijos >= 3 -> deducciones.add("Deducción por familia numerosa")
    }

    when (sectorLaboral) {
        "Administrativo" -> deducciones.add("Deducción por categoría de administrativo")
        "Sanidad" -> deducciones.add("Deducción por categoría de sanitario")
        "Educacion" -> deducciones.add("Deducción por categoría de docente")
        "Informatica" -> deducciones.add("Deducción por categoría de tecnologías de la información")
        "Ingenieria" -> deducciones.add("Deducción por categoría ingeniero")
    }

    when {
        edad <= 26 -> deducciones.add("Deducción por juventud (menor de 27 años)")
    }

    val gradoDiscapacidad = gradoDisc.toInt()
    when (gradoDiscapacidad) {
        in 1..24 -> deducciones.add("Deducción por discapacidad leve")
        in 25..49 -> deducciones.add("Deducción por discapacidad moderada")
        in 50..70 -> deducciones.add("Deducción por discapacidad grave")
        in 71..100 -> deducciones.add("Deducción por discapacidad muy grave")
    }

    return if (deducciones.isNotEmpty()) {
        deducciones.joinToString("\n")
    } else {
        "No hay deducciones aplicables"
    }
}