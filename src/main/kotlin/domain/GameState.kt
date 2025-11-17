package domain

class GameState(
    private val cumulativeCoin: Coin = Coin.of(0),
    private var round: Round = Round.of(1),
    private val level: Level = Level.of(1),
    private var status: GameStatus = GameStatus.RUNNING
) {

    fun getCumulativeCoin(): Coin = cumulativeCoin

    fun getRound(): Round = round

    fun getLevel(): Level = level

    fun cumulateCoin(coin: Coin) {
        cumulativeCoin.add(coin)
    }

    fun advanceLevel(voltorbFound: Boolean) {
        level.next(voltorbFound)
    }

    fun advanceRound() {
        round.next()
    }

    fun exit() {
        status = GameStatus.EXITED
    }

    fun isFinalRound(): Boolean {
        return round.isFinal()
    }

    fun isFinalRoundOver(): Boolean {
        return round.isOver()
    }

    fun isExited(): Boolean {
        return status == GameStatus.EXITED
    }

}
