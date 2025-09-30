package com.example.yumly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Firebase başlat
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // XML'deki alanları bağla
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etPasswordConfirm = findViewById<TextInputEditText>(R.id.etPasswordConfirm)
        val btnRegister = findViewById<Button>(R.id.registerButton)

        // Butona tıklanınca
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etPasswordConfirm.text.toString().trim()

            // 1. Alan kontrolü
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Şifreler aynı mı?
            if (password != confirmPassword) {
                Toast.makeText(this, "Şifreler aynı değil", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Firebase Authentication ile kayıt
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid // Firebase UID

                        // Firestore’a kullanıcı bilgilerini kaydet
                        val user = hashMapOf(
                            "username" to username,
                            "email" to email
                        )

                        if (userId != null) {
                            db.collection("users").document(userId).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Kullanıcı Firestore’a kaydedildi!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Firestore hatası: ${it.message}", Toast.LENGTH_LONG).show()
                                }
                        }

                        // Kayıt başarılı → MainActivity’ye yönlendir
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(
                            this,
                            "Hata: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
