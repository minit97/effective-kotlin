package com.effective.kotlin.chapter01.item03

class User(val name: String)

class UserRepo(
    val user: User = User("John Doe")
)

/** 1. 최대한 플랫폼 타입을 사용하지 말라
 */
fun example01() {
    // 위의 전제들은 Java로 간주해야 아래 내용들을 이해할 수 있다.

    val repo = UserRepo()
    val user1 = repo.user           // user1의 타입은 User!
    val user2: User = repo.user     // user2의 타입은 User
    val user3: User? = repo.user    // user3의 타입은 User?
}