package utils

object ConsoleColor {

    private const val RED = "\u001B[31m"
    private const val YELLOW = "\u001B[33m"
    private const val BLUE = "\u001B[34m"
    private const val EXIT = "\u001B[0m"

    fun red(string: String): String {
        return RED + string + EXIT
    }

    fun yellow(string: String): String {
        return YELLOW + string + EXIT
    }

    fun blue(string: String): String {
        return BLUE + string + EXIT
    }
}