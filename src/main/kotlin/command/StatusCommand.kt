package command

import domain.Board
import domain.GameState
import manager.PrintManager

class StatusCommand(
    private val gameState: GameState,
    private val board: Board
) : Command {

    private val requiredArgsSize: Int = 0

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)

        PrintManager.printGameStatus(gameState, board)
    }
}