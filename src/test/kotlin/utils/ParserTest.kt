package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Parser 클래스의")
class ParserTest {

    @Nested
    @DisplayName("parseToCardConfig 메소드는")
    inner class ParseToCardConfig {

        @Test
        fun `문자열을 카드 구성으로 파싱한다`() {
            // given
            val string: String = "1,2,3"
            val expected: List<String> = listOf("1", "2", "3")

            // when
            val actual = Parser.parseToCardConfig(string)

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `잘못된 구분자가 들어간 경우 예외가 발생한다`() {
            // given
            val string: String = "1;2;3"

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) { Parser.parseToCardConfig(string) }
        }
    }

    @Nested
    @DisplayName("parseToCommand 메소드는")
    inner class ParseToCommand {

        @Test
        fun `문자열을 명령어 리스트로 파싱한다`() {
            // given
            val string: String = "flip a b"
            val expected: List<String> = listOf("flip", "a", "b")

            // when
            val actual = Parser.parseToCommand(string)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("parseInt 메소드는")
    inner class ParseInt {

        @Test
        fun `문자열을 정수로 변환한다`() {
            // given
            val string: String = "1"
            val expected: Int = 1

            // when
            val actual = Parser.parseInt(string)

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `정수만으로 이루어져 있지 않은 문자열이 들어온 경우 예외가 발생한다`() {
            // given
            val string: String = "1a"

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) { Parser.parseInt(string) }
        }
    }
}