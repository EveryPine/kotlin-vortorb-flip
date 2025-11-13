package controller

import domain.CommandInvoker
import manager.GameManager
import view.InputView
import view.OutputView

class GameController(
    private val gameManager: GameManager,
    private val commandInvoker: CommandInvoker
) {

    fun run() {
        var input: String
        gameManager.printGameStartGuide()
        while (!gameManager.isGameOver()) {
            gameManager.printRoundStartGuide()
            while (!gameManager.isRoundOver()) {
                gameManager.printGameStatus()
                input = getValidInput()
                invokeCommand(input)
            }
            if (!gameManager.isGameOver()) {
                gameManager.printRoundResult()
                gameManager.rerollRound()
            }
        }
        gameManager.printGameResult()
    }

    private fun getValidInput(): String {
        var input: String? = null

        while (input == null) {
            try {
                OutputView.printCommandGuide()
                input = InputView.validInput()
            } catch (e: IllegalArgumentException) {
                OutputView.printInfoMessage(e.message!!)
            }
        }

        return input
    }

    private fun invokeCommand(input: String) {
        try {
            commandInvoker.setCommand(input)
            commandInvoker.execute()
        } catch (e: IllegalArgumentException) {
            OutputView.printInfoMessage(e.message!!)
        } catch (e: IllegalStateException) {
            OutputView.printInfoMessage(e.message!!)
        }
    }
}