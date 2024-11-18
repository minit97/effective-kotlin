package com.effective.kotlin.chapter05

/** 1. 팩토리 함수
 */
fun example01() {
    // 기본 생성자
    val list1 = MyLinkedList(1, MyLinkedList(2, null))

    // 팩토리 함수
    val list2 = myLinkedListOf(1, 2)
}

 class MyLinkedList<T> (
     val head: T,
     val tail: MyLinkedList<T>?
 )

fun <T> myLinkedListOf(
    vararg elements: T
): MyLinkedList<T>? {
    if (elements.isEmpty()) return null
    val head = elements.first()
    val elementsTail = elements
        .copyOfRange(1, elements.size)
    val tail = myLinkedListOf(*elementsTail)
    return MyLinkedList(head, tail)
}


