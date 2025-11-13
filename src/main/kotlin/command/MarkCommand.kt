package command

import domain.Position
import manager.GameManager

class MarkCommand(private val gameManager: GameManager) : Command {

    private val requiredArgsSize: Int = 2

    override fun execute(args: List<String>) {
        validate(args)
        val position = args[0]
        val symbol = args[1].single()
        gameManager.requestMarkCard(position, symbol)
    }

    private fun validate(args: List<String>) {
        if (args.size != requiredArgsSize) {
            throw IllegalArgumentException("mark 명령 인자의 개수가 올바르지 않습니다.")
        }
    }
}