package command

import manager.GameManager

class StatusCommand(private val gameManager: GameManager) : Command {

    override fun execute(args: List<String>) {
        validate(args)
        gameManager.requestPrintStatus()
    }

    private fun validate(args: List<String>) {
        if (args.isNotEmpty()) {
            throw IllegalArgumentException("status 명령어는 인자를 받을 수 없습니다")
        }
    }
}