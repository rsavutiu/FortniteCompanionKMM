package org.smartmuseum.fortnitecompanion.ui.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.smartmuseum.fortnitecompanion.data.stats.BattlePass
import org.smartmuseum.fortnitecompanion.data.stats.PlayerAccount
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStats
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStatsData
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStatsResponse
import org.smartmuseum.fortnitecompanion.data.stats.StatsCategory
import org.smartmuseum.fortnitecompanion.data.stats.StatsMode
import org.smartmuseum.fortnitecompanion.ui.screens.cosmetics.PlayerStatsScreen
import org.smartmuseum.fortnitecompanion.ui.themes.AppTheme

fun mockPlayerStatsResponse(): PlayerStatsResponse {
    return PlayerStatsResponse(
        status = 200,
        data = PlayerStatsData(
            account = PlayerAccount(
                id = "be077f6151034d418b0a1f476056d628",
                name = "144hz misu."
            ),
            battlePass = BattlePass(
                level = 14,
                progress = 5
            ),
            stats = PlayerStats(
                all = StatsCategory(
                    overall = StatsMode(
                        score = 667731,
                        scorePerMin = 25.899,
                        scorePerMatch = 191.053,
                        wins = 111,
                        top3 = 41,
                        top5 = 63,
                        top6 = 62,
                        top10 = 50,
                        top12 = 143,
                        top25 = 101,
                        kills = 7921,
                        killsPerMin = 0.307,
                        killsPerMatch = 2.266,
                        deaths = 3384,
                        kd = 2.341,
                        matches = 3495,
                        winRate = 3.176,
                        minutesPlayed = 25782,
                        playersOutlived = 141238,
                        lastModified = LocalDateTime.parse("2025-02-25T14:33:44")
                            .toInstant(TimeZone.UTC)
                    ),
                    solo = StatsMode(
                        score = 79881,
                        scorePerMin = 28.127,
                        scorePerMatch = 186.638,
                        wins = 14,
                        top3 = null,
                        top5 = null,
                        top6 = null,
                        top10 = 50,
                        top12 = null,
                        top25 = 101,
                        kills = 1000,
                        killsPerMin = 0.352,
                        killsPerMatch = 2.336,
                        deaths = 414,
                        kd = 2.415,
                        matches = 428,
                        winRate = 3.271,
                        minutesPlayed = 2840,
                        playersOutlived = 18762,
                        lastModified = LocalDateTime.parse("2025-01-17T18:37:02")
                            .toInstant(TimeZone.UTC)
                    ),
                    duo = StatsMode(
                        score = 97592,
                        scorePerMin = 25.99,
                        scorePerMatch = 212.157,
                        wins = 18,
                        top3 = null,
                        top5 = 63,
                        top6 = null,
                        top10 = null,
                        top12 = 143,
                        top25 = null,
                        kills = 936,
                        killsPerMin = 0.249,
                        killsPerMatch = 2.035,
                        deaths = 442,
                        kd = 2.118,
                        matches = 460,
                        winRate = 3.913,
                        minutesPlayed = 3755,
                        playersOutlived = 23755,
                        lastModified = LocalDateTime.parse("2025-02-21T17:51:36")
                            .toInstant(TimeZone.UTC)
                    ),
                    squad = StatsMode(
                        score = 47823,
                        scorePerMin = 27.08,
                        scorePerMatch = 293.393,
                        wins = 18,
                        top3 = 41,
                        top5 = null,
                        top6 = 62,
                        top10 = null,
                        top12 = null,
                        top25 = null,
                        kills = 453,
                        killsPerMin = 0.257,
                        killsPerMatch = 2.779,
                        deaths = 145,
                        kd = 3.124,
                        matches = 163,
                        winRate = 11.043,
                        minutesPlayed = 1766,
                        playersOutlived = 8939,
                        lastModified = LocalDateTime.parse("2024-11-29T22:05:05")
                            .toInstant(TimeZone.UTC)
                    ),
                    ltm = StatsMode(
                        score = 474668,
                        scorePerMin = 25.464,
                        scorePerMatch = 185.707,
                        wins = 71,
                        top3 = null,
                        top5 = null,
                        top6 = null,
                        top10 = null,
                        top12 = null,
                        top25 = null,
                        kills = 5842,
                        killsPerMin = 0.313,
                        killsPerMatch = 2.286,
                        deaths = 2485,
                        kd = 2.351,
                        matches = 2556,
                        winRate = 2.778,
                        minutesPlayed = 18641,
                        playersOutlived = 96051,
                        lastModified = LocalDateTime.parse("2025-02-25T14:33:44")
                            .toInstant(TimeZone.UTC)
                    )
                ),
                keyboardMouse = StatsCategory(
                    overall = StatsMode(
                        score = 307514,
                        scorePerMin = 26.034,
                        scorePerMatch = 189.123,
                        wins = 52,
                        top3 = 18,
                        top5 = 35,
                        top6 = 29,
                        top10 = 22,
                        top12 = 76,
                        top25 = 44,
                        kills = 3450,
                        killsPerMin = 0.292,
                        killsPerMatch = 2.122,
                        deaths = 1574,
                        kd = 2.192,
                        matches = 1626,
                        winRate = 3.198,
                        minutesPlayed = 11812,
                        playersOutlived = 66595,
                        lastModified = LocalDateTime.parse("2025-02-25T14:33:44")
                            .toInstant(TimeZone.UTC)
                    ),
                    solo = StatsMode(
                        score = 35478,
                        scorePerMin = 28.914,
                        scorePerMatch = 199.315,
                        wins = 7,
                        top3 = null,
                        top5 = null,
                        top6 = null,
                        top10 = 22,
                        top12 = null,
                        top25 = 44,
                        kills = 473,
                        killsPerMin = 0.385,
                        killsPerMatch = 2.657,
                        deaths = 171,
                        kd = 2.766,
                        matches = 178,
                        winRate = 3.933,
                        minutesPlayed = 1227,
                        playersOutlived = 7965,
                        lastModified = LocalDateTime.parse("2025-01-17T18:37:02")
                            .toInstant(TimeZone.UTC)
                    ),
                    duo = StatsMode(
                        score = 53331,
                        scorePerMin = 26.706,
                        scorePerMatch = 225.025,
                        wins = 11,
                        top3 = null,
                        top5 = 35,
                        top6 = null,
                        top10 = null,
                        top12 = 76,
                        top25 = null,
                        kills = 543,
                        killsPerMin = 0.27,
                        killsPerMatch = 1.762,
                        deaths = 216,
                        kd = 1.819,
                        matches = 223,
                        winRate = 3.139,
                        minutesPlayed = 1758,
                        playersOutlived = 11193,
                        lastModified = Instant.parse("2024-11-02T16:27:59Z")
                    )
                )
            )
        )
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
@PreviewLightDark
fun PlayerStatsScreenPreview() {
    val playerStatsResponse: PlayerStatsResponse = mockPlayerStatsResponse()
    AppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp))
            PlayerStatsScreen(
                coroutineScope = rememberCoroutineScope(),
                stats = playerStatsResponse.data
            )
        }
    }
}