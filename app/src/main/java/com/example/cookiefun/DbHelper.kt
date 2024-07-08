package com.example.cookiefun

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.cookiefun.user.User

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 2) {

    companion object {
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_LOGIN = "login"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email"

        private const val TABLE_FAVORITES = "favorites"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_RECIPE_ID = "recipe_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_LOGIN TEXT, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(query)

        val createFavoritesTable = "CREATE TABLE $TABLE_FAVORITES ($COLUMN_USER_ID INTEGER, $COLUMN_RECIPE_ID INTEGER, PRIMARY KEY ($COLUMN_USER_ID, $COLUMN_RECIPE_ID))"
        db?.execSQL(createFavoritesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FAVORITES")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put(COLUMN_LOGIN, user.login)
        values.put(COLUMN_PASSWORD, user.password)

        val db = this.writableDatabase
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getUser(login: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?", arrayOf(login, password))
        val result = cursor.moveToFirst()
        cursor.close()
        return result
    }

    fun getUserId(login: String, password: String): Int? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_ID FROM $TABLE_USERS WHERE $COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?", arrayOf(login, password))
        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            cursor.close()
            userId
        } else {
            cursor.close()
            null
        }
    }
    // метод для удаления пользователя по логину
    fun deleteAllUsers() {
        val db = this.writableDatabase
        db.delete(TABLE_USERS, null, null)
        db.close()
        Log.d("DbHelper", "All users deleted")
    }

    fun addFavorite(userId: Int, recipeId: Int) {
        val values = ContentValues()
        values.put(COLUMN_USER_ID, userId)
        values.put(COLUMN_RECIPE_ID, recipeId)

        val db = this.writableDatabase
        db.insert(TABLE_FAVORITES, null, values)
        db.close()
    }

    fun removeFavorite(userId: Int, recipeId: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_FAVORITES, "$COLUMN_USER_ID = ? AND $COLUMN_RECIPE_ID = ?", arrayOf(userId.toString(), recipeId.toString()))
        db.close()
    }

    fun isFavorite(userId: Int, recipeId: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_FAVORITES WHERE $COLUMN_USER_ID = ? AND $COLUMN_RECIPE_ID = ?", arrayOf(userId.toString(), recipeId.toString()))
        val result = cursor.moveToFirst()
        cursor.close()
        return result
    }


}
