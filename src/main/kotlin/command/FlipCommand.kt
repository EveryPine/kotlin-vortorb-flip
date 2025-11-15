package command

import domain.Board
import domain.Position
import manager.PrintManager

class FlipCommand(private val board: Board) : Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)

        val position: Position = Position.of(args[0])
        board.flipOf(position)
        PrintManager.printInfoMessage("$position 카드를 뒤집었습니다.")
    }
}