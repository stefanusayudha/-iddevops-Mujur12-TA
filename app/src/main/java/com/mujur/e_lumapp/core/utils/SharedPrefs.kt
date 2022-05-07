package com.mujur.e_lumapp.core.utils

import android.content.Context
import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class SharedPrefs(private val context: Context) {
    companion object {
        private const val PREF = "MyAppPrefName"
        private const val PREF_TOKEN = "user_token"
        private const val PREF_ISADMIN = "user_admin"
        private const val PREF_NAME = "user_name"
        private const val PREF_ID = "user_id"
        private const val PREF_STATUS = "user_status"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        put(PREF_TOKEN, token)
    }

    fun getToken(): String {
        return get(PREF_TOKEN, String::class.java)
    }

    fun saveIsAdmin(isAdmin: String) {
        put(PREF_ISADMIN, isAdmin)
    }

    fun getIsAdmin(): String {
        return get(PREF_ISADMIN, String::class.java)
    }

    fun saveName(name: String) {
        put(PREF_NAME, name)
    }

    fun getName(): String {
        return get(PREF_NAME, String::class.java)
    }

    fun saveID(id: Int) {
        put(PREF_ID, id)
    }

    fun getId(): Int {
        return get(PREF_ID, Int::class.java)
    }

    fun saveStatus(status: Boolean) {
        put(PREF_STATUS, status)
    }

    fun getStatus() : Boolean {
        return get(PREF_STATUS, Boolean::class.java)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }
}