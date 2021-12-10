package com.kodtjej.adventofcode2021.day9

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input =
        readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day9/input").map { it.split("").filter { !it.equals("") }.map { it.toInt() } }

    val result = calculateLowestPoints(input)

    println("---- Part 1 ----")
    println(result)
}

fun calculateLowestPoints(heightMap: List<List<Int>>): Int {
    val lowestPoints = mutableListOf<Int>()
    for (row in heightMap.indices) {
        for (column in heightMap[row].indices) {
            val neighbors = getNeighbors(column, row, heightMap)
            val currentValue = heightMap[row][column]
            if (neighbors.isBiggerThan(currentValue)) {
                lowestPoints.add(currentValue)
            }
        }
    }

    return lowestPoints.map { it.inc() }.sum()
}

fun getNeighbors(x: Int, y: Int, input: List<List<Int>>): List<Int> {
    val neighbors = mutableListOf<Int>()
    val rowSize = input.size
    val colSize = input[0].size
    //get East neighbor
    neighbors.add(input.getNeighbor(x.inc(), y, rowSize, colSize))
    //get West neighbor
    neighbors.add(input.getNeighbor(x.dec(), y, rowSize, colSize))
    //get North neighbor
    neighbors.add(input.getNeighbor(x, y.dec(), rowSize, colSize))
    //get South neighbor
    neighbors.add(input.getNeighbor(x, y.inc(), rowSize, colSize))

    return neighbors
}

fun List<List<Int>>.getNeighbor(x: Int, y: Int, rowSize: Int, colSize: Int): Int {
    if (y < 0 || y >= rowSize || x < 0 || x >= colSize) {
        return 10
    }
    return this[y][x]
}

fun List<Int>.isBiggerThan(currentValue: Int): Boolean {
    for (i in this) {
        if (currentValue >= i) {
            return false
        }
    }
    return true
}