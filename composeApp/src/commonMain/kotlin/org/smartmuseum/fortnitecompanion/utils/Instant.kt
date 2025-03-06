package org.smartmuseum.fortnitecompanion.utils

import androidx.compose.runtime.Composable
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.days_ago
import fortnitecompanionapp.composeapp.generated.resources.hours_ago
import fortnitecompanionapp.composeapp.generated.resources.just_now
import fortnitecompanionapp.composeapp.generated.resources.minutes_ago
import fortnitecompanionapp.composeapp.generated.resources.months_ago
import fortnitecompanionapp.composeapp.generated.resources.years_ago
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.math.absoluteValue
import kotlin.time.Duration

@Composable
fun Instant.getRelativeTimeText(now: Instant = Clock.System.now()): String {
    val duration: Duration = now - this
    return when {
        duration.inWholeSeconds < 60 -> stringResource(Res.string.just_now).format(duration.inWholeSeconds.toString())
        duration.inWholeMinutes < 60 -> stringResource(Res.string.minutes_ago).format(
            duration.inWholeMinutes.toString()
        )

        duration.inWholeHours < 24 -> stringResource(Res.string.hours_ago).format(duration.inWholeHours.toString())
        duration.inWholeDays < 30 -> stringResource(Res.string.days_ago).format(duration.inWholeDays.toString())
        duration.inWholeDays < 365 -> {
            val months = this.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber.minus(
                now.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber
            ).absoluteValue
            stringResource(Res.string.months_ago).format(months.toString())
        }

        else -> {
            val years = this.toLocalDateTime(TimeZone.currentSystemDefault()).year.minus(
                now.toLocalDateTime(TimeZone.currentSystemDefault()).year
            ).absoluteValue
            stringResource(Res.string.years_ago).format(years.toString())
        }
    }
}