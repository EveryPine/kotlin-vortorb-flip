package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Position 클래스의")
class PositionTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `전달된 열과 행의 Position 객체를 반환한다`() {
            // given
            val row = 'A'
            val column = 1

            // when
            val actual = Position.of(row, column)

            // then
            assertThat(actual).extracting("row", "column")
                .contains(row, column)
        }

        @Test
        fun `전달된 행 값이 범위를 벗어나는 경우 예외가 발생한다`() {
            // given
            val wrongRow = 'Z'
            val column = 1

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Position.of(wrongRow, column)
            }
        }

        @Test
        fun `전달된 열 값이 범위를 벗어나는 경우 예외가 발생한다`() {
            // given
            val row = 'A'
            val wrongColumn = 9

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Position.of(row, wrongColumn)
            }
        }

        @Test
        fun `전달된 문자열의 Position 객체를 반환한다`() {
            // given
            val position: String = "A1"
            val expectedRow = 'A'
            val expectedColumn = 1

            // when
            val actual = Position.of(position)

            assertThat(actual).extracting("row", "column")
                .contains(expectedRow, expectedColumn)
        }

        @Test
        fun `잘못된 형식의 위치 문자열이 전달된 경우 예외가 발생한다`() {
            // given
            val wrongPosition = "a"

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Position.of(wrongPosition)
            }
        }
    }
}