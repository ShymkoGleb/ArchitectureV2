package com.example.architecturev2.domain

import java.util.*

class CheckPostRulesUseCase(
    private val postTitle: String,
    private val postBody: String,
) {
    companion object {
        private val bannedTitleStrings = listOf("Реклама", "Товар", "Куплю")
    }

    fun invoke(): Boolean {
        if (postTitle.length < 3 || postTitle.length > 50) {
            println("CheckPostRulesUseCase->postTitle.length < 3 || postTitle.length > 50")
            return false
        }

        if (postBody.length < 5 || postBody.length > 500) {
            println("CheckPostRulesUseCase ->postBody.length < 5 || postBody.length > 500")
            return false
        }

        bannedTitleStrings.forEach { bannedString ->
            if (bannedString.toLowerCase(Locale.getDefault()) in postTitle.toLowerCase(Locale.getDefault())) {
                println("CheckPostRulesUseCase ->bannedTitleStrings")
                return false
            }
        }
        return true
    }
}