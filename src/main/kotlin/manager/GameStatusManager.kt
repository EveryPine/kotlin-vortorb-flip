package manager

import domain.Board
import domain.CardType
import domain.GameState

object GameStatusManager {

    fun isGameOver(gameState: GameState): Boolean {
        return gameState.isFinalRoundOver() || gameState.isExited()
    }

    fun isRoundOver(gameState: GameState, board: Board): Boolean {
        return isGameOver(gameState)
                || (board.isAllFound(CardType.TWO) && board.isAllFound(CardType.THREE))
                || board.isVoltorbFound()
    }

    fun rerollRound(gameState: GameState, board: Board) {
        gameState.addCoins(board.calculateCoins())
        gameState.nextRound()
        gameState.nextLevel(board.isVoltorbFound())
        board.reset(gameState.getLevel())
    }
}