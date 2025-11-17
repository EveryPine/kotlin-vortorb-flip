package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Column 클래스의")
class ColumnTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `열 객체를 생성한다`() {
            // given
            val column: Int = 3

            // when
            val actual: Column = Column.of(column)

            // then
            assertEquals(actual.value, column)
        }

        @Test
        fun `열 값이 범위를 벗어나는 경우 예외가 발생한다`() {
            // given
            val column: Int = 10

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Column.of(column)
            }
        }
    }
}