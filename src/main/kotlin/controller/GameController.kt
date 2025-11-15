package controller

import domain.Board
import domain.GameState
import manager.CommandManager
import manager.GameStatusManager
import manager.PrintManager
import view.InputView

class GameController(
    private val gameState: GameState,
    private var board: Board
) {

    fun run() {
        var input: String

        CommandManager.initCommandMap(gameState, board)
        PrintManager.printGameStartGuide()
        while (!GameStatusManager.isGameOver(gameState)) {
            PrintManager.printRoundStartGuide(gameState)
            while (!GameStatusManager.isRoundOver(gameState, board)) {
                PrintManager.printGameStatus(gameState, board)
                input = requestValidInput()
                invokeCommand(input)
            }
            if (!GameStatusManager.isGameOver(gameState)) {
                PrintManager.printRoundResult(gameState, board)
                GameStatusManager.rerollRound(gameState, board)
            }
        }
        PrintManager.printGameResult(gameState)
    }

    private fun requestValidInput(): String {
        var input: String? = null

        while (input == null) {
            try {
                PrintManager.printCommandGuide()
                input = InputView.validInput()
            } catch (e: IllegalArgumentException) {
                PrintManager.printInfoMessage(e.message!!)
            }
        }

        return input
    }

    private fun invokeCommand(input: String) {
        try {
            CommandManager.execute(input)
        } catch (e: IllegalArgumentException) {
            PrintManager.printInfoMessage(e.message!!)
        } catch (e: IllegalStateException) {
            PrintManager.printInfoMessage(e.message!!)
        }
    }
}