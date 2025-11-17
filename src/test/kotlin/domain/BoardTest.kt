package domain

import domain.Constants.GRID_EDGE_LENGTH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import kotlin.test.Test

@DisplayName("Board 클래스의")
open class BoardTest {

    @Nested
    @DisplayName("init 블록은")
    inner class Init {

        @Test
        fun `모든 보드판 셀에 카드가 존재하는지 검증한다`() {
            // given
            val cards: List<Card> = provideCards()

            // when

            // then
            assertDoesNotThrow {
                BoardFactory.create(cards)
            }
        }

        @Test
        fun `어떤 한 셀에 카드가 없는 경우 예외가 발생한다`() {
            // given
            val cards: MutableList<Card> = provideCards().toMutableList()
            cards.removeAt(0)

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                BoardFactory.create(cards)
            }
        }
    }

    @Nested
    @DisplayName("calculateRowLineHint 메소드는")
    inner class CalculateRowLineHint {

        @Test
        fun `행의 카드 힌트를 반환한다`() {
            // given
            val board: Board = BoardFactory.create(provideCards())
            val row: Char = 'A'
            val expected: LineHint = LineHint(7, 2)

            // when
            val actual: LineHint = board.calculateRowLineHint(row)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("calculateColumnLineHint 메소드는")
    inner class CalculateColumnLineHint {

        @Test
        fun `열의 카드 힌트를 반환한다`() {
            // given
            val board: Board = BoardFactory.create(provideCards())
            val column: Int = 1
            val expected: LineHint = LineHint(6, 2)

            // when
            val actual: LineHint = board.calculateColumnLineHint(column)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("calculateObtainedCoin 메소드는")
    inner class CalculateEarnedCoin {

        @Test
        fun `획득한 코인을 계산하여 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, listOf(
                Position.of('A', 3),
                Position.of('A', 4),
                Position.of('C', 1),
                Position.of('D', 1),
            ))
            val board: Board = BoardFactory.create(cards)
            val expected: Coin = Coin.of(2 * 2 * 3 * 3)

            // when
            val actual: Coin = board.calculateObtainedCoin()

            // then
            assertThat(actual).usingRecursiveComparison()
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("isAllFound 메소드는")
    inner class IsAllFound {

        @ParameterizedTest
        @EnumSource(value = CardType::class)
        fun `모든 요청한 유형의 카드가 뒤집어졌을 경우 true를 반환한다`(type: CardType) {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, null)

            val board: Board = BoardFactory.create(cards)
            val expected: Boolean = true

            // when
            val actual: Boolean = board.isAllFound(type)

            // then
            assertEquals(expected, actual)

        }

        @ParameterizedTest
        @EnumSource(value = CardType::class)
        fun `적어도 하나의 요청한 유형의 카드가 뒤집어지지 않았을 경우 false를 반환한다`(type: CardType) {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, listOf(
                Position.of('A', 1),
                Position.of('A', 2),
                Position.of('A', 3),
                Position.of('B', 2)))

            val board: Board = BoardFactory.create(cards)
            val expected: Boolean = false

            // when
            val actual: Boolean = board.isAllFound(type)

            // then
            assertEquals(expected, actual)
        }

    }

    @Nested
    @DisplayName("isVoltorbFound 메소드는")
    inner class IsVoltorbFound {

        @Test
        fun `적어도 하나의 찌리리공 카드가 뒤집어졌을 경우 true를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            cards[Position.of('A', 3).toIndex()].flip()

            val board: Board = BoardFactory.create(cards)

            val expected: Boolean = true

            // when
            val actual: Boolean = board.isVoltorbFound()

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `모든 찌리리공 카드가 뒤집어지지 않은 경우 false를 반환한다`() {
            // given
            val board: Board = BoardFactory.create(provideCards())
            val expected: Boolean = false

            // when
            val actual: Boolean = board.isVoltorbFound()

            // then
            assertEquals(expected, actual)
        }

    }

    /*
    * 다음 보드판을 생성하는 List<Card> 객체 반환
    *   2 3 X X 2
        3 1 1 1 1
        X 1 1 1 1
        X 1 1 1 1
        1 1 1 1 1
    * */
    protected fun provideCards(): List<Card> {
        val cardOrder: MutableList<Card> = mutableListOf()

        repeat(GRID_EDGE_LENGTH * GRID_EDGE_LENGTH) {
            cardOrder.add(Card(CardType.ONE))
        }

        cardOrder[Position.of('A', 1).toIndex()] = Card(CardType.TWO)
        cardOrder[Position.of('A', 5).toIndex()] = Card(CardType.TWO)

        cardOrder[Position.of('A', 2).toIndex()] = Card(CardType.THREE)
        cardOrder[Position.of('B', 1).toIndex()] = Card(CardType.THREE)

        cardOrder[Position.of('A', 3).toIndex()] = Card(CardType.VOLTORB)
        cardOrder[Position.of('A', 4).toIndex()] = Card(CardType.VOLTORB)
        cardOrder[Position.of('C', 1).toIndex()] = Card(CardType.VOLTORB)
        cardOrder[Position.of('D', 1).toIndex()] = Card(CardType.VOLTORB)

        return cardOrder
    }

    protected fun flipAll(cards: List<Card>, excluded: List<Position>?) {
        for ((index, card) in cards.withIndex()) {
            if (isExcluded(index, excluded)) {
                continue
            }

            card.flip()
        }
    }

    protected fun isExcluded(index: Int, excluded: List<Position>?): Boolean {
        if (excluded == null) {
            return false
        }

        return excluded.stream()
            .map{ position -> position.toIndex() }
            .toList().contains(index)
    }

}