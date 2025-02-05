package org.smartmuseum.fortnitecompanion.data.stats

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class PlayerStatsResponse(
    val status: Int,
    val data: PlayerStatsData
)

@Serializable
data class PlayerStatsData(
    val account: PlayerAccount,val battlePass: BattlePass,
    val stats: PlayerStats
)

@Serializable
data class PlayerAccount(
    val id: String,
    val name: String
)

@Serializable
data class BattlePass(
    val level: Int,
    val progress: Int
)

@Serializable
data class PlayerStats(
    val all: StatsCategory,
    val gamepad: StatsCategory
)

@Serializable
data class StatsCategory(
    val overall: StatsMode,
    val solo: StatsMode
)

@Serializable
data class StatsMode(
    val score: Int,
    val scorePerMin: Double,
    val scorePerMatch: Double,
    val wins: Int,
    val top3: Int? = null,
    val top5: Int? = null,
    val top6: Int? = null,
    val top10: Int,
    val top12: Int? = null,
    val top25: Int,
    val kills: Int,
    val killsPerMin: Double,
    val killsPerMatch: Double,
    val deaths: Int,
    val kd: Double,
    val matches: Int,
    val winRate: Double,
    val minutesPlayed: Int,
    val playersOutlived: Int,
    val lastModified: Instant
)