package command

import manager.GameManager

class ExitCommand(private val gameManager: GameManager) : Command {

    override fun execute(args: List<String>) {
        validate(args)
        gameManager.requestExitGame()
    }

    private fun validate(args: List<String>) {
        if (args.isNotEmpty()) {
            throw IllegalArgumentException("exit 명령은 인자를 받을 수 없습니다")
        }
    }
}