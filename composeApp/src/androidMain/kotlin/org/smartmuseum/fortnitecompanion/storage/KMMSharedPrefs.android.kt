package org.smartmuseum.fortnitecompanion.storage

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

actual typealias KMMSharedPrefs = Application

actual fun KMMSharedPrefs.getInt(key: String ) : Int {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getInt(key, -1)
}

actual fun KMMSharedPrefs.setInt(key: String, value: Int) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putInt(key,value)
    editor.apply()
}

actual fun KMMSharedPrefs.getIntList(key: String): List<Int> {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val stringValue: MutableSet<String>? = prefs.getStringSet(key, emptySet())
    return stringValue?.map { it.toInt() } ?: emptyList()
}

actual fun KMMSharedPrefs.setIntList(key: String, values: List<Int>) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val valuesToSave = values.map { it.toString() }.toSet()
    val editor = prefs.edit()
    editor.putStringSet(key, valuesToSave)
    editor.apply()
}

actual fun KMMSharedPrefs.getString(key: String): String {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    return prefs.getString(key, "")!!
}

actual fun KMMSharedPrefs.setString(
    key: String,
    value: String
) {
    val prefs: SharedPreferences = this.getSharedPreferences("", MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key, value)
    editor.apply()
}