package org.smartmuseum.fortnitecompanion.storage

import com.diamondedge.logging.KmLog
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class KMMStorage(private val context: KMMSharedPrefs) : KoinComponent {
    private val logger: KmLog by inject { parametersOf("KMMStorage") }
    suspend fun getString(key: String): String {
        logger.info { "Getting string for key $key = ${context.getInt(key)}" }
        return context.getString(key)
    }

    suspend fun getInt(key: String): Int {
        logger.info { "Getting int for key $key = ${context.getInt(key)}" }
        return context.getInt(key)
    }

    suspend fun putInt(key: String, value: Int) {
        logger.info { "Put int $value for key $key" }
        context.setInt(key, value)
    }

    suspend fun getIntList(key: String): List<Int> {
        logger.info { "Getting int list for key $key = ${context.getInt(key)}" }
        return context.getIntList(key)
    }

    suspend fun addIntList(key: String, value: Int) {
        logger.info { "Put int $value to list for key $key" }
        val initialList: List<Int> = context.getIntList(key)
        context.setIntList(key, initialList.plus(value))
    }

//    suspend fun getSavedPlayers(): List<PlayerStatsUiState?> {
//        val jsonString: String = context.getString(SAVED_PLAYERS)
//        val ret = jsonString.split("|||").map {
//            PlayerStatsUiState.fromString(it)
//        }
//        logger.info{"getSavedPlayers returns ${ret}" }
//        return ret
//    }
//
//    suspend fun savePlayers(players: List<PlayerStatsUiState>) {
//        val jsonString: String = players.joinToString("|||") { it.encodeAsString() }
//        context.setString(SAVED_PLAYERS, jsonString)
//    }

    suspend fun getPlayerIds(): List<Int> = getIntList(OWN_PLAYER_SAVED_ID)

    suspend fun savePlayerId(value: Int) =
        addIntList(OWN_PLAYER_SAVED_ID, value)


    companion object {
        const val OWN_PLAYER_SAVED_ID = "OWN_PLAYER_SAVED_ID"
        const val SAVED_PLAYERS = "SAVED_PLAYERS"
    }
}

expect fun getStorage(context: KMMSharedPrefs): KMMStorage