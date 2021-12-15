package com.kodtjej.adventofcode2021.day8

import com.kodtjej.adventofcode2021.day1.readFile

var segments: Map<Int, String> = mapOf()

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day8/input")

    val outputValues = getOutputValues(input).map { it.split(" ").filter { it != "" } }
    val appearances = outputValues.flatMap { it.map { decideOnNumber(it) } }.filter { it != -1 }
    println("---- Part 1 ----")
    println("Times 1, 4, 7, 8 appears: ${appearances.size}")


    val knownSegments = populateKnownSegments(outputValues)
    segments = populateUnknownSegments(outputValues, knownSegments.toMutableMap())
    val numbers = getNumbersFromSegments(outputValues)
    println("---- Part 2 ----")
//    println(numbers.map { it.toInt() }.sum())
}


fun getNumbersFromSegments(input: List<List<String>>): List<Int> {
    val numbers = mutableListOf<Int>()
    input.forEach { outputValue ->
        numbers.add(outputValue.map { it.getNumber().toString() }.reduce { acc, b -> acc.plus(b) }.toInt())
    }

    return emptyList()
}

fun String.getNumber(): Int {
    for ((k, v) in segments) {
        val res = this.removeSegments(v)
        if (res.length == 0) {
            return k

        }
    } // NEJ, TVÄRTOM? NEJ? BOTH?
    return -1
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

fun populateUnknownSegments(input: List<List<String>>, segments: MutableMap<Int, String>): Map<Int, String> {
    input.forEach { outputValues ->
        outputValues.forEach {
            if (it.length == 6) {
                if (it.contains(Regex("[${segments[1]}]"))) {
                    segments[0] = it
                } else if (it.contains(Regex("[${segments[4]}]"))) {
                    segments[9] = it
                } else {
                    segments[6] = it
                }
            }
            if (it.length == 5) {
                val isTwo = it.removeSegments(segments[4]!!).length == 3
                if (it.contains(Regex("[${segments[1]}]"))) {
                    segments[3] = it
                } else if (isTwo) {
                    segments[2] = it
                } else {
                    segments[5] = it
                }
            }
        }
    }

    return segments
}

fun String.removeSegments(segmentsToRemove: String): String {
    var newString = this
    segmentsToRemove.forEach { segment ->
        newString = newString.filter { it != segment }
    }
    return newString
}

fun populateKnownSegments(input: List<List<String>>): Map<Int, String> {
    val knownSegments = mutableMapOf<Int, String>()
    input.forEach { outputValues ->
        outputValues.forEach {
            val num = decideOnNumber(it)
            if (num == 1) {
                knownSegments[1] = it
            }
            if (num == 4) {
                knownSegments[4] = it
            }
            if (num == 7) {
                knownSegments[7] = it
            }
            if (num == 8) {
                knownSegments[8] = it
            }
        }
    }
    return knownSegments
}