package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.DAY -> value * DAY
        TimeUnits.HOUR -> value * HOUR
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date? = Date()): String{
    val diff: Long = abs(this.getTime() - date!!.getTime())
    if (diff / 1000 <= 1) return "только что"
    if (diff / 1000 <= 45) return "несколько секунд назад"
    if (diff / 1000 <= 75) return "минуту назад"
    if (diff / 1000 / 60 <= 45) return "${diff / 1000 / 60}  минут назад"
    if (diff / 1000 / 60 <= 75) return "час назад"
    if (diff / 1000 / 60 / 60 <= 22) return "${diff / 1000 / 60 / 60}  часов назад"
    if (diff / 1000 / 60 / 60 <= 26) return "день назад"
    if (diff / 1000 / 60 / 60 / 24 <= 360) return "${diff / 1000 / 60 / 60 / 24}  дней назад"
    return "более года назад"
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            var valueToString = value.toString()
            if (valueToString.endsWith("5") ||
                valueToString.endsWith("6") ||
                valueToString.endsWith("7") ||
                valueToString.endsWith("8") ||
                valueToString.endsWith("9") ||
                valueToString.endsWith("0") ||
                valueToString.endsWith("11") ||
                valueToString.endsWith("12") ||
                valueToString.endsWith("13") ||
                valueToString.endsWith("14")) return "$value секунд"
            if (valueToString.endsWith("1")) return "$value секунду"
            if (valueToString.endsWith("2") ||
                valueToString.endsWith("3") ||
                valueToString.endsWith("4")) return "$value секунды"
            return "$value секунд"
        }
    }, MINUTE {
        override fun plural(value: Int): String {
            var valueToString = value.toString()
            if (valueToString.endsWith("5") ||
                valueToString.endsWith("6") ||
                valueToString.endsWith("7") ||
                valueToString.endsWith("8") ||
                valueToString.endsWith("9") ||
                valueToString.endsWith("0") ||
                valueToString.endsWith("11") ||
                valueToString.endsWith("12") ||
                valueToString.endsWith("13") ||
                valueToString.endsWith("14")) return "$value минут"
            if (valueToString.endsWith("1")) return "$value минуту"
            if (valueToString.endsWith("2") ||
                valueToString.endsWith("3") ||
                valueToString.endsWith("4")) return "$value минуты"
            return "$value минут"
        }
    }, HOUR {
        override fun plural(value: Int): String {
            var valueToString = value.toString()
            if (valueToString.endsWith("5") ||
                valueToString.endsWith("6") ||
                valueToString.endsWith("7") ||
                valueToString.endsWith("8") ||
                valueToString.endsWith("9") ||
                valueToString.endsWith("0") ||
                valueToString.endsWith("11") ||
                valueToString.endsWith("12") ||
                valueToString.endsWith("13") ||
                valueToString.endsWith("14")) return "$value часов"
            if (valueToString.endsWith("1")) return "$value час"
            if (valueToString.endsWith("2") ||
                valueToString.endsWith("3") ||
                valueToString.endsWith("4")) return "$value часа"
            return "$value часов"
        }
    }, DAY {
        override fun plural(value: Int): String {
            var valueToString = value.toString()
            if (valueToString.endsWith("5") ||
                valueToString.endsWith("6") ||
                valueToString.endsWith("7") ||
                valueToString.endsWith("8") ||
                valueToString.endsWith("9") ||
                valueToString.endsWith("0") ||
                valueToString.endsWith("11") ||
                valueToString.endsWith("12") ||
                valueToString.endsWith("13") ||
                valueToString.endsWith("14")) return "$value дней"
            if (valueToString.endsWith("1")) return "$value день"
            if (valueToString.endsWith("2") ||
                valueToString.endsWith("3") ||
                valueToString.endsWith("4")) return "$value дня"
            return "$value часов"
        }
    };

    abstract fun plural(value: Int): String
}