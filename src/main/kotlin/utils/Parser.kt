package utils

object Parser {

    private const val DELIMITER = ","

    fun parseString(string: String): List<String> {
        validateDelimiter(string)
        return string.split(DELIMITER).toList()
    }

    fun parseInt(string: String): Int {
        try {
            return Integer.parseInt(string)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("파싱 대상 문자열에 숫자가 아닌 문자가 들어있습니다")
        }
    }


    private fun validateDelimiter(string: String) {
        if (!string.contains(DELIMITER)) {
            throw IllegalArgumentException("파싱 대상 문자열에 구분자가 포함되어 있지 않습니다")
        }
    }
}