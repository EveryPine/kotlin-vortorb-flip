package manager

import domain.Board
import domain.BoardFactory
import domain.CardConfigProvider
import domain.CardType
import domain.GameState
import domain.GameStatus
import domain.Position
import dto.BoardDto
import dto.GameStateDto
import view.OutputView

class GameManager(
    private val gameState: GameState,
    private var board: Board = createBoard(gameState)
) {

    private var gameStatus = GameStatus.RUNNING

    companion object {
        internal fun createBoard(gameState: GameState): Board {
            val cardConfig = CardConfigProvider.provide(gameState.getLevel())

            return BoardFactory.create(cardConfig, true)
        }
    }

    fun requestFlipCard(position: Position) {
        board.flipOf(position)
    }

    fun requestMarkCard(position: Position, symbol: Char) {
        board.mark(position, symbol)
    }

    fun requestUnmarkCard(position: Position) {
        board.unmark(position)
    }

    fun requestPrintStatus() {
        OutputView.printStatus(GameStateDto.from(gameState), BoardDto.from(board))
    }

    fun isGameOver(): Boolean {
        return gameState.isFinalRound() || (gameStatus == GameStatus.EXITED)
    }

    fun isRoundOver(): Boolean {
        return (board.isAllTwoFound() && board.isAllThreeFound()) || board.isVoltorbFound()
    }

    fun exitGame() {
        gameStatus = GameStatus.EXITED
    }

    fun rerollRound() {
        gameState.addCoins(board.calculateCoins())
        gameState.nextRound()
        gameState.nextLevel(board.isVoltorbFound())
        board = createBoard(gameState)
    }

}