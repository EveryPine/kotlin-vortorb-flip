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
        private fun createBoard(gameState: GameState): Board {
            val cardConfig = CardConfigProvider.provide(gameState.getLevel())

            return BoardFactory.create(cardConfig, true)
        }
    }

    fun isRoundOver(): Boolean {
        return (board.isAllTwoFound() && board.isAllThreeFound()) || board.isVoltorbFound()
    }

}