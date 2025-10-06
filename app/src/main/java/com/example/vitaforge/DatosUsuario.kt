package com.example.vitaforge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DatosUsuario : AppCompatActivity() {

    // Constantes para la validación de los datos
    companion object {
        private const val minPeso = 30.0
        private const val maxPeso = 180.0
        private const val minAltura = 100.0
        private const val maxAltura = 250.0
        private const val minEdad = 12
        private const val maxEdad = 120
    }

    private lateinit var auth: FirebaseAuth //Firebase Authentication
    private lateinit var ff: FirebaseFirestore // Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_usuario)

        // Inicializa Firebase Authentication y Firestore
        auth = FirebaseAuth.getInstance()
        ff = FirebaseFirestore.getInstance()

        // Vincula las vistas
        val txtPeso = findViewById<EditText>(R.id.txtPeso)
        val txtAltura = findViewById<EditText>(R.id.txtAltura)
        val txtEdad = findViewById<EditText>(R.id.txtEdad)
        val rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        val rgObjetivo = findViewById<RadioGroup>(R.id.rgObjetivo)
        val rgEstiloVida = findViewById<RadioGroup>(R.id.rgEstiloVida)
        val btnVerMenu = findViewById<Button>(R.id.btnVerMenu)

        // Configura el botón de ver el menú
        btnVerMenu.setOnClickListener {
            val peso = txtPeso.text.toString().toDoubleOrNull()
            val altura = txtAltura.text.toString().toDoubleOrNull()
            val edad = txtEdad.text.toString().toIntOrNull()
            val sexo = findViewById<RadioButton>(rgSexo.checkedRadioButtonId)?.text?.toString() ?: ""
            val objetivo = findViewById<RadioButton>(rgObjetivo.checkedRadioButtonId)?.text?.toString() ?: ""
            val estiloVida = findViewById<RadioButton>(rgEstiloVida.checkedRadioButtonId)?.text?.toString() ?: ""

            when { // Validación de los datos
                !validarPeso(peso) -> {
                    txtPeso.error = "El peso debe estar entre $minPeso y $maxPeso kg"
                    txtPeso.requestFocus()
                }
                !validarAltura(altura) -> {
                    txtAltura.error = "La altura debe estar entre $minAltura y $maxAltura cm"
                    txtAltura.requestFocus()
                }
                !validarEdad(edad) -> {
                    txtEdad.error = "La edad debe estar entre $minEdad y $maxEdad años"
                    txtEdad.requestFocus()
                }
                sexo.isEmpty() -> {
                    Toast.makeText(this, "Seleccione su sexo", Toast.LENGTH_SHORT).show()
                    rgSexo.requestFocus()
                }
                objetivo.isEmpty() -> {
                    Toast.makeText(this, "Seleccione su objetivo", Toast.LENGTH_SHORT).show()
                    rgObjetivo.requestFocus()
                }
                estiloVida.isEmpty() -> {
                    Toast.makeText(this, "Seleccione su estilo de vida", Toast.LENGTH_SHORT).show()
                    rgEstiloVida.requestFocus()
                }
                else -> guardarDatosUsuario(peso,altura,edad,sexo,objetivo,estiloVida) // Si es rellenado completamente se llama a guardarDatosUsuario
            }
        }
    }

    // Función para guardar los datos del usuario en Firestore
    private fun guardarDatosUsuario(peso: Double?, altura: Double?, edad: Int?, sexo: String, objetivo: String, estiloVida: String) {
        val user = auth.currentUser // Verificación del usuario
        user?.let {
            val datosUsuario = hashMapOf( // Se crea un mapa para guardar los datos
                "peso" to peso, // Las claves deben coincidir
                "altura" to altura,
                "edad" to edad,
                "sexo" to sexo,
                "objetivo" to objetivo,
                "estiloVida" to estiloVida
            )

            ff.collection("users") // Operación con Firestore, accede a la colección users
                .document(it.uid) // al documento
                .update(datosUsuario as Map<String, Any>) // y actualiza los datos
                .addOnSuccessListener {
                    Toast.makeText(this, "Datos guardados con éxito", Toast.LENGTH_SHORT).show() // Mensaje de éxito
                    startActivity(Intent(this, MainActivity::class.java)) // Se accede a la ventana de ver el menu
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar los datos: ${e.message}", Toast.LENGTH_SHORT).show() // Mensaje de error
                }
        }
    }

    // Validación para el peso
    private fun validarPeso(peso: Double?): Boolean {
        return peso != null && peso in minPeso..maxPeso
    }

    // Validación para la altura
    private fun validarAltura(altura: Double?): Boolean {
        return altura != null && altura in minAltura..maxAltura
    }

    // Validación para la edad
    private fun validarEdad(edad: Int?): Boolean {
        return edad != null && edad in minEdad..maxEdad
    }
}//class