package org.smartmuseum.fortnitecompanion.storage

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias KMMSharedPrefs = NSObject

actual fun KMMSharedPrefs.getInt(key: String): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

actual fun KMMSharedPrefs.setInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun KMMSharedPrefs.getIntList(key: String): List<Int> {
    return NSUserDefaults.standardUserDefaults.objectForKey(key) as List<Int>
}

actual fun KMMSharedPrefs.setIntList(
    key: String,
    values: List<Int>
) {
    NSUserDefaults.standardUserDefaults.setObject(values, key)
}

actual fun KMMSharedPrefs.getString(key: String): String {
    return NSUserDefaults.standardUserDefaults.getString(key)
}

actual fun KMMSharedPrefs.setString(
    key: String,
    value: String
) {
    NSUserDefaults.standardUserDefaults.setString(key, value)
}