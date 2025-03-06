package org.smartmuseum.fortnitecompanion.data.stats

import androidx.compose.runtime.Composable
import fortnitecompanionapp.composeapp.generated.resources.Res
import fortnitecompanionapp.composeapp.generated.resources.all
import fortnitecompanionapp.composeapp.generated.resources.deaths
import fortnitecompanionapp.composeapp.generated.resources.duo
import fortnitecompanionapp.composeapp.generated.resources.gamepad
import fortnitecompanionapp.composeapp.generated.resources.kd
import fortnitecompanionapp.composeapp.generated.resources.keyboardAndMouse
import fortnitecompanionapp.composeapp.generated.resources.kills
import fortnitecompanionapp.composeapp.generated.resources.killsPerMatch
import fortnitecompanionapp.composeapp.generated.resources.killsPerMin
import fortnitecompanionapp.composeapp.generated.resources.lastModified
import fortnitecompanionapp.composeapp.generated.resources.ltm
import fortnitecompanionapp.composeapp.generated.resources.matches
import fortnitecompanionapp.composeapp.generated.resources.minutesPlayed
import fortnitecompanionapp.composeapp.generated.resources.overall
import fortnitecompanionapp.composeapp.generated.resources.playersOutlived
import fortnitecompanionapp.composeapp.generated.resources.score
import fortnitecompanionapp.composeapp.generated.resources.scorePerMatch
import fortnitecompanionapp.composeapp.generated.resources.scorePerMin
import fortnitecompanionapp.composeapp.generated.resources.solo
import fortnitecompanionapp.composeapp.generated.resources.squad
import fortnitecompanionapp.composeapp.generated.resources.touch
import fortnitecompanionapp.composeapp.generated.resources.winRate
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.smartmuseum.fortnitecompanion.utils.formatDate

@Serializable
data class PlayerStatsResponse(
    val status: Int,
    val data: PlayerStatsData
)

@Serializable
data class PlayerStatsData(
    val account: PlayerAccount,
    val battlePass: BattlePass,
    val stats: PlayerStats
) {
    @Composable
    fun getTabs(): List<Pair<String, StatsCategory>> {
        val ret: MutableList<Pair<String, StatsCategory>> = mutableListOf()
        ret.add(Pair(stringResource(Res.string.all), stats.all))
        if (stats.keyboardMouse != null) {
            ret.add(Pair(stringResource(Res.string.keyboardAndMouse), stats.keyboardMouse))
        }
        if (stats.touch != null) {
            ret.add(Pair(stringResource(Res.string.touch), stats.touch))
        }
        if (stats.gamepad != null) {
            ret.add(Pair(stringResource(Res.string.gamepad), stats.gamepad))
        }
        return ret
    }
}

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
    val gamepad: StatsCategory? = null,
    val keyboardMouse: StatsCategory? = null,
    val touch: StatsCategory? = null
)

@Serializable
data class StatsCategory(
    val overall: StatsMode? = null,
    val solo: StatsMode? = null,
    val duo: StatsMode? = null,
    val squad: StatsMode? = null,
    val ltm: StatsMode? = null
) {
    @Composable
    fun getNonnullStatsMap(): LinkedHashMap<String, StatsMode> {
        val statsMap = linkedMapOf<String, StatsMode>()
        val possibleStats: Map<String, StatsMode?> = mapOf(
            stringResource(Res.string.overall) to this.overall,
            stringResource(Res.string.solo) to this.solo,
            stringResource(Res.string.duo) to this.duo,
            stringResource(Res.string.squad) to this.squad,
            stringResource(Res.string.ltm) to this.ltm
        )
        for (entry in possibleStats.entries) {
            entry.value?.let {
                statsMap[entry.key] = it
            }
        }
        return statsMap
    }
}

@Serializable
data class StatsMode(
    val score: Int,
    val scorePerMin: Double,
    val scorePerMatch: Double,
    val wins: Int? = null,
    val top3: Int? = null,
    val top5: Int? = null,
    val top6: Int? = null,
    val top10: Int? = null,
    val top12: Int? = null,
    val top25: Int? = null,
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
) {
    fun getDataPoints(): List<Float> {
        val ret: MutableList<Float> = mutableListOf()
        var lastFilledIndex = 1
        ret.add(wins?.toFloat() ?: 0f)
        val tops = listOf(
            Pair(top3, 3),
            Pair(top5, 5),
            Pair(top6, 6),
            Pair(top10, 10),
            Pair(top12, 12),
            Pair(top25, 25)
        )
        for (top in tops) {
            top.first?.let { topValue ->
                val diff = top.second - lastFilledIndex
                for (i in lastFilledIndex until top.second) {
                    ret.add(topValue.toFloat() / diff.toFloat())
                }
                lastFilledIndex = top.second
            }
        }
        while (ret.size < 25) {
            ret.add(0f)
        }
        return ret
    }

    fun toMap(): HashMap<StringResource, String> {
        val ret: HashMap<StringResource, String> = hashMapOf()
        ret[Res.string.score] = score.toString()
        ret[Res.string.scorePerMin] = scorePerMin.toString()
        ret[Res.string.scorePerMatch] = scorePerMatch.toString()
        ret[Res.string.kills] = kills.toString()
        ret[Res.string.killsPerMin] = killsPerMin.toString()
        ret[Res.string.killsPerMatch] = killsPerMatch.toString()
        ret[Res.string.deaths] = deaths.toString()
        ret[Res.string.kd] = kd.toString()
        ret[Res.string.matches] = matches.toString()
        ret[Res.string.winRate] = winRate.toString()
        ret[Res.string.minutesPlayed] = minutesPlayed.toString()
        ret[Res.string.playersOutlived] = playersOutlived.toString()
        ret[Res.string.lastModified] = lastModified.formatDate("dd MMM yy HH:mm")
        return ret
    }
}