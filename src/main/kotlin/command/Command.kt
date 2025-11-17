package command

interface Command {

    fun execute(args: List<String>)

    fun validateArgsSize(args: List<String>, requiredArgsSize: Int) {
        if (args.size != requiredArgsSize) {
            throw IllegalArgumentException("명령 인자의 개수가 올바르지 않습니다.")
        }
    }
}
