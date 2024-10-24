package com.effective.kotlin.item05

import java.rmi.server.LogStream.log


/** 1. 예외를 활용해 코드에 제한을 걸어라
 * - 제한을 걸면 문서를 읽지 않은 개발자도 문제를 확인할 수 있다.
 * - 문제가 있을 경우에 함수가 예상하지 못한 동작을 하지 않고 예외를 throw 한다.
 *      - 예상하지 못한 동작을 하는 것은 예외를 throw하는 것보다 굉장히 위험하며, 상태를 관리하는 것이 굉장히 힘들다.
 *      - 이러한 제한으로 인해서 문제를 놓치지 않을 수 있고, 코드가 더 안정적으로 작동하게 된다.
 * - 코드가 어느 정도 자체적으로 검사된다. 따라서 이와 관련된 단위 테스트를 줄일 수 있다.
 * - 스마트 캐스트 기능을 활용할 수 있게 되므로, 캐스트(타입 변환)를 적게 할 수 있다.
 */
val size = 0
val isOpen = true
var collection = listOf(1, 2, 3)

fun pop(num: Int = 1): List<Int> {
    require(num <= size) {
        "Cannot remove more elements than current size"
    }
    check(isOpen) { "Cannot pop from closed stack" }
    val ret = collection.take(num)
    collection = collection.drop(num)
    assert(ret.size == num)
    return ret
}

/** 2. 아규먼트 체크 - require
 */
fun factorial1(n: Int): Long {
    require(n >= 0)
    return if (n <= 1) 1 else factorial1(n - 1) * n
}


class Point
class Cluster
fun findCluster(points: List<Point>) {
    require(points.isNotEmpty())
    // ...
}


class User(
    val email: String,
)
fun isValidEmail(email: String): Boolean {
    TODO("Not yet implemented")
}
fun sendEmail(user: User, message: String) {
    requireNotNull(user.email)
    require(isValidEmail(user.email))
}

/** 3. require 람다를 활용한 지연 메시지 정의
 */
fun factorial2(n: Int): Long {
    require(n >= 0) { "Cannot calculate factorial of $n because it is smaller than 0"}
    return if (n <= 1) 1 else factorial2(n - 1) * n
}

/** 4. 상태와 관련딘 제한 - check
 */
val isInitailized = true
fun speak(text: String) {
    check(isInitailized)
}


val token = "token"
fun getUserInfo() {
    checkNotNull(token)
}

fun next() {
    check(isOpen)
}

/** 5. nullability와 스마트 캐스팅
 */
class Person(
    val email: String,
)
fun sendEmail(person: Person, text: String) {
    val email1: String = person.email ?: return

    val email2: String = person.email ?: run {
        log("Email not sent, no email addresss")
        return
    }
}
