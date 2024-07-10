package com.example.cookiefun.user

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.cookiefun.DbHelper
import com.example.cookiefun.MainActivity
import com.example.cookiefun.R
import java.io.IOException

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var dbHelper: DbHelper
    private lateinit var textLogin: TextView
    private lateinit var textEmail: TextView
    private lateinit var profileImage: ImageButton

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textLogin = view.findViewById(R.id.text_login) // Находим TextView с идентификатором text_login
        textEmail = view.findViewById(R.id.text_email) // Находим TextView с идентификатором text_email
        profileImage = view.findViewById(R.id.profile_image) // Находим ImageButton с идентификатором profile_image

        dbHelper = DbHelper(requireContext(), null)

        // Получаем логин и почту из SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val login = sharedPreferences.getString("login", "")
        val email = sharedPreferences.getString("email", "")

        // Отображаем логин и почту в TextView
        textLogin.text = login
        textEmail.text = email

        profileImage.setOnClickListener {
            openImageChooser()
        }

        val buttonDeleteAccount: Button = view.findViewById(R.id.button_delete_account)
        val buttonLogout: Button = view.findViewById(R.id.button_logout)

        buttonDeleteAccount.setOnClickListener {
            deleteAccount()
        }

        buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri? = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
                profileImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
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
