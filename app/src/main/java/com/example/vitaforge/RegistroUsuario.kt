package com.example.vitaforge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroUsuario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth //Firebase Authentication
    private lateinit var ff: FirebaseFirestore // Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuario)

        // Inicializa Firebase Authentication y Firestore
        auth = FirebaseAuth.getInstance()
        ff = FirebaseFirestore.getInstance()

        // Vincula las vistas
        val correo = findViewById<EditText>(R.id.Correo)
        val password = findViewById<EditText>(R.id.Contraseña)
        val btnRegistro = findViewById<Button>(R.id.btnRegistrarse)

        // Configura el botón del registro
        btnRegistro.setOnClickListener {
            val correo = correo.text.toString().trim()
            val password = password.text.toString().trim()

            // Validar los campos
            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                registroUsuario(correo, password)
            }
        }

        // Configurar el boton de inicio de sesión
        val btnSesion = findViewById<Button>(R.id.btnSesion)

        btnSesion.setOnClickListener {
            val correo = correo.text.toString().trim()
            val password = password.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty()) { // Valida que el usuario ingrese el correo y la contraseña
                Toast.makeText(this, "Ingresa el correo y la contraseña", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(correo, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Verificar si ya tiene datos guardados en Firestore
                            val usuario = auth.currentUser
                            usuario?.let {
                                ff.collection("users").document(it.uid).get()
                                    .addOnSuccessListener { document ->
                                        if (document != null && document.contains("peso")) {
                                            // Si ya tiene datos, ir a MainActivity (pantalla de ver el menú)
                                            startActivity(Intent(this, MainActivity::class.java))
                                        } else {
                                            // Si no tiene datos, ir a DatosUsuario
                                            startActivity(Intent(this, DatosUsuario::class.java))
                                        }
                                        finish()
                                    }
                            }
                        } else {
                            Toast.makeText(this, "Error: no existe ningún usuario con esos datos", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    // Función del registro en Firestore usando Firebase Authentication
    private fun registroUsuario(correo: String, password: String) {
        auth.createUserWithEmailAndPassword(correo, password)// Crea el usuario
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { // Si tiene éxito
                    val user = auth.currentUser
                    user?.let {
                        guardarFirestore(it.uid, correo) // llama a la función guardarFirestore
                    }
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show() // Muestra el mensaje al usuario

                    // Redirige a DatosUsuario
                    val intent = Intent(this, DatosUsuario::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Registro fallido
                    Toast.makeText(this, "Error en el registro, el usuario ya esta registrado", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Función para almacenar los datos de un usuario en Firestore
    private fun guardarFirestore(uid: String, correo: String) {
        // Crea el objeto usuario
        val usuario = hashMapOf(
            "correo" to correo,
            "uid" to uid
        )

        // Guardar datos en Firestore, en la colleción users
        ff.collection("users")
            .document(uid)
            .set(usuario)
            .addOnSuccessListener {
            }
    }
}//class

/*
Para comprobar la conexión a Firebase:
        if (FirebaseApp.initializeApp(this) == null) {
            Log.e("Firebase", "Firebase no se ha inicializado correctamente")
        } else {
            Log.d("Firebase", "Firebase se ha inicializado correctamente")
        }
*/

