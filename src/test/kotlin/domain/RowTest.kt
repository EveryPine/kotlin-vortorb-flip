package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Row 클래스의")
class RowTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `행 객체를 반환한다`() {
            // given
            val row: Char = 'A'

            // when
            val actual: Row = Row.of(row)

            // then
            assertEquals(row, actual.value)
        }

        @Test
        fun `행 값이 범위를 벗어난 경우 예외가 발생한다`() {
            // given
            val row: Char = 'F'

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Row.of(row)
            }
        }
    }

}