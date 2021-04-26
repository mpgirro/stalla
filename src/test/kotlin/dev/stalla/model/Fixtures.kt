package dev.stalla.model

import dev.stalla.arguments
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
import dev.stalla.staticPropertiesByType
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.reflections.Reflections
import java.math.BigInteger

@JvmOverloads
internal fun anRssImage(
    url: String = "image url",
    title: String = "image title",
    link: String = "image link",
    width: Int? = 123,
    height: Int? = 456,
    description: String? = "image description"
) = RssImage(url, title, link, width, height, description)

@JvmOverloads
internal fun anHrefOnlyImage(
    href: String = "image href"
) = HrefOnlyImage(href)

@JvmOverloads
internal fun anAtomPerson(
    name: String = "person name",
    email: String? = "person email",
    uri: String? = "person uri"
) = AtomPerson(name, email, uri)

@JvmOverloads
internal fun aLink(
    href: String = "link href",
    hrefLang: String? = "link hrefLang",
    hrefResolved: String? = "link hrefResolved",
    length: String? = "link length",
    rel: String? = "link rel",
    title: String? = "link title",
    type: MediaType? = MediaType.HTML
) = Link(href, hrefLang, hrefResolved, length, rel, title, type)

@JvmOverloads
internal fun anRssCategory(
    category: String = "rss category",
    domain: String? = "rss category domain"
) = RssCategory(category, domain)

@JvmOverloads
internal fun anItunesCategory(
    category: ItunesCategory = ItunesCategory.SCIENCE_FICTION
) = category

@JvmOverloads
internal fun aGoogleplayCategory(
    category: GoogleplayCategory = GoogleplayCategory.NEWS_AND_POLITICS
) = category

@JvmOverloads
internal fun aPodcastindexGeoLocation(
    coordA: Double = 48.20849,
    coordB: Double = 16.37208,
    coordC: Double? = 5.0,
    crs: String? = GeoLocation.CRS_WGS84,
    uncertainty: Double? = 10.0,
    parameters: List<GeoLocation.Parameter> = emptyList()
) = GeoLocation(coordA, coordB, coordC, crs, uncertainty, parameters)

@JvmOverloads
internal fun aPodcastindexOpenStreetMapFeature(
    type: OpenStreetMapElementType = OpenStreetMapElementType.Relation,
    id: BigInteger = BigInteger.ONE,
    revision: BigInteger? = BigInteger.TWO
) = OpenStreetMapFeature(type, id, revision)

@JvmOverloads
internal fun aPodcastindexPerson(
    name: String = "podcastindex person name",
    role: String? = "podcastindex person role",
    group: String? = "podcastindex person group",
    img: String? = "podcastindex person img",
    href: String? = "podcastindex person href"
) = PodcastindexPerson(name, role, group, img, href)

@JvmOverloads
internal fun aPodcastindexLocation(
    name: String = "podcastindex location name",
    geo: GeoLocation? = aPodcastindexGeoLocation(),
    osm: OpenStreetMapFeature? = aPodcastindexOpenStreetMapFeature()
) = PodcastindexLocation(name, geo, osm)

internal val simpleCategoryNames = listOf(
    "Arts",
    "Business",
    "Comedy",
    "Education",
    "Fiction",
    "Government",
    "History",
    "Health & Fitness",
    "Kids & Family",
    "Leisure",
    "Music",
    "News",
    "Religion & Spirituality",
    "Science",
    "Society & Culture",
    "Sports",
    "Technology",
    "True Crime",
    "TV & Film"
)

internal val nestedCategoryNames = listOf(
    "Books",
    "Design",
    "Fashion & Beauty",
    "Food",
    "Performing Arts",
    "Visual Arts",
    "Careers",
    "Entrepreneurship",
    "Investing",
    "Management",
    "Marketing",
    "Non-Profit",
    "Comedy Interviews",
    "Improv",
    "Stand-Up",
    "Courses",
    "How To",
    "Language Learning",
    "Self-Improvement",
    "Comedy Fiction",
    "Drama",
    "Science Fiction",
    "Alternative Health",
    "Fitness",
    "Medicine",
    "Mental Health",
    "Nutrition",
    "Sexuality",
    "Education for Kids",
    "Parenting",
    "Pets & Animals",
    "Stories for Kids",
    "Animation & Manga",
    "Automotive",
    "Aviation",
    "Crafts",
    "Games",
    "Hobbies",
    "Home & Garden",
    "Video Games",
    "Music Commentary",
    "Music History",
    "Music Interviews",
    "Business News",
    "Daily News",
    "Entertainment News",
    "News Commentary",
    "Politics",
    "Sports News",
    "Tech News",
    "Buddhism",
    "Christianity",
    "Hinduism",
    "Islam",
    "Judaism",
    "Religion",
    "Spirituality",
    "Astronomy",
    "Chemistry",
    "Earth Sciences",
    "Life Sciences",
    "Mathematics",
    "Natural Sciences",
    "Nature",
    "Physics",
    "Social Sciences",
    "Documentary",
    "Personal Journals",
    "Philosophy",
    "Places & Travel",
    "Relationships",
    "Baseball",
    "Basketball",
    "Cricket",
    "Fantasy Sports",
    "Football",
    "Golf",
    "Hockey",
    "Rugby",
    "Running",
    "Soccer",
    "Swimming",
    "Tennis",
    "Volleyball",
    "Wilderness",
    "Wrestling",
    "After Shows",
    "Film History",
    "Film Interviews",
    "Film Reviews",
    "TV Reviews"
)

@get:JvmName("getAllItunesCategoryNames")
internal val allItunesCategoryNames = simpleCategoryNames + nestedCategoryNames

@get:JvmName("getAllTranscriptTypeNames")
internal val allTranscriptTypeNames = listOf(
    "text/plain",
    "text/html",
    "application/json",
    "application/srt"
)

internal class ItunesCategoryNameProvider : ArgumentsProvider by arguments(*allItunesCategoryNames.toTypedArray())

internal class TranscriptTypeNameProvider : ArgumentsProvider by arguments(*allTranscriptTypeNames.toTypedArray())

internal val reflections = Reflections("dev.stalla")

/**
 * List of the Java class of all Kotlin classes in this library's package
 * that provide a companion object that implements [BuilderFactory].
 */
@get:JvmName("getAllBuilderFactorySubTypes")
internal val allBuilderFactorySubTypes: List<Class<*>> by lazy {
    reflections.getSubTypesOf(BuilderFactory::class.java).mapNotNull { clazz -> clazz.declaringClass }
}

/**
 * List of the Java class of all Kotlin classes in this library's package
 * that provide a companion object that implements [TypeFactory].
 */
@get:JvmName("getAllTypeFactorySubTypes")
internal val allTypeFactorySubTypes: List<Class<*>> by lazy {
    reflections.getSubTypesOf(TypeFactory::class.java).mapNotNull { clazz -> clazz.declaringClass }
}

/** All members of ItunesCategory class that are exposed as static fields in Java. */
@get:JvmName("getStaticItunesCategoryProperties")
internal val staticItunesCategoryProperties: Array<ItunesCategory> by lazy {
    staticPropertiesByType(ItunesCategory::class.java, ItunesCategory.TECHNOLOGY)
}

/** All members of TranscriptType class that are exposed as static fields in Java. */
@get:JvmName("getStaticTranscriptTypeProperties")
internal val staticTranscriptTypeProperties: Array<TranscriptType> by lazy {
    staticPropertiesByType(TranscriptType::class.java, TranscriptType.PLAIN_TEXT)
}
