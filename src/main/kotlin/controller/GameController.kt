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
        while (!gameManager.isGameOver()) {
            while (!gameManager.isRoundOver()) {
                OutputView.printCommandGuide()
                input = InputView.validInput()
                invokeCommand(input)
            }
            gameManager.rerollRound()
        }
    }

    private fun invokeCommand(input: String) {
        try {
            commandInvoker.setCommand(input)
            commandInvoker.execute()
        } catch (e: IllegalArgumentException) {
            OutputView.printInfoMessage(e.message!!)
        }
    }
}