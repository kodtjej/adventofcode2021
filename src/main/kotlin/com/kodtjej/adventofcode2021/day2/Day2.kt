package com.kodtjej.adventofcode2021.day2

import com.kodtjej.adventofcode2021.day1.readFile

fun main() {
    val input = readFile("src/main/kotlin/com/kodtjej/adventofcode2021/day2/input")

    val resultPart1:Int = getFinalPosition(input);
    val resultPart2:Int = getFinalPositionWithAim(input);

    println("Part 1: $resultPart1");
    println("Part 2: $resultPart2");

}

fun getFinalPositionWithAim(input: List<String>): Int {

    return 0;
}

fun getFinalPosition(input:List<String>):Int {
    var horizontalPosition:Int = 0;
    var depth:Int = 0;

    for (s in input){
        val splitString:List<String> = s.split(" ");
        val action:String = splitString[0];
        val amount:Int = splitString[1].toInt();

        if ("forward" == action){
            horizontalPosition += amount;
        }
        if("down" == action){
            depth += amount;
        }
        if("up" == action){
            depth -= amount;
        }
    }
    println("$horizontalPosition $depth")
    return horizontalPosition * depth;
}
