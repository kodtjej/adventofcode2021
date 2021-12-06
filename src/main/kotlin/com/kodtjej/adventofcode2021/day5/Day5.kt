package com.kodtjej.adventofcode2021.day5

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day5/input")
    val amountOfLinesOverlapping = getOverLappingAmount(input)

    println(amountOfLinesOverlapping)
}

fun getOverLappingAmount(input: List<String>): Int {
    val lines = convertInputToLines(input)

    val matrix = Array(1000) { IntArray(1000) }
    for (l in lines) {
        val fromX = getFromValue(l.fromX, l.toX)
        val toX = getToValue(l.fromX, l.toX)
        val fromY = getFromValue(l.fromY, l.toY)
        val toY = getToValue(l.fromY, l.toY)

        for (x in fromX..toX) {
            for (y in fromY..toY) {
                matrix[y][x]++
            }
        }
    }

    val linesOverLapping = matrix.flatMap { it.filter { it > 1 } }.size

    return linesOverLapping
}

fun getFromValue(a: Int, b: Int): Int {
    return if (a < b) a else b
}

fun getToValue(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun convertInputToLines(input: List<String>): List<Line> {
    val lines = mutableListOf<Line>()
    for (i in input) {
        val r = Regex("(\\d{1,3},\\d{1,3})")
        val matches = r.findAll(i)

        val m = matches.map { listOf(it.value.split(",")[0].toInt(), it.value.split(",")[1].toInt()) }.toList()
        val l = Line(fromX = m[0][0], toX = m[1][0], fromY = m[0][1], toY = m[1][1])

        if (l.hasSameX() || l.hasSameY()) {
            // we only want horizontal and vertical lines
            lines.add(l)
        }

    }
    return lines
}

class Line {
    var fromX: Int = 0
    var toX: Int = 0
    var fromY: Int = 0
    var toY: Int = 0

    constructor(fromX: Int, fromY: Int, toX: Int, toY: Int) {
        this.fromX = fromX
        this.toX = toX
        this.fromY = fromY
        this.toY = toY
    }

    override fun toString(): String {
        return "From X: $fromX to X: $toX, from Y: $fromY to Y: $toY"
    }

    fun hasSameX(): Boolean {
        return fromX == toX
    }

    fun hasSameY(): Boolean {
        return fromY == toY
    }
}