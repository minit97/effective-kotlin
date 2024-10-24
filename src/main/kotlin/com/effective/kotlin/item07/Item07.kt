package com.effective.kotlin.item07
import com.google.gson.Gson

/** 1. 결과 부족이 발생할 경우 null과 Failure를 사용하라
 */
sealed class Result<out T>
class Success<out T>(val result: T) : Result<T>()
class Failure(val throwable:Throwable) : Result<Nothing>()

class JsonParsingException: Exception()

inline fun <reified T> String.readObjectOrNUll(): T? {
    val incorrectSign = false
    val temp = "result"
    val result = Gson().fromJson(temp, T::class.java)

    //..

    if(incorrectSign) {
        return null
    }

    return result
}


inline fun <reified T> String.readObject(): Result<T> {
    val incorrectSign = false
    val temp = "result"
    val result = Gson().fromJson(temp, T::class.java)

    //..

    if(incorrectSign) {
        return Failure(JsonParsingException())
    }

    return Success(result)
}

/** 1-1. 이렇게 표시되는 오류는 다루기 쉬우며 놓치기 어렵다.
 * - null을 처리해야 한다면, 사용자는 안전 호출(safe call) 또는 Elvis 연산자와 같은 다양한 널 안정성(null-safey)
 */
val userText = "userText"
class Person(
    val age: Int
)

val age1 = userText.readObjectOrNUll<Person>()?.age ?: -1

/** 1-2. Result 같은 공용체(union type)를 리턴하기로 했다면, when 표현식을 사용해서 이를 처리할 수 있다.
 */
val person = userText.readObject<Person>()
val age2 = when(person) {
    is Success -> person.result.age
    is Failure -> -1
    else -> null
}