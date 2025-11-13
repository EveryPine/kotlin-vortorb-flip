package command

import domain.Position
import manager.GameManager

class UnmarkCommand(private val gameManager: GameManager) : Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)
        val position: String = args[0]
        gameManager.requestUnmarkCard(position)
    }
}