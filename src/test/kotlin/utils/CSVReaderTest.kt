package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.IOException

@DisplayName("CSVReader 클래스의")
class CSVReaderTest {

    @Nested
    @DisplayName("readBody 메소드는")
    inner class ReadBody {

        @Test
        fun `csv 파일의 body를 읽는다`() {
            // given
            val absoluteFilePath: String = "src/test/resources/test.csv"
            val expected: List<String> = listOf(
                "body1,body2,body3",
                "body4,body5,body6"
            )

            // when
            val actual = CSVReader.readBody(absoluteFilePath)

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `요청한 경로에 csv 파일이 존재하지 않는 경우 예외를 발생시킨다`() {
            // given
            val absoluteFilePath: String = "src/test/resources/###"

            // when

            // then
            assertThrows(IOException::class.java) { CSVReader.readBody(absoluteFilePath) }
        }
    }
}