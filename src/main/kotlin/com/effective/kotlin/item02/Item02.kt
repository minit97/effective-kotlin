package com.effective.kotlin.item02

import java.awt.Color

class User(val name: String = "Default")

fun getValue(): User {
    return User("Existing User")
}

/** 1. 변수의 스코프를 최소화하라
 */
fun example1() {
    val users = listOf(User("Alice"), User("Bob"), User("Charlie"))


    /** 나쁜 예 - 변수 user는 for 반복문 스코프 내부뿐만 아니라 외부에서도 사용 가능
     */
    var user1: User
    for (i in users.indices) {
        user1 = users[i]
        print("User at $i is $user1")
    }

    // 조금 더 좋은 예
    for (i in users.indices) {
        val user2 = users[i]
        print("User at $i is $user2")
    }

    // 제일 좋은 예
    for ((i, user3) in users.withIndex()) {
        print("User at $i is $user3")
    }
}



fun example02() {
    val hasValue = true;

    // 나쁜 예
    val user1: User
    if (hasValue) {
        user1 = getValue()
    } else {
        user1 = User()
    }

    // 조금 더 좋은 예
    val user2: User = if(hasValue) {
        getValue()
    } else {
        User()
    }
}

/**
 * 구조분해 선언 (destructuring declaration)
 */
// 나쁜 예
fun badUpdateWeather(degrees: Int) {
    val description: String
    val color: Color
    if (degrees < 5) {
        description = "cold"
        color = Color.BLUE
    } else if (degrees < 23) {
        description = "mild"
        color = Color.YELLOW
    } else {
        description = "hot"
        color = Color.RED
    }
    // ...
}

// 조금 더 좋은 예
fun goodUpdateWeather(degrees: Int) {
    val (description, color) = when {
        degrees < 5 -> "cold" to Color.BLUE
        degrees < 23 -> "mild" to Color.YELLOW
        else -> "hot" to Color.RED
    }

    // ...
}
