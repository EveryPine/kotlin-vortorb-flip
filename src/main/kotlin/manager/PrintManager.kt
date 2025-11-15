package manager

import domain.Board
import domain.GameState
import dto.BoardDto
import dto.GameStateDto
import dto.RoundResultDto
import view.OutputView

object PrintManager {

    fun printGameStartGuide() {
        OutputView.printGameStartGuide()
    }

    fun printRoundStartGuide(gameState: GameState) {
        OutputView.printRoundStartGuide(GameStateDto.from(gameState))
    }

    fun printCommandGuide() {
        OutputView.printCommandGuide()
    }

    fun printInfoMessage(message: String) {
        OutputView.printInfoMessage(message)
    }

    fun printGameStatus(gameState: GameState, board: Board) {
        OutputView.printGameStatus(GameStateDto.from(gameState), BoardDto.from(board))
    }

    fun printRoundResult(gameState: GameState, board: Board) {
        OutputView.printRoundResult(RoundResultDto.from(gameState, board))
    }

    fun printGameResult(gameState: GameState) {
        OutputView.printGameResult(GameStateDto.from(gameState))
    }
}