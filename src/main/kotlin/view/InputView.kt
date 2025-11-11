package view

object InputView {

    fun validInput(): String {
        val input: String? = readLine()
        validate(input)

        return input!!.trim()
    }

    private fun validate(input: String?) {
        if (input == null || input.isEmpty()) {
            throw IllegalArgumentException("입력이 비어있습니다")
        }
    }
}