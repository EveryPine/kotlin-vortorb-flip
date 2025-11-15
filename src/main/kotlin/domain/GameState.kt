package domain

import domain.Constants.ROUND_UPPER_BOUND

class GameState(
    private var coins: Int = 0,
    private var round: Int = 1,
    private var level: Level = Level.of(1),
    private var status: GameStatus = GameStatus.RUNNING
) {

    fun getCoins(): Int = coins

    fun getRound(): Int = round

    fun getLevel(): Level = level

    fun addCoins(coins: Int) {
        this.coins += coins
    }

    fun advanceLevel(voltorbFound: Boolean) {
        level.next(voltorbFound)
    }

    fun nextRound() {
        round++
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
