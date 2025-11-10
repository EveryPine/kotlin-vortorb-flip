package manager

import domain.Board
import domain.BoardFactory
import domain.CardConfigProvider
import domain.GameState

class GameManager(
    private val gameState: GameState,
    private var board: Board = createBoard(gameState)
) {

    companion object {
        internal fun createBoard(gameState: GameState): Board {
            val cardConfig = CardConfigProvider.provide(gameState.getLevel())

            return BoardFactory.create(cardConfig, true)
        }
    }

    fun isRoundOver(): Boolean {
        return (board.isAllTwoFound() && board.isAllThreeFound()) || board.isVoltorbFound()
    }

    fun rerollRound() {
        gameState.addCoins(board.calculateCoins())
        gameState.nextRound()
        gameState.nextLevel(board.isVoltorbFound())
        board = createBoard(gameState)
    }

}