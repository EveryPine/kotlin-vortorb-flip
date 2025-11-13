package command

import domain.Position
import manager.GameManager

class FlipCommand(private val gameManager: GameManager) : Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)
        gameManager.requestFlipCard(args[0])
    }
}