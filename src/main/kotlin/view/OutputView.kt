package view

import domain.CardState
import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.MAX_LEVEL
import domain.Constants.MIN_LEVEL
import domain.Constants.ROUND_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND
import domain.Position
import dto.BoardDto
import dto.CardDto
import dto.GameStateDto
import dto.RoundResultDto
import utils.ConsoleColor

object OutputView {

    fun printGameStartGuide() {
        println("찌리리공 뒤집기 게임을 시작합니다!")
    }

    fun printRoundStartGuide(gameStateDto: GameStateDto) {
        println("${gameStateDto.round} 라운드가 시작되었습니다.")
    }

    fun printCommandGuide() {
        println("명령어를 입력해주세요.")
        print(">> ")
    }

    fun printInfoMessage(message: String) {
        println("[INFO] $message")
    }

    fun printGameStatus(gameStateDto: GameStateDto, boardDto: BoardDto) {
        println("\n라운드: ${gameStateDto.round} | 레벨: ${gameStateDto.level} | 누적 코인: ${gameStateDto.totalCoins}개 | " +
                "현재 코인: ${boardDto.roundCoins}개\n")
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
            print(ConsoleColor.green(String.format("%2s", cardDto.symbol)))
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

    fun printRoundResult(roundResultDto: RoundResultDto) {
        println()
        printRoundOverReason(roundResultDto)
        print("${roundResultDto.round} 라운드가 종료되었습니다. ")
        printObtainedCoins(roundResultDto)
        if (roundResultDto.round != ROUND_UPPER_BOUND) {
            printLevelChangedGuide(roundResultDto)
        }
        println()
    }

    private fun printRoundOverReason(roundResultDto: RoundResultDto) {
        if (!roundResultDto.voltorbFound) {
            println("축하합니다! 이번 라운드에 존재하는 모든 2와 3 카드를 뒤집었습니다.")
            return
        }
        println("찌리리공을 뒤집었습니다!")
    }

    private fun printObtainedCoins(roundResultDto: RoundResultDto) {
        if (!roundResultDto.voltorbFound) {
            println("이번 라운드에서 코인 ${roundResultDto.obtainedCoins}개를 획득했습니다.")
        }
        if (roundResultDto.voltorbFound) {
            println("이번 라운드에서는 코인을 획득하지 못했습니다.")
        }
    }

    private fun printLevelChangedGuide(roundResultDto: RoundResultDto) {
        if (roundResultDto.voltorbFound && roundResultDto.level != MIN_LEVEL) {
            println("레벨이 1 내려갔습니다.")
            return
        }
        if (!roundResultDto.voltorbFound && roundResultDto.level != MAX_LEVEL) {
            println("레벨이 1 올라갔습니다.")
            return
        }
        if (roundResultDto.voltorbFound && roundResultDto.level == MIN_LEVEL) {
            println("레벨이 더 이상 내려가지 않습니다.")
            return
        }
        if (!roundResultDto.voltorbFound && roundResultDto.level == MAX_LEVEL) {
            println("레벨이 더 이상 올라가지 않습니다.")
            return
        }
    }


    fun printGameResult(gameStateDto: GameStateDto) {
        println("게임이 종료되었습니다.")
        println("--- 게임 결과 ---")
        println("누적 코인: ${gameStateDto.totalCoins}개")
    }
}