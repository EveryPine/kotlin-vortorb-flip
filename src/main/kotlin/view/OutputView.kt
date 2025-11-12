package view

import domain.Card
import domain.CardState
import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND
import domain.Position
import dto.BoardDto
import dto.CardDto
import dto.GameStateDto
import utils.ConsoleColor

object OutputView {

    fun printCommandGuide() {
        println("명령어를 입력해주세요")
    }

    fun printInfoMessage(message: String) {
        println("[INFO] $message")
    }

    fun printStatus(gameStateDto: GameStateDto, boardDto: BoardDto) {
        println("누적 코인: ${gameStateDto.totalCoins}개 | 현재 코인: ${boardDto.roundCoins} | 레벨: ${gameStateDto.level}\n")
        println("     1    2    3    4    5")
        println("  +----+----+----+----+----+")
        for (row: Char in ROW_LOWER_BOUND .. ROW_UPPER_BOUND) {
            print("$row | ")
            for (column: Int in COLUMN_LOWER_BOUND .. COLUMN_UPPER_BOUND) {
                printCard(boardDto.cardMap[Position.of(row, column)]!!)
            }
            printHorizontalCardHint(row, boardDto)
        }
        println("  +----+----+----+----+----+")
        printVerticalCardHint(boardDto)
        println()
    }

    private fun printCard(cardDto: CardDto) {
        if (cardDto.state == CardState.NORMAL) {
            print(String.format("%2s", "?"))
        }
        if (cardDto.state == CardState.FLIPPED) {
            print(String.format("%2s", cardDto.symbol))
        }
        if (cardDto.state == CardState.MARKED) {
            print(ConsoleColor.yellow(String.format("%2s", cardDto.markSymbol)))
        }
        print(" | ")
    }

    private fun printHorizontalCardHint(row: Char, boardDto: BoardDto) {
        print(ConsoleColor.blue(String.format("%2s", boardDto.rowNumberCountMap[row])))
        println(ConsoleColor.red(String.format("%2s", boardDto.rowVoltorbCountMap[row])))
    }

    private fun printVerticalCardHint(boardDto: BoardDto) {
        print("    ")
        for (column: Int in COLUMN_LOWER_BOUND .. COLUMN_UPPER_BOUND) {
            print(ConsoleColor.blue(String.format("%2s   ", boardDto.columnNumberCountMap[column])))
        }
        print("\n    ")
        for (column: Int in COLUMN_LOWER_BOUND .. COLUMN_UPPER_BOUND) {
            print(ConsoleColor.red(String.format("%2s   ", boardDto.columnVoltorbCountMap[column])))
        }
        println()
    }
}