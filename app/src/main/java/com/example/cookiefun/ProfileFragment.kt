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
import android.widget.Toast

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var dbHelper: DbHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonDeleteAccount: Button = view.findViewById(R.id.button_delete_account)
        val buttonLogout: Button = view.findViewById(R.id.button_logout)

        dbHelper = DbHelper(requireContext(), null)

        buttonDeleteAccount.setOnClickListener {
            deleteAccount()
        }

        buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun deleteAccount() {

        dbHelper.deleteAllUsers()

        // Сброс аутентификации
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("is_authenticated", false)
            apply()
        }

        // Переход на экран входа
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()


    }

    private fun logout() {
        // Сбрасываем аутентификацию
        val sharedPreferences =
            requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_authenticated", false)
        editor.apply()
        Log.d("YourFragment", "Authentication reset")

        // Перенаправляем на экран входа
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        Log.d("YourFragment", "Navigated to MainActivity")
    }

}