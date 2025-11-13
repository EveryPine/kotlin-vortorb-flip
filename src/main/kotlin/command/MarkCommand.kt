package command

import domain.Position
import manager.GameManager

class MarkCommand(private val gameManager: GameManager) : Command {

    private val requiredArgsSize: Int = 2

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)
        val position: String = args[0]
        val symbol: Char = args[1].single()
        gameManager.requestMarkCard(position, symbol)
    }
}