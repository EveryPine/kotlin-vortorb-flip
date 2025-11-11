package utils

object Parser {

    private const val CARD_CONFIG_DELIMITER = ","
    private const val COMMAND_DELIMITER = " "

    fun parseToCardConfig(string: String): List<String> {
        validateDelimiter(string, CARD_CONFIG_DELIMITER)
        return string.split(CARD_CONFIG_DELIMITER).toList()
    }

    fun parseToCommand(string: String): List<String> {
        return string.split(COMMAND_DELIMITER).toList()
    }

    fun parseInt(string: String): Int {
        try {
            return Integer.parseInt(string)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("파싱 대상 문자열에 숫자가 아닌 문자가 들어있습니다")
        }
    }


    private fun validateDelimiter(string: String, delimiter: String) {
        if (!string.contains(delimiter)) {
            throw IllegalArgumentException("파싱 대상 문자열에 구분자가 포함되어 있지 않습니다")
        }
    }
}