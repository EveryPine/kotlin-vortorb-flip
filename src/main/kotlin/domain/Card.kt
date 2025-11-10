package domain

class Card(
    private val type: CardType,
    private var state: CardState = CardState.UNFLIPPED
) {

    fun addTo(count: Int): Int {
        return count + type.factor
    }

    fun isVoltorb(): Boolean {
        return type == CardType.VOLTORB
    }


}