package io.hemin.wien.model

/**
 * Model class for data from elements of the Atom namespace.
 *
 * @property authors List of data from the `<atom:author>` elements as [Person] instances.
 * @property contributors List of data from the `<atom:contributor>` elements as [Person] instances.
 * @property links List of data from the `<atom:link>` elements as [Link] instances.
 */
data class Atom(
    val authors: List<Person>,
    val contributors: List<Person>,
    val links: List<Link>
)
