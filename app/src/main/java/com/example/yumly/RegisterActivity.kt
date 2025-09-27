package com.example.yumly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // senin XML’in adı neyse onu yaz

        // Firebase Auth başlat
        auth = FirebaseAuth.getInstance()

        // XML'deki alanları bağla
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etPasswordConfirm = findViewById<TextInputEditText>(R.id.etPasswordConfirm)
        val btnRegister = findViewById<Button>(R.id.registerButton)

        // Butona tıklanınca (DEBUG TEST)
        btnRegister.setOnClickListener {


            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etPasswordConfirm.text.toString().trim()

            // 1. Alanlar boş mu?
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Şifreler aynı mı?
            if (password != confirmPassword) {
                Toast.makeText(this, "Şifreler aynı değil", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Firebase'e kayıt ol
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show()

                        // Kayıt sonrası HomeActivity'ye yönlendir
                        val intent = Intent(this, HomeActivity::class.java)
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
