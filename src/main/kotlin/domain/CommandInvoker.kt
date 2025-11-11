package domain

import command.Command
import command.ExitCommand
import command.FlipCommand
import command.MarkCommand
import command.StatusCommand
import command.UnmarkCommand
import manager.GameManager
import utils.Parser

class CommandInvoker(private val receiver: GameManager) {

    private val cache: HashMap<String, Command> = hashMapOf()
    private var command: Command? = null
    private var args: List<String>? = null

    fun setCommand(input: String) {
        val parsed: List<String> = Parser.parseToCommand(input)

        command = choiceCommand(parsed.first())
        args = parsed.drop(1)
    }

    private fun choiceCommand(command: String): Command {
        if (command == "flip") {
            return cache.getOrPut(command) { FlipCommand(receiver) }
        }
        if (command == "mark") {
            return cache.getOrPut(command) { MarkCommand() }
        }
        if (command == "unmark") {
            return cache.getOrPut(command) { UnmarkCommand() }
        }
        if (command == "status") {
            return cache.getOrPut(command) { StatusCommand() }
        }
        if (command == "exit") {
            return cache.getOrPut(command) { ExitCommand() }
        }
        throw IllegalArgumentException("$command 는 존재하지 않는 명령어입니다")
    }

    fun execute() {
        validate()
        command!!.execute(args!!)
    }

    private fun validate() {
        if (command == null) {
            throw IllegalArgumentException("명령어가 입력되지 않았습니다")
        }

        if (args!!.isEmpty()) {
            throw IllegalArgumentException("명령어 인자가 없습니다")
        }
    }
}