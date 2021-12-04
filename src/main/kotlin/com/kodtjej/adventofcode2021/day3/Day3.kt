package com.kodtjej.adventofcode2021.day3

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day3/input")
    val gammaRatePt1 = calculateRate(input, ::gammaPredicate)
    val epsilonRatePt1 = calculateRate(input, ::epsilonPredicate)
    val powerConsumption = gammaRatePt1 * epsilonRatePt1

    println("Part 1 gammaRate: $gammaRatePt1")
    println("Part 1 epsilonRate: $epsilonRatePt1")
    println("Part 1 power consumption: $powerConsumption")
}

fun gammaPredicate(zeros:Int, ones:Int):Boolean {
    return zeros > ones
}
fun epsilonPredicate(zeros:Int, ones:Int):Boolean {
    return zeros < ones
}

fun calculateRate(input: List<String>, predFn: (val1:Int, val2:Int) -> Boolean): Int {
    val flippedInput = makeColumnsIntoRows(input)
    var epsilonBit = ""
    for (column in flippedInput) {
        val count = column.groupingBy { it }.eachCount()
        if (predFn(count['0']!!, count['1']!!)) {
            epsilonBit += "0"
            continue
        }
        epsilonBit += "1"

    }

    return Integer.parseInt(epsilonBit, 2)
}

fun makeColumnsIntoRows(input: List<String>): List<String> {
    val flippedValues = mutableListOf<String>()

    for (column in 0 until input[0].length) {
        var newRow = ""
        for (row in input) {
            newRow += row[column]
        }
        flippedValues.add(newRow)
    }

    return flippedValues
}