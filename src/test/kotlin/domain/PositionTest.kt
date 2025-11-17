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
            val row: Row = Row.of('A')
            val column: Column = Column.of(1)

            // when
            val actual: Position = Position.of(row, column)

            // then
            assertThat(actual).extracting("row", "column")
                .contains(row, column)
        }

        @Test
        fun `전달된 문자열의 Position 객체를 반환한다`() {
            // given
            val position: String = "A1"
            val expectedRow: Row = Row.of('A')
            val expectedColumn: Column = Column.of(1)

            // when
            val actual: Position = Position.of(position)

            assertThat(actual).extracting("row", "column")
                .contains(expectedRow, expectedColumn)
        }

        @Test
        fun `잘못된 형식의 위치 문자열이 전달된 경우 예외가 발생한다`() {
            // given
            val wrongPosition: String = "a"

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Position.of(wrongPosition)
            }
        }
    }

    @Nested
    @DisplayName("toIndex 메소드는")
    inner class ToIndex {

        @Test
        fun `1차원으로 평탄화한 인덱스를 반환한다`() {
            // given
            val position: Position = Position.of("C2")
            val expected: Int = 11

            // when
            val actual: Int = position.toIndex()

            // then
            assertEquals(expected, actual)
        }
    }
}