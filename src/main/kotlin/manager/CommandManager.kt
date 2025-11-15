package manager

import command.Command
import command.ExitCommand
import command.FlipCommand
import command.MarkCommand
import command.StatusCommand
import command.UnmarkCommand
import domain.Board
import domain.GameState
import utils.Parser

object CommandManager {

    private val commandMap: HashMap<String, Command> = hashMapOf()
    private var command: Command? = null
    private var args: List<String>? = null

    fun initCommandMap(gameState: GameState, board: Board) {
        commandMap["flip"] = FlipCommand(board)
        commandMap["mark"] = MarkCommand(board)
        commandMap["unmark"] = UnmarkCommand(board)
        commandMap["status"] = StatusCommand(gameState, board)
        commandMap["exit"] = ExitCommand(gameState)
    }

    fun execute(input: String) {
        val parsedCommand: List<String> = Parser.parseToCommand(input)
        val command: Command = selectCommand(parsedCommand[0])
        val args: List<String> = parsedCommand.drop(1)

        command.execute(args)
    }

    private fun selectCommand(command: String): Command {
        if (commandMap.containsKey(command)) {
            return commandMap[command]!!
        }
        throw IllegalArgumentException("$command 는 존재하지 않는 명령어입니다")
    }
}