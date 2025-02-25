package org.smartmuseum.fortnitecompanion

import kotlinx.datetime.Clock
import org.smartmuseum.fortnitecompanion.data.stats.StatsMode
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerStatsResponseTest {
    val playerStatsResponseEmptyDataPoints = StatsMode(
        score = 1000,
        scorePerMin = 10.0,
        scorePerMatch = 50.0,
        kills = 1000,
        killsPerMin = 33.0,
        killsPerMatch = 2.7,
        deaths = 800,
        kd = 1.25,
        matches = 500,
        winRate = 0.2,
        minutesPlayed = 325234,
        playersOutlived = 314323,
        lastModified = Clock.System.now()
    )

    @Test
    fun testStatsResponseDataPoints1() {
        val dataPoints = playerStatsResponseEmptyDataPoints.copy(
            wins = 5,
            top3 = 3,
            top5 = 2,
            top6 = 1,
            top10 = 4,
            top12 = 2,
            top25 = 20
        ).getDataPoints()
        assertEquals(dataPoints[0], 5f)
        assertEquals(dataPoints[1], dataPoints[2])
        assertEquals(dataPoints[1], 1.5f)
        assertEquals(dataPoints[3], 1f)
        assertEquals(dataPoints[4], 1f)
        assertEquals(dataPoints[5], 1f)
        assertEquals(dataPoints[6], 1f)
        assertEquals(dataPoints[7], 1f)
        assertEquals(dataPoints[8], 1f)
        assertEquals(dataPoints[9], 1f)
        assertEquals(dataPoints.size, 25)
    }

    @Test
    fun testStatsResponseDataPoints2() {
        val dataPoints = playerStatsResponseEmptyDataPoints.copy(
            wins = 5,
            top3 = 0,
            top5 = 2
        ).getDataPoints()
        assertEquals(dataPoints[0], 5f)
        assertEquals(dataPoints[1], dataPoints[2])
        assertEquals(dataPoints[1], 0f)
        assertEquals(dataPoints.size, 25)
    }
}