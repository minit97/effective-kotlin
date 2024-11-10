package com.effective.kotlin.chapter02.item12


/** 1. 연산자 오버로딩
 */
fun Int.factorial(): Int = (1..this).product()

fun Iterable<Int>.product(): Int = fold(1) { acc, e -> acc * e }

fun example01_01() {
    print(10 * 6.factorial())   //7200
}

operator fun Int.not() = factorial()

fun example01_02() {
    print(10 * !6)   //7200
}