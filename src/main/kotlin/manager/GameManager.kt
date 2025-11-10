package manager

import domain.Board
import domain.BoardFactory
import domain.CardConfigProvider
import domain.GameState

class GameManager(
    private val gameState: GameState
) {

    private lateinit var board: Board

    init {
        createBoard()
    }

    private fun createBoard() {
        val cardConfig = CardConfigProvider.provide(gameState.getLevel())
        board = BoardFactory.create(cardConfig, true)
    }

}