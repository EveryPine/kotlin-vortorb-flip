package command

import manager.GameManager

class StatusCommand(private val gameManager: GameManager): Command {

    override fun execute(args: List<String>) {
        gameManager.requestPrintStatus()
    }
}