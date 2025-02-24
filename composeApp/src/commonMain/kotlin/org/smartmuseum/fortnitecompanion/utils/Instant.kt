package org.smartmuseum.fortnitecompanion.utils

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.smartmuseum.fortnitecompanion.resources
import kotlin.math.absoluteValue
import kotlin.time.Duration

@Composable
fun Instant.getRelativeTimeText(now: Instant = Clock.System.now()): String {
    val duration: Duration = now - this
    return when {
        duration.inWholeSeconds < 60 -> stringResource(resources.strings.just_now).format(duration.inWholeSeconds.toString())
        duration.inWholeMinutes < 60 -> stringResource(resources.strings.minutes_ago).format(
            duration.inWholeMinutes.toString()
        )

        duration.inWholeHours < 24 -> stringResource(resources.strings.hours_ago).format(duration.inWholeHours.toString())
        duration.inWholeDays < 30 -> stringResource(resources.strings.days_ago).format(duration.inWholeDays.toString())
        duration.inWholeDays < 365 -> {
            val months = this.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber.minus(
                now.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber
            ).absoluteValue
            stringResource(resources.strings.months_ago).format(months.toString())
        }

        else -> {
            val years = this.toLocalDateTime(TimeZone.currentSystemDefault()).year.minus(
                now.toLocalDateTime(TimeZone.currentSystemDefault()).year
            ).absoluteValue
            stringResource(resources.strings.years_ago).format(years.toString())
        }
    }
}