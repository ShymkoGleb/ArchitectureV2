package com.example.architecturev2.domain

import java.util.*

class ValidationPost(
    private val userId:Int,
    private val title: String,
    private val body: String,
) {
    companion object {
        private val forbiddenWords = listOf("Реклама", "Товар", "Куплю")
    }


    fun invoke(): Boolean {
        if (userId<10){
            println("ValidationPost->postTitle.length < 3 || postTitle.length > 50")
            return false
        }

        if (title.length < 3 || title.length > 50) {
            println("ValidationPost->postTitle.length < 3 || postTitle.length > 50")
            return false
        }

        if (body.length < 5 || body.length > 500) {
            println("ValidationPost ->postBody.length < 5 || postBody.length > 500")
            return false
        }

        forbiddenWords.forEach { forbiddenWords ->
            if (forbiddenWords.toLowerCase() in title.toLowerCase()) {
                println("ValidationPost ->bannedTitleStrings")
                return false
            }
        }
        return true
    }
}