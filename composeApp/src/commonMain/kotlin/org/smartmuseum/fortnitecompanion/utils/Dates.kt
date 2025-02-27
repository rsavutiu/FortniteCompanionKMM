package org.smartmuseum.fortnitecompanion.utils

import kotlinx.datetime.Instant

expect fun Instant.formatDate(pattern: String, defValue: String = ""): String

expect fun String.parseDate(pattern: String, defValue: Long = 0L): Long

const val serverPatternDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val dateDisplayFormat = "dd MMM yy HH:mm"

const val dateDisplayFormatDayOnly = "dd MMM yy"