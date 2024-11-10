package com.effective.kotlin.chapter01.item09

import java.io.BufferedReader
import java.io.File
import java.io.FileReader


/** 1. use
 */
fun countCharactersInFile1(path: String): Int {
//    val reader = BufferedReader(FileReader(path))
//    reader.use {
//        return reader.lineSequence().sumOf { it.length }
//    }

    BufferedReader(FileReader(path)).use { reader ->
        return reader.lineSequence().sumOf { it.length }
    }
}

/** 2. useLines
 */
fun countCharactersInFile2(path: String): Int {
    File(path).useLines { lines ->
        return lines.sumOf { it.length }
    }
}

fun countCharactersInFile2_refactor(path: String): Int =
    File(path).useLines { lines ->
        lines.sumOf { it.length }
    }
