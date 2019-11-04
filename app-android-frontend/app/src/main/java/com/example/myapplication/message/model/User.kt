package com.example.myapplication.message.model

/**
 * Model for User Object.
 * Contains a user's name and their type (sender or receiver).
 *
 * @param name - user name
 * @param userType - type of user
 */
class User(val name: String, val userType: UserType)
