package com.example.myapplication.auth.api

/**
 * Objects for UserApi Responses.
 */
object Model {
    /**
     * Response object from /user/signOn endpoint.
     *
     * @param _id - userId
     * @param properties - properties of user
     * @param optional - optional return values
     */
    data class UserResponse(val _id: String, val properties: Properties, val optional: Optional)

    /**
     * Properties object from backend responses.
     *
     * @param score - user ELO score
     * @param email - user email
     */
    data class Properties(val score: Int, val email: String)

    /**
     * Optional object from backend responses.
     *
     * @param userDescription - description of user
     * @param dob - date of birth
     * @param phoneNumber - phone number of user
     */
    data class Optional(val userDescription: String, val dob: String, val phoneNumber: String)
}
