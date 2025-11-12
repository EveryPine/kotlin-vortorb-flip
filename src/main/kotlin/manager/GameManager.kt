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

    fun requestFlipCard(position: String) {
        board.flipOf(Position.of(position))
        OutputView.printInfoMessage("$position 카드를 뒤집었습니다.")
    }

    fun requestMarkCard(position: String, symbol: Char) {
        board.mark(Position.of(position), symbol)
        OutputView.printInfoMessage("$position 카드를 ${symbol}로 마킹했습니다")
    }

    fun requestUnmarkCard(position: Position) {
        board.unmark(position)
    }

    fun requestPrintStatus() {
        OutputView.printStatus(GameStateDto.from(gameState), BoardDto.from(board))
    }

    fun requestExitGame() {
        exitGame()
    }

    fun isGameOver(): Boolean {
        return gameState.isFinalRound() || (gameStatus == GameStatus.EXITED)
    }

    fun isRoundOver(): Boolean {
        return isGameOver() || (board.isAllTwoFound() && board.isAllThreeFound()) || board.isVoltorbFound()
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