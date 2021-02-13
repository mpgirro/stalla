package dev.stalla.model.itunes

/**
 * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12].
 */
public sealed interface ItunesCategory {

    /** The name of the category */
    public val categoryName: String

    public companion object Factory : ItunesCategoryFactory {

        private val categoryMap: Map<String, ItunesCategory> = run {
            // Note that the map calls here do a necessary smart cast
            val categories = listOf(
                SimpleItunesCategory.values().map { it },
                NestedItunesCategory.values().map { it }
            )
            categories.flatten().map { it.categoryName.toLowerCase() to it }.toMap()
        }

        @JvmStatic
        public override fun from(category: String?): ItunesCategory? = category?.let {
            return categoryMap[it.toLowerCase()]
        }
    }
}
