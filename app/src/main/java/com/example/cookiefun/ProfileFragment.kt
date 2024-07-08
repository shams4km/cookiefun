package com.example.cookiefun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var dbHelper: DbHelper
    private lateinit var textLogin: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textLogin = view.findViewById(R.id.text_login)
        dbHelper = DbHelper(requireContext(), null)

        // Получаем логин из SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val login = sharedPreferences.getString("login", "")

        // Отображаем логин в TextView
        textLogin.text = "$login"

        val buttonDeleteAccount: Button = view.findViewById(R.id.button_delete_account)
        val buttonLogout: Button = view.findViewById(R.id.button_logout)

        buttonDeleteAccount.setOnClickListener {
            deleteAccount()
        }

        buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun deleteAccount() {
        dbHelper.deleteAllUsers()

        // Сбрасываем статус аутентификации
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("is_authenticated", false)
            apply()
        }

        // Переходим на экран входа
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    private fun logout() {
        // Сбрасываем статус аутентификации
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("is_authenticated", false)
            apply()
        }

        // Переходим на экран входа
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

