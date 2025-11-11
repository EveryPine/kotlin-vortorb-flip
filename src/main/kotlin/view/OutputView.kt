package view

object OutputView {

    fun printCommandGuide() {
        println("명령어를 입력해주세요")
    }

    fun printInfoMessage(message: String) {
        println("[INFO] $message")
    }
}