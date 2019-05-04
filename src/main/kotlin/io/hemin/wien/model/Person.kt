package io.hemin.wien.model

/**
 * Model class for elements describing persons.
 *
 * @property name The name of the person.
 * @property email The email of the person.
 * @property uri The uri of the person.
 */
data class Person(
    val name: String?,
    val email: String?,
    val uri: String?
)
