package domain

import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.GRID_CELL_COUNT
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND

object BoardFactory {

    fun create(cards: List<Card>): Board {
        validateCardsSize(cards)
        val cardMap: Map<Position, Card> = mapCards(cards)

        return Board(cardMap)
    }

    private fun validateCardsSize(cards: List<Card>) {
        if (cards.size != GRID_CELL_COUNT) {
            throw IllegalArgumentException("카드 개수는 ${GRID_CELL_COUNT}개여야 합니다.")
        }
    }

    private fun mapCards(cards: List<Card>): HashMap<Position, Card> {
        val cardMap: HashMap<Position, Card> = HashMap()

        for (row in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            for (column in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
                val position: Position = Position.of(row, column)
                cardMap[position] = cards[position.toIndex()]
            }
        }

        return cardMap
    }
}