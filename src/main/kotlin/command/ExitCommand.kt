package command

import manager.GameManager

class ExitCommand(private val gameManager: GameManager) : Command {

    private val requiredArgsSize: Int = 0

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)
        gameManager.requestExitGame()
    }
}