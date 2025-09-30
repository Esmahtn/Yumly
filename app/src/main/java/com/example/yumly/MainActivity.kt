package com.example.yumly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Firebase başlat
        auth = FirebaseAuth.getInstance()

        // Kullanıcı giriş yapmış mı kontrol et
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Kullanıcı giriş yaptıysa direkt HomeActivity'ye git
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Eğer kullanıcı giriş yapmamışsa mevcut ekranı göster
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Login butonuna basınca LoginActivity açılacak
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Register butonuna basınca RegisterActivity açılacak
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
