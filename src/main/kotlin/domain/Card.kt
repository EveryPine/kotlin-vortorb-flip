package domain

import view.OutputView

class Card(
    private val type: CardType,
    private var state: CardState = CardState.UNFLIPPED
) {

    fun flip() {
        if (state == CardState.FLIPPED) {
            OutputView.printInfoMessage("입력한 위치에 있는 카드는 이미 뒤집힌 상태입니다")
            return
        }
        state = CardState.FLIPPED
    }

    fun addTo(count: Int): Int {
        return count + type.factor
    }

    fun multiplyTo(coins: Int): Int {
        return coins * type.factor
    }

    fun isTypeOf(type: CardType): Boolean {
        return this.type == type
    }

    fun isFlipped(): Boolean {
        return state == CardState.FLIPPED
    }


}