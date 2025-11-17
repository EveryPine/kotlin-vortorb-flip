package domain

import domain.Constants.ROUND_UPPER_BOUND

class GameState(
    private val cumulativeCoin: Coin = Coin.of(0),
    private var round: Int = 1,
    private val level: Level = Level.of(1),
    private var status: GameStatus = GameStatus.RUNNING
) {

    fun getCumulativeCoin(): Coin = cumulativeCoin

    fun getRound(): Int = round

    fun getLevel(): Level = level

    fun cumulateCoin(coin: Coin) {
        cumulativeCoin.add(coin)
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
