package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.cyr2lat

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String? = " "): String{
        var result = ""
        for (char in payload){
            var isUpper = char.isUpperCase()
            var cyr2lat = String.cyr2lat(char.toLowerCase())
            result += if (isUpper) cyr2lat.capitalize() else cyr2lat
        }
        result = result.replace(" ", divider!!)
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName == null && lastName == null) return null
        var result: String? = null
        if ((firstName != null && firstName.replace(" ", "").isEmpty()) &&
            (lastName != null && lastName.replace(" ", "").isEmpty())) return null

        if (firstName != null && firstName.replace(" ", "").isNotEmpty()) {
                result = "${firstName.toUpperCase().get(0)}"
            }
        if (lastName != null && lastName.replace(" ", "").isNotEmpty()){
            if (result != null) {
                result += "${lastName.toUpperCase().get(0)}"
            }else{
                result = "${lastName.toUpperCase().get(0)}"
            }
        }
        return result
    }
}