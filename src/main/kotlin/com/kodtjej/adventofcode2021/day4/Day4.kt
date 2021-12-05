package com.kodtjej.adventofcode2021.day4

import com.kodtjej.adventofcode2021.day1.readFile
import java.lang.Error

fun main() {

    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day4/input")
    val numbersCalled = input[0].split(",").map { it.toInt() }
    val boards = parseBoards(input.subList(1, input.size))
    val checkedBoards = checkAllBoards(boards, numbersCalled)

    val firstBoardToWin = checkWinningBoard(checkedBoards, numbersCalled)
    val lastBoardToWin = checkLosingBoard(checkedBoards, numbersCalled)
    val sumOfUnmarkedWinner = firstBoardToWin.getSumOfUnmarked()
    val sumOfUnmarkedLoser = lastBoardToWin.getSumOfUnmarked()
    val finalScoreWinner = sumOfUnmarkedWinner * firstBoardToWin.winningNumberCalled
    val finalScoreLoser = sumOfUnmarkedLoser * lastBoardToWin.winningNumberCalled

    println("---- Part 1 ----")
    println("Final score: $finalScoreWinner")
    println("---- Part 2 ----")
    println("Final score: $finalScoreLoser")
}

fun checkWinningBoard(boards: List<Board>, numbersCalled: List<Int>): Board {
    for (n in numbersCalled) {
        val winb = boards.filter {
            it.winningNumberCalled == n
        }
        if (winb.isNotEmpty()) {
            return winb[0]
        }
    }
    throw Error("no winner")
}

fun checkLosingBoard(boards: List<Board>, numbersCalled: List<Int>): Board {
    for (n in numbersCalled.reversed()) {
        val losingBoard = boards.filter {
            it.winningNumberCalled == n
        }
        if (losingBoard.isNotEmpty()) {
            return losingBoard[0]
        }
    }
    throw Error("no winner")
}


fun checkAllBoards(boards: List<Board>, numbersCalled: List<Int>): List<Board> {
    val checkedBoards = mutableListOf<Board>()
    for (board in boards) {
        val b = checkBoard(board, numbersCalled, 0)
        checkedBoards.add(b)
    }
    return checkedBoards
}

fun checkBoard(board: Board, numbersToCall: List<Int>, lastNumber: Int): Board {
    if (board.hasWon()) {
        board.winningNumberCalled = lastNumber
        return board
    }
    //check if there are no more numbers to mark
    if (numbersToCall.isEmpty()) {
        return board
    }
    //mark board
    board.checkNumber(numbersToCall[0])

    return checkBoard(
        board,
        numbersToCall.subList(1, numbersToCall.size),
        numbersToCall[0]
    )
}

fun parseBoards(list: List<String>):List<Board>{
    val boards = mutableListOf<Board>()
    val removedEmptyRows = list.filter { it.isNotEmpty() }.map { it.split(" ") }
    val removeEmptyValues = removedEmptyRows.map { it.filter { it.isNotEmpty() } }

    for (i in removeEmptyValues.indices step 5){
        val b = Board()
        for (row in 0..4) {
            for (col in 0..4) {
                b.numbers[row][col] = removeEmptyValues[i+row][col].toInt()
            }
        }
        boards.add(b)
    }

    return boards
}

class Board {
    val numbers: Array<IntArray> = Array(5) { IntArray(5) { 0 } }
    val marked: Array<BooleanArray> = Array(5) { BooleanArray(5) { false } }
    var winningNumberCalled: Int = 0

    fun hasWon(): Boolean {
        var winner = false
        outerloop@for (row in 0..4) {
            for (col in 0..4) {
                if (!marked[row][col]) {
                    continue@outerloop
                }
                if (marked[row][col] && col == 4) {
                    winner = true
                }
            }
        }

        outerloop@ for (row in 0..4) {
            for (col in 0..4) {
                if (!marked[col][row]) {
                    continue@outerloop
                }
                if (marked[col][row] && col == 4) {
                    winner = true
                }
            }
        }
        return winner
    }

    fun checkNumber(num: Int) {
        for (row in 0..4) {
            for (col in 0..4) {
                if (numbers[row][col] == num) {
                    marked[row][col] = true
                }
            }
        }
    }

    fun getSumOfUnmarked():Int{
        var sum = 0
        for (row in 0..4){
            for(col in 0..4) {
                if (!marked[row][col]) {
                    sum += numbers[row][col]
                }
            }
        }
        return sum
    }
}