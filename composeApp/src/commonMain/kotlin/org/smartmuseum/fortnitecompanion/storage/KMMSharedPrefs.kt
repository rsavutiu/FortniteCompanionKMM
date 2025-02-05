package org.smartmuseum.fortnitecompanion.storage

expect class KMMSharedPrefs

expect fun KMMSharedPrefs.getInt(key: String) : Int
expect fun KMMSharedPrefs.setInt(key: String, value: Int)
expect fun KMMSharedPrefs.getString(key: String) : String
expect fun KMMSharedPrefs.setString(key: String, value: String)
expect fun KMMSharedPrefs.getIntList(key: String): List<Int>
expect fun KMMSharedPrefs.setIntList(key: String, values: List<Int>)