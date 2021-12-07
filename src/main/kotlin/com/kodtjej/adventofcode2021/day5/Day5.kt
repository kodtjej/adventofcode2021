package com.kodtjej.adventofcode2021.day5

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day5/input")
    val amountOfLinesOverlapping = getOverLappingAmount(input)

    println(amountOfLinesOverlapping)
}

fun getOverLappingAmount(input: List<String>): Int {
    val lines = convertInputToLines(input)
    val horizontalAndVerticalLines = getHorizontalAndVerticalLines(lines)
    val diagonalLines = getDiagonalLines(lines)

    var matrix = Array(1000) { IntArray(1000) }
    matrix = plotHorizontalAndVerticalLines(horizontalAndVerticalLines, matrix)
    matrix = plotDiagonalLines(diagonalLines, matrix)

    return matrix.flatMap { it -> it.filter { it > 1 } }.size
}

fun getDiagonalLines(input: List<Line>): List<Line> {
    return input.filter { line -> !line.hasSameX() && !line.hasSameY() }
}

fun getHorizontalAndVerticalLines(input: List<Line>): List<Line> {
    return input.filter { line ->
        line.from.isHorizontallyAlignedWith(line.to) || line.from.isVerticallyAlignedWith(line.to)
    }
}

fun plotHorizontalAndVerticalLines(input: List<Line>, matrix: Array<IntArray>): Array<IntArray> {
    for (l in input) {
        val fromX = getFromValue(l.from.x, l.to.x)
        val toX = getToValue(l.from.x, l.to.x)
        val fromY = getFromValue(l.from.y, l.to.y)
        val toY = getToValue(l.from.y, l.to.y)

        for (x in fromX..toX) {
            for (y in fromY..toY) {
                matrix[y][x]++
            }
        }
    }
    return matrix
}

fun plotDiagonalLines(input: List<Line>, matrix: Array<IntArray>): Array<IntArray> {
    for (line in input) {
        if (line.from <= line.to) {
            var walkingX = line.from.x
            var walkingY = line.from.y

            if (line.from.y < line.to.y) {
                while (walkingX <= line.to.x && walkingY <= line.to.y) {
                    matrix[walkingY][walkingX]++
                    walkingX++
                    walkingY++
                }
            } else {
                while (walkingX <= line.to.x && walkingY >= line.to.y) {
                    matrix[walkingY][walkingX]++
                    walkingX++
                    walkingY--
                }
            }
        } else {
            var walkingX = line.from.x
            var walkingY = line.from.y

            if (walkingY > line.to.y) {
                while (walkingX >= line.to.x && walkingY >= line.to.y) {
                    matrix[walkingY][walkingX]++
                    walkingX--
                    walkingY--
                }
            } else {
                while (walkingX >= line.to.x && walkingY <= line.to.y) {
                    matrix[walkingY][walkingX]++
                    walkingX--
                    walkingY++
                }
            }
        }

    }
    return matrix
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
        val l = Line(Point(m[0][0], m[0][1]), Point(m[1][0], m[1][1]))

        lines.add(l)
    }
    return lines
}

class Line(val from: Point, val to: Point) {

    override fun toString(): String {
        return "From X: ${from.x} to X: ${to.x}, from Y: ${from.y} to Y: ${to.y}"
    }

    fun hasSameX(): Boolean {
        return from.x == to.x
    }

    fun hasSameY(): Boolean {
        return from.y == to.y
    }
}

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    fun isHorizontallyAlignedWith(other: Point) = x == other.x
    fun isVerticallyAlignedWith(other: Point) = y == other.y

    override fun compareTo(other: Point): Int {
        if (this.x < other.x) return -1
        if (this.x > other.x) return 1
        if (this.y < other.y) return -1
        if (this.y > other.y) return 1

        return 0
    }
}