package dto

import domain.Card
import domain.CardState

data class CardDto(
    val symbol: Char,
    val markSymbol: Char?,
    val state: CardState,
) {
    companion object {

        fun from(card: Card): CardDto {
            return CardDto(
                symbol = card.getType().symbol,
                markSymbol = card.getMarkType()?.symbol,
                state = card.getState()
            )
        }
    }
}