package com.example.vitaforge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth //Firebase Authentication
    private lateinit var ff: FirebaseFirestore // Firestore
    private lateinit var tvMenu: TextView
    private lateinit var btnNuevoMenu: Button
    private lateinit var btnCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        tvMenu = findViewById(R.id.tvMenu)
        btnNuevoMenu = findViewById(R.id.btnNuevoMenu)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        ff = FirebaseFirestore.getInstance()

        // Cargar datos y generar el menú al iniciar
        cargarDatosUsuario()

        // Configurar el botón del menu
        btnNuevoMenu.setOnClickListener {
            cargarDatosUsuario()
        }

        // Configurar el botón de cerrar sesión
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }
    }

    // Función que carga los datos del usuario y genera el menú
    private fun cargarDatosUsuario() {
        val user = auth.currentUser // Verifica si el usuario esta registrado
        if (user != null) {
            ff.collection("users").document(user.uid).get() // Obtiene los datos de Firestore
                .addOnSuccessListener { document -> // Si tiene éxito, verifica el documento
                    if (document.exists()) {
                        val peso = document.getDouble("peso") ?: 0.0 // Extrae los valores
                        val altura = document.getDouble("altura") ?: 0.0
                        val edad = document.getLong("edad")?.toInt() ?: 0
                        val sexoStr = document.getString("sexo") ?: "hombre"
                        val objetivo = document.getString("objetivo") ?: "mantener"
                        val estiloVida = document.getString("estiloVida") ?: "moderada"

                        // Convertir el sexo a Int (0 para hombre, 1 para mujer)
                        val sexo = when (sexoStr.lowercase()) {
                            "mujer", "femenino" -> 1
                            else -> 0
                        }

                        // Generar el menú
                        val menu = Alimento.generarMenu( // LLama a la función generarMenu del objeto Alimento
                            sexo = sexo,
                            peso = peso,
                            altura = altura,
                            edad = edad,
                            objetivo = objetivo,
                            nivelActividad = estiloVida
                        )

                        tvMenu.text = salidaMenu(menu) // Muestra el menú
                    } else {
                        tvMenu.text = "No se encontraron datos. Completa tu perfil."
                    }
                }
                .addOnFailureListener { e -> // Fallo al cargar los datos
                    tvMenu.text = "Error al cargar datos: ${e.message}"
                }
        } else { // Si no esta registrado muestra
            tvMenu.text = "Usuario no registrado"
        }
    }
    // Función para ver el menú
    private fun salidaMenu(menu: Map<String, List<Alimento>>): String {
        return buildString {
            // Definir el orden de las comidas
            val comidas = listOf("desayuno", "comida", "cena")

            comidas.forEach { comida -> // recorre por cada comida
                // Encabezado de la comida
                append("══════ ${comida.uppercase()} ══════\n\n")

                // Obtener los alimentos para esta comida
                menu[comida]?.forEach { alimento ->
                    append(" • ${alimento.nombre}\n")

                }
                append("\n")
            }
        }
    }
}//class



























