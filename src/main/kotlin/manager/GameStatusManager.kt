package manager

import domain.Board
import domain.BoardFactory
import domain.Card
import domain.CardConfigProvider
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

    fun advanceRound(gameState: GameState, board: Board) {
        gameState.cumulateCoin(board.calculateObtainedCoin())
        gameState.advanceRound()
        gameState.advanceLevel(board.isVoltorbFound())
    }

    fun resetBoard(gameState: GameState): Board {
        val cards: List<Card> = CardConfigProvider.provide(
            gameState.getLevel())
            .toList()

        return BoardFactory.create(cards)
    }
}