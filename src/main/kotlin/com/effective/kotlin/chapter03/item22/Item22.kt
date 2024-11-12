package com.effective.kotlin.chapter03.item22

import com.effective.kotlin.chapter01.item04.Animal


/** example01
 */
fun <T: Comparable<T>> Iterable<T>.sorted(): List<T> {
    return TODO("Provide the return value")
}

fun <T, C : MutableCollection<in T>> Iterable<T>.toCollection(destination: C): C {
    return TODO("Provide the return value")
}

//class ListAdapter<T: ItemAdapter>(/* ... */) { /* ... */ }

/** example02
 * - 많이 사용하는 제한으로는 Any가 있다.
 */
inline fun <T, R : Any> Iterable<T>.mapNotNull(
    transfrom: (T) -> R?
): List<R> {
    return mapNotNullTo(ArrayList<R>(), transfrom)
}

/** example03
 * - 둘 이상 제한
 */
//fun <T: Animal> pet(animal: T) where T: GoodTempered {
//    return TODO("Provide the return value")
//}
//
//fun <T> pet(animal: T) where T: Animal, T: GoodTempered {
//    return TODO("Provide the return value")
//}