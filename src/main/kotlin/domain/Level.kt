package domain

import domain.Constants.MAX_LEVEL
import domain.Constants.MIN_LEVEL
import dto.LevelDto
import java.util.Objects
import kotlin.math.max
import kotlin.math.min

class Level private constructor(
    private var value: Int = 1
) {

    companion object {
        fun of(value: Int): Level {
            validateValue(value)
            return Level(value)
        }

        private fun validateValue(value: Int) {
            if (value > MAX_LEVEL) {
                throw IllegalArgumentException("레벨은 ${MAX_LEVEL}을 넘을 수 없습니다.")
            }
            if (value < MIN_LEVEL) {
                throw IllegalArgumentException("레벨은 ${MIN_LEVEL}보다 커야 합니다.")
            }
        }
    }

    fun next(voltorbFound: Boolean) {
        if (voltorbFound) {
            value = max(value - 1, MIN_LEVEL)
            return
        }
        value = min(value + 1, MAX_LEVEL)
    }

    fun toDto(): LevelDto {
        return LevelDto.of(value)
    }

    override fun equals(other: Any?): Boolean {
        other as Level
        return value == other.value
    }

    override fun hashCode(): Int {
        return Objects.hash(value)
    }
}