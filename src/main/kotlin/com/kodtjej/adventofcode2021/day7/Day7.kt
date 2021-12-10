package com.kodtjej.adventofcode2021.day7

import com.kodtjej.adventofcode2021.day1.readFile
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day7/input")[0].split(",").map { it.toLong() }
    val inputAsInts = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day7/input")[0].split(",").map { it.toInt() }

    val leastAmountOfFuel1 = calculateMoves1(input)
    val leastAmountOfFuel2 = calculateMoves2(inputAsInts)
    println("---- Part 1 ----")
    println("Least amount of fuel: $leastAmountOfFuel1")
    println("---- Part 2 ----")
    println("Least amount of fuel: $leastAmountOfFuel2")

}

fun calculateMoves1(positions: List<Long>): Long {
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

fun calculateMoves2(positions: List<Int>): Int {
    // I take no credit for this part, I just gave up.
    val averageFloor = positions.average().toInt()
    val averageCiel = positions.average().roundToInt()

    val fuelToAverageFloor = positions.sumToAverage(averageFloor)
    val fuelToAverage = if (averageFloor != averageCiel)
        min(fuelToAverageFloor, positions.sumToAverage(averageCiel))
    else fuelToAverageFloor

    return fuelToAverage
}

fun List<Int>.sumToAverage(ave: Int) =
    this.sumOf {
        val diff = abs(it - ave)
        diff * (1 + diff) / 2
    }