package command

import domain.GameState

class ExitCommand(private val gameState: GameState) : Command {

    private val requiredArgsSize: Int = 0

    override fun execute(args: List<String>) {
        validateArgsSize(args, requiredArgsSize)
        gameState.exit()
    }
}