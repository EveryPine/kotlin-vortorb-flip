package manager

import domain.Board
import domain.GameState
import dto.GameResultDto
import dto.GameStatusDto
import dto.RoundDto
import dto.RoundResultDto
import view.OutputView

object PrintManager {

    fun printGameStartGuide() {
        OutputView.printGameStartGuide()
    }

    fun printRoundStartGuide(gameState: GameState) {
        OutputView.printRoundStartGuide(gameState.getRound().toDto())
    }

    fun printCommandGuide() {
        OutputView.printCommandGuide()
    }

    fun printInfoMessage(message: String) {
        OutputView.printInfoMessage(message)
    }

    fun printGameStatus(gameState: GameState, board: Board) {
        OutputView.printGameStatus(GameStatusDto.of(gameState, board))
    }

    fun printRoundResult(gameState: GameState, board: Board) {
        OutputView.printRoundResult(RoundResultDto.from(gameState, board))
    }

    fun printGameResult(gameState: GameState) {
        OutputView.printGameResult(GameResultDto.from(gameState))
    }
}