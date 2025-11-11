package command

import domain.Position
import manager.GameManager

class FlipCommand(private val gameManager: GameManager): Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validate(args)
        gameManager.requestFlipCard(Position.of(args[0]))
    }

    private fun validate(args: List<String>) {
        if (args.size != requiredArgsSize) {
            throw IllegalArgumentException("flip 명령 인자의 개수가 올바르지 않습니다.")
        }

        if (args.first().length != 2) {
            throw IllegalArgumentException("flip 명령 인자가 잘못되었습니다.")
        }
    }
}