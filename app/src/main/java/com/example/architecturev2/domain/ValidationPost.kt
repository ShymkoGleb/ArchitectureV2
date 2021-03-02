package com.example.architecturev2.domain

import javax.inject.Inject


class ValidationPost @Inject constructor(
    private val userId: Int,
    private val title: String,
    private val body: String,
) {
    companion object {
        private val forbiddenWords = listOf("Реклама", "Товар", "Куплю")
    }

    fun invoke(): Boolean {
        if (userId < 10) {
            println("userId<10")
            return false
        } else if (title.length < 3 || title.length > 50) {
            println("ValidationPost->postTitle.length < 3 || postTitle.length > 50")
            return false
        } else if (body.length < 5 || body.length > 500) {
            println("ValidationPost ->postBody.length < 5 || postBody.length > 500")
            return false
        }

        forbiddenWords.forEach { forbiddenWords ->
            if (forbiddenWords.toLowerCase() in title.toLowerCase()) {
                println("ValidationPost ->bannedTitleStrings")
                return false
            }
        }
        if (camelCaseValidation(title) || camelCaseValidation(body)) {
            println("camelCaseValidation(title) || camelCaseValidation(body)")
            return false
        }

        return true
    }

    fun camelCaseValidation(s: String): Boolean {
        val titleArray = s?.split(" ")
        var camelBoolean = false


        for (i in 0..titleArray.size - 1) {
            val charArray = titleArray[i].toCharArray()
            for (y in 1..charArray.size - 1) {
                if (y!=1 && charArray[y].isUpperCase() && charArray[y + 1].isLowerCase()) {
                    camelBoolean = true
                }
            }
        }
        return camelBoolean
    }
}


