package com.example.architecturev2.repository

import javax.inject.Inject

class UserRepository @Inject constructor() {
    companion object {
        val ListOfIdForUserWithBann = listOf<Int>(7)
        val ListOfIdForUserWithWarning = listOf<Int>(3, 4)
    }
}