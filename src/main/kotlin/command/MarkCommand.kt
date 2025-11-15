package command

import domain.Board
import domain.Position
import manager.PrintManager

class MarkCommand(private val board: Board) : Command {

    private val requiredArgsSize: Int = 2

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)

        val position: Position = Position.of(args[0])
        val symbol: Char = args[1].single()

        board.mark(position, symbol)
        PrintManager.printInfoMessage("$position 카드를 ${symbol.uppercase()}로 마킹했습니다.")
    }
}