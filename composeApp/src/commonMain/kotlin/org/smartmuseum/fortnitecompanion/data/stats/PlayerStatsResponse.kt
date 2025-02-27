package org.smartmuseum.fortnitecompanion.data.stats

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.smartmuseum.fortnitecompanion.resources
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
        ret.add(Pair(stringResource(resources.strings.all), stats.all))
        if (stats.keyboardMouse != null) {
            ret.add(Pair(stringResource(resources.strings.keyboardAndMouse), stats.keyboardMouse))
        }
        if (stats.touch != null) {
            ret.add(Pair(stringResource(resources.strings.touch), stats.touch))
        }
        if (stats.gamepad != null) {
            ret.add(Pair(stringResource(resources.strings.gamepad), stats.gamepad))
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
            stringResource(resources.strings.overall) to this.overall,
            stringResource(resources.strings.solo) to this.solo,
            stringResource(resources.strings.duo) to this.duo,
            stringResource(resources.strings.squad) to this.squad,
            stringResource(resources.strings.ltm) to this.ltm
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
        ret[resources.strings.score] = score.toString()
        ret[resources.strings.scorePerMin] = scorePerMin.toString()
        ret[resources.strings.scorePerMatch] = scorePerMatch.toString()
        ret[resources.strings.kills] = kills.toString()
        ret[resources.strings.killsPerMin] = killsPerMin.toString()
        ret[resources.strings.killsPerMatch] = killsPerMatch.toString()
        ret[resources.strings.deaths] = deaths.toString()
        ret[resources.strings.kd] = kd.toString()
        ret[resources.strings.matches] = matches.toString()
        ret[resources.strings.winRate] = winRate.toString()
        ret[resources.strings.minutesPlayed] = minutesPlayed.toString()
        ret[resources.strings.playersOutlived] = playersOutlived.toString()
        ret[resources.strings.lastModified] = lastModified.formatDate("dd MMM yy HH:mm")
        return ret
    }
}