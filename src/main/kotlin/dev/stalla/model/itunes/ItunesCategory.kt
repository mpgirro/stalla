package dev.stalla.model.itunes

/**
 * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12].
 */
public sealed interface ItunesCategory {

    /** The name of the category */
    public val value: String

    public companion object Factory {

        // Note that the map call here does a required smart cast
        private val categoryMap: Map<String, ItunesCategory> = run {
            val categories = listOf(
                SimpleItunesCategory.values().map { it },
                NestedItunesCategory.values().map { it }
            )
            categories.flatten().map { it.value.toLowerCase() to it }.toMap()
        }

        public fun from(category: String?): ItunesCategory? = category?.let {
            return categoryMap.get(it.toLowerCase())
        }
    }
}
