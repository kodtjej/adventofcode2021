package com.kodtjej.adventofcode2021.day3

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day3/input")
    val gammaRatePt1 = calculateRate(input, ::gammaPredicate)
    val epsilonRatePt1 = calculateRate(input, ::epsilonPredicate)
    val powerConsumption = gammaRatePt1 * epsilonRatePt1

    println("----- Part 1 -----")
    println("gammaRate: $gammaRatePt1")
    println("epsilonRate: $epsilonRatePt1")
    println("power consumption: $powerConsumption")

    val oxygenGeneratorRating:Int= getOxygenGeneratorRating(input)
    val CO2ScrubberRating:Int= getCO2ScrubberRating(input)
    val lifeSupportRating:Int = oxygenGeneratorRating * CO2ScrubberRating

    println("----- Part 2 -----")
    println("Oxygen generator rating: $oxygenGeneratorRating")
    println("CO2 Scubber rating: $CO2ScrubberRating")
    println("Life support rating: $lifeSupportRating")

}

fun getCO2ScrubberRating(input: List<String>):Int {
    val result = getValueBasedOnLeastCommon(input, 0)
    return  Integer.parseInt(result[0], 2)
}

fun getOxygenGeneratorRating(input: List<String>):Int{
    val result = getValueBasedOnMostCommon(input, 0)
    return  Integer.parseInt(result[0], 2)
}

fun getValueBasedOnLeastCommon(list: List<String>, index:Int): List<String>{
    if(list.size == 1)
        return list

    val flippedInput = makeColumnsIntoRows(list)

    val a = getLeastCommon(flippedInput, index)
    val newList = list.filter { value -> a == value[index].digitToInt() }
    return getValueBasedOnLeastCommon(newList, index+1)

}

fun getValueBasedOnMostCommon(list: List<String>, index:Int): List<String>{
    if(list.size == 1)
        return list

    val flippedInput = makeColumnsIntoRows(list)

    val a = getMostCommon(flippedInput, index)
    val newList = list.filter { value -> a == value[index].digitToInt() }
    return getValueBasedOnMostCommon(newList, index+1)

}

fun getMostCommon(input: List<String>, index:Int):Int{
    val count = input[index].groupingBy { it }.eachCount()
    if (count['0']!! > count['1']!!) {
        return 0
    }
    return 1
}

fun getLeastCommon(input: List<String>, index:Int):Int{
    val count = input[index].groupingBy { it }.eachCount()
    if (count['0']!! > count['1']!!) {
        return 1
    }
    return 0
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