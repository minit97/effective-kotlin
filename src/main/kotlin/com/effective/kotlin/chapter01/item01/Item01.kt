package com.effective.kotlin.chapter01.item01

import kotlin.concurrent.thread

data class FullName(
    var name: String? = "Marton",
    val surname: String = "Braun"
) : Comparable<FullName> {
    val fullName: String?
        get() = name?.let { "$it $surname" }

    val fullName2: String? = name?.let { "$it $name" }

    override fun compareTo(other: FullName): Int {
        // 성을 먼저 비교하고, 같으면 이름을 비교
        val lastNameComparison = this.surname.compareTo(other.surname)
        return if (lastNameComparison != 0) {
            lastNameComparison
        } else {
            this.name!!.compareTo(other.name!!)
        }
    }

    override fun toString(): String {
        return "$name $surname"
    }
}

/** 1. 읽기 전용 프로퍼티(val)
 * - 스마트 캐스트 : if문으로 null인지 검사하면, 조건문 본문 내부에서는 null 아니라는 것이 확인된 것
 * - fullName은 게터로 정의 했으므로 스마트 캐스트를 할 수 없다.
 * - 게터를 활용하므로, 값이 사용하는 시점의 name에 따라서 다른 결과가 나올 수 있기 때문이다.
 */
fun example01() {
    val clazz = FullName()
    if (clazz.fullName != null) {
//        println(fullName.length)    // complie error
    }

    if (clazz.fullName2 != null) {
        println(clazz.fullName2.length)
    }
}



/**  2. 읽기 전용에서 mutable로 변경해야 한다면, 복제(copy)를 통해서 새로운 mutable 컬렉션을 만드는 list.toMutableList를 활용
 */
fun example02() {
    val list = listOf(1, 2, 3)

    val mutableList = list.toMutableList()
    mutableList.add(4)
}


/** 3. 세트와 맵은 내부적으로 해시테이블을 사용한다.
 * - 해시 테이블은 처음 요소를 넣을 때 요소의 값 기반으로 버킷을 결정한다.
 * - 요소에 수정이 일어나면 해시 테이블 내부에서 요소를 찾을 수 없게 되어 버린다.
 */
fun example03() {
//    val names: SortedSet<FullName> = TreeSet()    // TreeSet은 객체 참조로 비교 가능!
    val names: MutableSet<FullName> = mutableSetOf()
    val person = FullName("AAA", "AAA")
    names.add(person)
    names.add(FullName("Jordan", "Hansen"))
    names.add(FullName("David", "Blanc"))

    println(names)              // [AAA AAA, Dvid Blanc, Jordan Hansen]
    println(person in names)    // true

    person.name = "ZZZ"
    println(names)              // [ZZZ AAA, Dvid Blanc, Jordan Hansen]
    println(person in names)    // false
}


/** 4. 다른 종류의 변경 가능 지점
 */
fun example04() {
    // 1. 구체적인 리스트 구현 내부에 변경 기능 지점이 존재
    val list1: MutableList<Int> = mutableListOf()
    // 2. 프로퍼티 자체가 변경 가능 지점
    var list2: List<Int> = listOf()

    // 2번째 방식이 멀티스레드 처리 안정성이 더 좋지만, 잘못만들면 일부 요소 손실
    var list = listOf<Int>()
    for (i in 1..1000) {
        thread {
            list = list + i
        }
    }

    Thread.sleep(1000)
    print(list.size)


    // 이렇게 하지마세요. : 프로퍼티와 컬렉션을 모두 변경 가능한 지점으로 만드는 것!
    var list3 = mutableListOf<Int>()
}