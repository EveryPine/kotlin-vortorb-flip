package domain

enum class CardType(val factor: Int, val symbol: Char) {
    ONE(1, '1'),
    TWO(2, '2'),
    THREE(3, '3'),
    VOLTORB(0, 'X');

    companion object {
        fun of(symbol: Char): CardType {
            for (type in entries) {
                if (type.symbol == symbol) {
                    return type
                }
            }
            throw IllegalArgumentException("알 수 없는 카드 유형 심볼입니다")
        }
    }
}