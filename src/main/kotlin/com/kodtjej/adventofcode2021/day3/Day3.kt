package com.kodtjej.adventofcode2021.day3

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day3/input")
    val gammaRatePt1 = calculateGammaRate(input);
    val epsilonRatePt1 = calculateEpsilonRate(input)
    val powerConsumption = gammaRatePt1 * epsilonRatePt1

    println("Part 1 gammaRate: $gammaRatePt1")
    println("Part 1 epsilonRate: $epsilonRatePt1")
    println("Part 1 power consumption: $powerConsumption")
}

fun calculateGammaRate(input: List<String>): Int {
    // do stuff here
    val masseradInput = makeColumnsIntoRows(input);
    var gammaBit = "";
    for (column in masseradInput) {
        val count = column.groupingBy { it }.eachCount()
        if (count['0']!! > count['1']!!) {
            gammaBit += "0";
            continue
        }
        gammaBit += "1"

    }

    return Integer.parseInt(gammaBit, 2)
}

fun calculateEpsilonRate(input: List<String>): Int {
    val masseradInput = makeColumnsIntoRows(input)
    var epsilonBit = ""
    for (column in masseradInput) {
        val count = column.groupingBy { it }.eachCount()
        if (count['0']!! < count['1']!!) {
            epsilonBit += "0";
            continue
        }
        epsilonBit += "1"

    }

    return Integer.parseInt(epsilonBit, 2)
}

fun makeColumnsIntoRows(input: List<String>): List<String> {
    val omMippladLista = mutableListOf<String>()

    for (column in 0 until input[0].length) {
        var newRow = ""
        for (row in input) {
            newRow += row[column]
        }
        omMippladLista.add(newRow);
    }

    return omMippladLista
}