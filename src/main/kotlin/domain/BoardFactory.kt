package domain

import domain.Constants.GRID_CELL_COUNT

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

        for (position: Position in Position.all) {
            cardMap[position] = cards[position.toIndex()]
        }

        return cardMap
    }
}