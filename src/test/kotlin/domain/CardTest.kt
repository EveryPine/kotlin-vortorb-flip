package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

@DisplayName("Card 클래스의")
class CardTest {

    @Nested
    @DisplayName("addTo 메소드는")
    inner class AddTo {

        @Test
        fun `숫자 카운트에 현재 카드의 숫자값을 더한다`() {
            // given
            val card: Card = Card(CardType.TWO)
            var actual: Int = 5
            val expected = 7

            // when
            actual = card.addTo(actual)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("isTypeOf 메소드는")
    inner class IsTypeOf {

        @Test
        fun `카드의 타입이 요청한 타입과 같은 경우 true를 반환한다`() {
            // given
            val card: Card = Card(CardType.TWO)
            val expected = true

            // when
            val actual = card.isTypeOf(CardType.TWO)

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `카드의 타입이 요청한 타입과 다른 경우 false를 반환한다`() {
            // given
            val card: Card = Card(CardType.TWO)
            val expected = false

            // when
            val actual = card.isTypeOf(CardType.THREE)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("isFlipped 메소드는")
    inner class IsFlipped {

        @Test
        fun `카드가 뒤집어진 경우 true를 반환한다`() {
            // given
            val card: Card = Card(CardType.TWO, CardState.FLIPPED)
            val expected = true

            // when
            val actual = card.isFlipped()

            assertEquals(expected, actual)
        }

        @ParameterizedTest
        @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = ["FLIPPED"])
        fun `카드가 뒤집어지지 않은 경우 false를 반환한다`(state: CardState) {
            // given
            val card: Card = Card(CardType.TWO, state)
            val expected = false

            // when
            val actual = card.isFlipped()

            assertEquals(expected, actual)
        }
    }

}