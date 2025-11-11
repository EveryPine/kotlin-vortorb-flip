package command

import domain.Position
import manager.GameManager

class UnmarkCommand(private val gameManager: GameManager): Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validate(args)
        val position = Position.of(args[0])
        gameManager.requestUnmarkCard(position)
    }

    private fun validate(args: List<String>) {
        if (args.size != requiredArgsSize) {
            throw IllegalArgumentException("unmark 명령 인자의 개수가 올바르지 않습니다.")
        }
    }
}