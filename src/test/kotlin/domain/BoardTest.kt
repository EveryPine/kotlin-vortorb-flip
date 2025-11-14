package domain

import domain.Constants.GRID_EDGE_LENGTH
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Board 클래스의")
class BoardTest {

    @Nested
    @DisplayName("calculateRowLineHint 메소드는")
    inner class CalculateRowLineHint {

        @Test
        fun `행의 카드 힌트를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            val board: Board = Board(cards)
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
            val cards: List<Card> = provideCards()
            val board: Board = Board(cards)
            val column: Int = 1
            val expected: LineHint = LineHint(6, 2)

            // when
            val actual: LineHint = board.calculateColumnLineHint(column)

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("calculateCoins 메소드는")
    inner class CalculateCoins {

        @Test
        fun `보드판의 모든 숫자 카드의 곱을 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, listOf(
                Position.of('A', 3),
                Position.of('A', 4),
                Position.of('C', 1),
                Position.of('D', 1),
            ))
            val board: Board = Board(cards)
            val expected: Int = 2 * 2 * 3 * 3

            // when
            val actual: Int = board.calculateCoins()

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("isAllTwoFound 메소드는")
    inner class IsAllTwoFound {

        @Test
        fun `모든 숫자 2 카드가 뒤집어졌을 경우 true를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, null)
            val board = Board(cards)
            val expected: Boolean = true

            // when
            val actual: Boolean = board.isAllFound(CardType.TWO)

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `적어도 하나의 숫자 2 카드가 뒤집어지지 않았을 경우 false를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, listOf(Position.of('A', 1)))
            val board = Board(cards)
            val expected: Boolean = false

            // when
            val actual: Boolean = board.isAllFound(CardType.TWO)

            // then
            assertEquals(expected, actual)
        }

    }

    @Nested
    @DisplayName("isAllThreeFound 메소드는")
    inner class IsAllThreeFound {

        @Test
        fun `모든 숫자 3 카드가 뒤집어졌을 경우 true를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, null)
            val board = Board(cards)
            val expected: Boolean = true

            // when
            val actual: Boolean = board.isAllFound(CardType.THREE)

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `적어도 하나의 숫자 3 카드가 뒤집어지지 않았을 경우 false를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            flipAll(cards, listOf(Position.of('A', 2)))
            val board = Board(cards)
            val expected: Boolean = false

            // when
            val actual: Boolean = board.isAllFound(CardType.THREE)

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
            val board = Board(cards)
            val expected: Boolean = true

            // when
            val actual: Boolean = board.isVoltorbFound()

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `모든 찌리리공 카드가 뒤집어지지 않은 경우 false를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            val board = Board(cards)
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