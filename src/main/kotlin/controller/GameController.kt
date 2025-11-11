package controller

import manager.CommandManager
import manager.GameManager
import view.InputView
import view.OutputView

class GameController(
    private val gameManager: GameManager,
    private val commandManager: CommandManager
) {

    fun run() {
        var input: String
        while (!gameManager.isGameOver()) {
            while (!gameManager.isRoundOver()) {
                OutputView.printCommandGuide()
                input = InputView.validInput()
                commandManager.setCommand(input)
                commandManager.execute()
            }
            gameManager.rerollRound()
        }
    }
}