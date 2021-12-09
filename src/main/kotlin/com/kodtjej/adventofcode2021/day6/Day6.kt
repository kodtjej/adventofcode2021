package com.kodtjej.adventofcode2021.day6

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day6/input")[0].split(",").map { it.toLong() }

    val amount1 = fishPop(input, 80)
    val amount2 = fishPop(input, 256)
    println("---- Part 1 ----")
    println("Amount after 80 days: $amount1")
    println("---- Part 2 ----")
    println("Amount after 256 days: $amount2")

}

fun fishPop(input: List<Long>, days: Int): Long {
    val fishes = (0L..8L).associateWith { 0L }.toMutableMap()
    input.forEach {
        fishes[it] = fishes[it]!! + 1
    }

    for (day in 0 until days) {
        val fishesToReproduce = fishes[0]!!
        fishes[0] = fishes[1]!!
        fishes[1] = fishes[2]!!
        fishes[2] = fishes[3]!!
        fishes[3] = fishes[4]!!
        fishes[4] = fishes[5]!!
        fishes[5] = fishes[6]!!
        fishes[6] = fishes[7]!! + fishesToReproduce
        fishes[7] = fishes[8]!!
        fishes[8] = fishesToReproduce
    }

    return fishes.values.sum()

}