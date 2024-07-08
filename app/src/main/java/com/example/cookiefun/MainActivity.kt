package com.example.cookiefun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cookiefun.user.AuthActivity
import com.example.cookiefun.user.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Проверка аутентификации
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val isAuthenticated = sharedPreferences.getBoolean("is_authenticated", false)
        if (isAuthenticated) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DbHelper(this, null)
        val button_vhod: Button = findViewById(R.id.button_vxod)
        val button_registr: Button = findViewById(R.id.button_reg)
        // Добавляем кнопку выхода

        button_registr.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        button_vhod.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

    }
}
