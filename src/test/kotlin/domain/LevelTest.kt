package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

@DisplayName("Level 클래스의")
class LevelTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `레벨 객체를 생성한다`() {
            // given
            val expected: Int = 1

            // when
            val actual: Level = Level.of(expected)

            // then
            assertThat(actual).extracting("value")
                .isEqualTo(expected)
        }

        @Test
        fun `최대 레벨보다 높은 경우 예외가 발생한다`() {
            // given
            val value: Int = 11

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Level.of(value)
            }
        }

        @Test
        fun `최대 레벨보다 낮은 경우 예외가 발생한다`() {
            // given
            val value: Int = 0

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Level.of(value)
            }
        }
    }

    @Nested
    @DisplayName("next 메소드는")
    inner class Next {

        @ParameterizedTest
        @CsvSource("3,2", "1,1")
        fun `찌리리공이 발견된 경우 레벨을 1만큼 감소시킨다`(value: Int, expected: Int) {
            // given
            val voltorbFound: Boolean = true
            val level: Level = Level.of(value)

            // when
            level.next(voltorbFound)

            // then
            assertThat(level).extracting("value")
                .isEqualTo(expected)
        }

        @ParameterizedTest
        @CsvSource("3,4", "6,6")
        fun `찌리리공이 발견되지 않은 경우 레벨을 1만큼 증가시킨다`(value: Int, expected: Int) {
            // given
            val voltorbFound: Boolean = false
            val level: Level = Level.of(value)

            // when
            level.next(voltorbFound)

            // then
            assertThat(level).extracting("value")
                .isEqualTo(expected)
        }
    }
}