package domain

import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND

class Board(level: Int) {

    private val cardMap: HashMap<Position, Card> = HashMap()

    init {
        reset(level)
    }

    fun reset(level: Int) {
        val cards: List<Card> = getCards(level)
        mapCards(cards)
    }

    private fun getCards(level: Int): List<Card> {
        val cardConfig: CardConfig = CardConfigProvider.provide(level)

        return cardConfig.toList()
    }

    private fun mapCards(cards: List<Card>) {
        for (row in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            for (column in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
                val position: Position = Position.of(row, column)
                cardMap[position] = cards[position.toIndex()]
            }
        }
    }

    fun getCardMap(): HashMap<Position, Card> = cardMap

    fun flipOf(position: Position) {
        cardMap[position]!!.flip()
    }

    fun mark(position: Position, symbol: Char) {
        cardMap[position]!!.markAs(symbol)
    }

    fun unmark(position: Position) {
        cardMap[position]!!.unmark()
    }

    fun calculateRowLineHint(row: Char): LineHint {
        var numberSum: Int = 0
        var voltorbCount: Int = 0

        for (column in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!

            numberSum = card.addTo(numberSum)
            if (card.isTypeOf(CardType.VOLTORB)) {
                voltorbCount++
            }
        }

        return LineHint(numberSum, voltorbCount)
    }

    fun calculateColumnLineHint(column: Int): LineHint {
        var numberSum: Int = 0
        var voltorbCount: Int = 0

        for (row in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!

            numberSum = card.addTo(numberSum)
            if (card.isTypeOf(CardType.VOLTORB)) {
                voltorbCount++
            }
        }

        return LineHint(numberSum, voltorbCount)
    }

    fun calculateCoins(): Int {
        var coins: Int = 1

        for (card: Card in cardMap.values) {
            if (card.isFlipped()) {
                coins = card.multiplyTo(coins)
            }
        }

        return coins
    }

    fun isAllFound(type: CardType): Boolean {
        for (card: Card in cardMap.values) {
            if (card.isTypeOf(type) && !card.isFlipped()) {
                return false
            }
        }
        return true
    }

    fun isVoltorbFound(): Boolean {
        for (card: Card in cardMap.values) {
            if (card.isTypeOf(CardType.VOLTORB) && card.isFlipped()) {
                return true
            }
        }

        return false
    }
}