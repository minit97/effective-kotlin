package com.effective.kotlin.chapter06

/** 1. equals가 필요한 이유
 *  - 유일한 객체를 의미
 */
class Name(val name: String)

fun example01() {
    val name1 = Name("John")
    val name2 = Name("John")
    val name1Ref = name1

    println(name1 == name1)     //true
    println(name1 == name2)     //false
    println(name1 == name1Ref)  //true

    println(name1 === name1)     //true
    println(name1 === name2)     //false
    println(name1 === name1Ref)  //true
}

/** 2. data class
 *  - 두 객체의 기본 생성자의 프로퍼티가 같다면, 같은 객체로 본다.
 */
data class FullName(val name: String, val surname: String)

fun example02() {
    val name1 = FullName("John", "Park")
    val name2 = FullName("John", "Park")
    val name3 = FullName("Kim", "Park")

    println(name1 == name1)     //true
    println(name1 == name2)     //true, 데이터가 같기에
    println(name1 == name3)     //false

    println(name1 === name1)     //true
    println(name1 === name2)     //false
    println(name1 === name3)  //false
}