package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Coin 클래스의")
class CoinTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `코인 객체를 생성한다`() {
            // given
            val value: Int = 300

            // when
            val actual: Coin = Coin.of(value)

            // then
            assertThat(actual).extracting("value")
                .isEqualTo(value)
        }

        @Test
        fun `음수값이 들어올 경우 예외가 발생한다`() {
            // given
            val value: Int = -100

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Coin.of(value)
            }
        }
    }

    @Nested
    @DisplayName("add 매소드는")
    inner class Add {

        @Test
        fun `코인을 추가한다`() {
            // given
            val actual: Coin = Coin.of(100)
            val gained: Coin = Coin.of(50)
            val expected: Int = 150

            // when
            actual.add(gained)

            // then
            assertThat(actual).extracting("value")
                .isEqualTo(expected)
        }
    }
}