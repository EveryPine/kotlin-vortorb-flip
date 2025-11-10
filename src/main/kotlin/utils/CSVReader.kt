package utils

import java.io.File
import java.io.IOException

object CSVReader {

    fun readBody(absoluteFilePath: String): List<String> {
        try {
            val file = File(absoluteFilePath)
            val lines = file.readLines()

            return lines.drop(1)
        } catch (e: IOException) {
            throw IOException(absoluteFilePath, e)
        }
    }
}