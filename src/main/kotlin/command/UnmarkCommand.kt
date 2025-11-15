package command

import domain.Board
import domain.Position
import manager.PrintManager

class UnmarkCommand(private val board: Board) : Command {

    private val requiredArgsSize: Int = 1

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)

        val position: Position = Position.of(args[0])
        board.unmark(position)
        PrintManager.printInfoMessage("$position 카드 마킹을 해제했습니다.")
    }
}