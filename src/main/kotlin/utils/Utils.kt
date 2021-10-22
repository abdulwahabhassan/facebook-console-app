package utils

import java.util.*

object Utils {

    fun validateAge(age: String?) : Int?  {
        return try {
            age?.toInt()
        } catch (e: NumberFormatException) {
            println("Try again..")
            null
        }
    }

    fun validateAdminEntry(entry: String?) : Boolean? {
        return when {
            entry?.trim()?.lowercase(Locale.getDefault()) == "yes" -> {
                true
            }
            entry?.trim()?.lowercase(Locale.getDefault()) == "no" -> {
                false
            }
            else -> {
                null
            }
        }
    }

    fun validateEmptyField(entry: String?) : Boolean {
        return if (entry == null || entry.trim().isEmpty())  {
            println("Field cannot be empty or null..")
            false }
        else true
    }

    fun validateStartEntry(entry: String?): Int? {
        return if(entry == "1") {
            1
        }  else if (entry == "2") {
            2
        } else {
            println("Invalid entry..")
            null
        }
    }
}