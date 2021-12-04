package com.kodtjej.adventofcode2021.day1

import java.io.File


fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day1/input")
    val resultPart1 = amountOfTimesALargerNumberAppearsAfterASmaller(input)
    val resultMovingWindow = amountOfTimesInAMovingWindow(input)
    val resultPart2 = amountOfTimesALargerNumberAppearsAfterASmaller(resultMovingWindow)
    println("Part 1: $resultPart1")
    println("Part 2: $resultPart2")
}

fun readFile(name: String): List<String> = File(name).bufferedReader().readLines()

fun amountOfTimesALargerNumberAppearsAfterASmaller(input: List<String>): Int {
    var result: Int = 0;
    for ((index, value) in input.withIndex()) {
        if (index == 0) {
            continue
        }
        val current = value.toInt()
        val previous = input[index - 1].toInt()
        if (current > previous) {
            result++;
        }
    }

    return result
}

@JvmName("amountOfTimesALargerNumberAppearsAfterASmaller1")
fun amountOfTimesALargerNumberAppearsAfterASmaller(input: List<Int>): Int {
    var result: Int = 0;
    for ((index, value) in input.withIndex()) {
        if (index == 0) {
            continue
        }
        val previous = input[index - 1]
        if (value > previous) {
            result++;
        }
    }

    return result
}

fun amountOfTimesInAMovingWindow(input: List<String>): List<Int> {
    val result = mutableListOf<Int>()

    for ((index, value) in input.withIndex()) {
        if (index == input.size) {
            return result
        }
        if (index + 1 > input.size || index + 2 >= input.size) {
            return result
        }
        val windowSum = value.toInt() + input[index + 1].toInt() + input[index + 2].toInt()
        result.add(windowSum)
    }

    return result
}