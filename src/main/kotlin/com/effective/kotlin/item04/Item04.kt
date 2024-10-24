package com.effective.kotlin.item04

/** 1. inferred 타입으로 리턴하지마라
 */
open class Animal
class Zebra: Animal()

fun example1() {
    var animal1 = Zebra()
//    animal1 = Animal()   // 오류: Type mismatch

    var animal2: Animal = Zebra()
    animal2 = Animal()
}

/** 2. 타입 추론의 잘못된 예시
 */
class Car
fun Fiat126P(): Car {
    TODO("Not yet implemented")
}

interface CarFctory {
    fun produce() = DEFAULT_CAR1
}

val DEFAULT_CAR1: Car = Fiat126P()
val DEFAULT_CAR2 = Fiat126P()       // 해당 경우에는 Fiat126 이외의 자동차는 생산 못한다.


