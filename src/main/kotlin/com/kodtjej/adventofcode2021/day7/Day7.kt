package com.kodtjej.adventofcode2021.day7

import com.kodtjej.adventofcode2021.day1.readFile
import kotlin.math.absoluteValue

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day7/input")[0].split(",").map { it.toLong() }

    val leastAmountOfFuel = calculateMoves(input)
    println("---- Part 1 ----")
    println("Least amount of fuel: $leastAmountOfFuel")

}

fun calculateMoves(positions: List<Long>): Long {
    val maxPosition = positions.maxOrNull() ?: 0
    val positionToFuel = mutableMapOf<Long, Long>()
    for (pos in 1 until maxPosition) {
        var fuel = 0L
        for (crab in positions) {
            val moves = crab.minus(pos).absoluteValue
            fuel += moves
        }
        positionToFuel[pos] = fuel
    }
    return positionToFuel.values.minOrNull() ?: 0L
}