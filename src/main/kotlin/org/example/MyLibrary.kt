package org.example

data class Language(val name: String, val hotness: Int)

class MyLibrary {
    fun kotlinLanguage() = Language("Kotlin", 10)
}