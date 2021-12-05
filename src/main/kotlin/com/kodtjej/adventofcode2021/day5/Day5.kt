package com.kodtjej.adventofcode2021.day5

import com.kodtjej.adventofcode2021.day1.readFile

fun main(){
    //val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day5/input")
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day5/test_input")
    val amountOfLinesOverlapping = getOverLappingAmount(input)

}

fun getOverLappingAmount(input:List<String>):Int {

    for (i in input){
        val r = Regex("(\\d,\\d)")
        val matches = r.findAll(i)

      matches.forEach { println(it.value) }
//        var l = Line(fromX = fromMatches[0].toInt(), fromY = fromMatches[1].toInt(), toX = toMatches[0].toInt(), toY = toMatches[1].toInt())
//        println(l)

    }


    return 0
}

class Line {
    var fromX:Int = 0
    var toX:Int = 0
    var fromY:Int = 0
    var toY:Int = 0

    constructor(fromX:Int, fromY:Int, toX:Int, toY:Int){
        this.fromX = fromX
        this.toX = toX
        this.fromY = fromY
        this.toY = toY
    }
}