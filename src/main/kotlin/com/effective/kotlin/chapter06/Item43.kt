package com.effective.kotlin.chapter06

/** 1. 멤버 함수와 확장 함수
 */
open class C
class D : C()
fun C.foo() = "c"
fun D.foo() = "d"

fun example1() {
    val d = D()
    println(d.foo())            // d
    val c: C = d
    println(c.foo())            // c

    println(D().foo())          // d
    println((D() as C).foo())   // c
}
