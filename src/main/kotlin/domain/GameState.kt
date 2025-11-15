package domain

import domain.Constants.MAX_LEVEL
import domain.Constants.MIN_LEVEL
import domain.Constants.ROUND_UPPER_BOUND
import kotlin.math.max
import kotlin.math.min

class GameState(
    private var coins: Int = 0,
    private var round: Int = 1,
    private var level: Int = 1,
    private var status: GameStatus = GameStatus.RUNNING
) {

    fun getCoins(): Int = coins

    fun getRound(): Int = round

    fun getLevel(): Int = level

    fun addCoins(coins: Int) {
        this.coins += coins
    }

    fun nextRound() {
        round++
    }

    fun nextLevel(voltorbFound: Boolean) {
        if (voltorbFound) {
            level = max(level - 1, MIN_LEVEL)
            return
        }

        level = min(level + 1, MAX_LEVEL)
    }

    fun exit() {
        status = GameStatus.EXITED
    }

    fun isFinalRoundOver(): Boolean {
        return round > ROUND_UPPER_BOUND
    }

    fun isExited(): Boolean {
        return status == GameStatus.EXITED
    }

}
