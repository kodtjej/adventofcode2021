package com.kodtjej.adventofcode2021.day8

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day8/input")

    val outputValues = getOutputValues(input).map { it.split(" ").filter { it != "" } }
    val res = outputValues.flatMap { it.map { decideOnNumber(it) } }.filter { it != -1 }
    println(res.size)
}


fun getOutputValues(input: List<String>): List<String> {
    val outputRegex = Regex("^.+\\|(.*)\$")
    return input.map { outputRegex.find(it)?.groupValues?.get(1) ?: "" }
}

fun decideOnNumber(input: String): Int {

    when (input.length) {
        2 -> return 1
        3 -> return 7
        4 -> return 4
        7 -> return 8
    }

    return -1
}