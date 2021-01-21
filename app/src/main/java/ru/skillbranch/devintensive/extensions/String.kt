package ru.skillbranch.devintensive.extensions

fun String.Companion.cyr2lat(input: Char): String = when (input) {
    'а' -> "a"
    'б' -> "b"
    'в' -> "v"
    'г' -> "g"
    'д' -> "d"
    'е' -> "e"
    'ё' -> "e"
    'ж' -> "zh"
    'з' -> "z"
    'и' -> "i"
    'й' -> "i"
    'к' -> "k"
    'л' -> "l"
    'м' -> "m"
    'н' -> "n"
    'о' -> "o"
    'п' -> "p"
    'р' -> "r"
    'с' -> "s"
    'т' -> "t"
    'у' -> "u"
    'ф' -> "f"
    'х' -> "h"
    'ц' -> "c"
    'ч' -> "ch"
    'ш' -> "sh"
    'щ' -> "sh"
    'ъ' -> ""
    'ы' -> "i"
    'ь' -> ""
    'э' -> "e"
    'ю' -> "yu"
    'я' -> "ya"
    else -> input.toString()
}

fun String.truncate(count: Int? = 16): String {
    var result = this.trimEnd()
    if (result.length <= count!!) return result
    result = result.subSequence(0, count).toString()
    result = "${result.trimEnd()}..."

    return result
}

fun String.stripHtml(): String = this.replace(Regex("<.*?>"), "").replace(Regex(" * "), " ")