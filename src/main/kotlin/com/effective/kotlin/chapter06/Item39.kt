package com.effective.kotlin.chapter06


/** 1. sealed class
 */
sealed class ValueMatcher<T> {
    abstract fun match(value: T): Boolean

    class Equal<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value == this.value
    }

    class NotEqual<T>(val value: T) : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value != this.value
    }

    class EmptyList<T> : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value is List<*> && value.isEmpty()
    }

    class NotEmptyList<T> : ValueMatcher<T>() {
        override fun match(value: T): Boolean = value is List<*> && value.isNotEmpty()
    }
}

/** 2. abstract 한정자와 비교해서 sealed 한정자의 장점
 */
fun <T> ValueMatcher<T>.reversed(): ValueMatcher<T> =
    when(this) {
        is ValueMatcher.EmptyList -> ValueMatcher.NotEmptyList()
        is ValueMatcher.NotEmptyList -> ValueMatcher.EmptyList()
        is ValueMatcher.Equal -> ValueMatcher.NotEqual(value)
        is ValueMatcher.NotEqual -> ValueMatcher.Equal(value)
    }