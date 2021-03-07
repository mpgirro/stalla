package dev.stalla

import assertk.fail
import dev.stalla.dom.DomBuilderFactory
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.w3c.dom.Document
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAccessor
import java.util.stream.Stream
import javax.xml.parsers.DocumentBuilder

private val domBuilder: DocumentBuilder = DomBuilderFactory.newDocumentBuilder()

/** Creates a DOM document from a resource loaded from given [filePath]. */
internal fun documentFromResource(filePath: String): Document {
    val resourceUrl = DomBuilderFactory::class.java.getResource(filePath)
        ?: fail("The resource '$filePath' does not exist")

    return resourceUrl.openStream()
        .use { domBuilder.parse(it) }
}

/** Creates a [java.util.Date] as specified. Defaults to Z timezone, and midnight. */
internal fun dateTime(
    year: Int,
    month: Month,
    day: Int,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
    nanosecond: Int = 0,
    overrideZoneId: ZoneId? = null
): TemporalAccessor {
    val localDate = LocalDate.of(year, month, day)
    val localTime = LocalTime.of(hour, minute, second, nanosecond)

    val zoneId = overrideZoneId ?: ZoneId.of("Z")
    return ZonedDateTime.of(localDate, localTime, zoneId)
}

/** Gets a list of all the resource files in the given resources path */
internal fun allResourceFilesIn(path: String): List<File> {
    val files = DomBuilderFactory::class.java.getResourceAsStream(path)!!
        .bufferedReader()
        .readLines()
        .map {
            val testFile = File(path, it)
            val fileUrl = DomBuilderFactory::class.java.getResource(testFile.path)
            File(fileUrl.toURI())
        }


    val invalidFiles = files.filter { !it.isFile }

    require(invalidFiles.isEmpty()) {
        val invalidFilePaths = invalidFiles.joinToString("\n") { it.path }
        "At least one resource in '$path' is not a file or doesn't exist:\n$invalidFilePaths"
    }

    return files
}

/** Returns an instance of [ArgumentsProvider] from all arguments to this function.  */
inline fun <reified T : Any> arguments(vararg args: T): ArgumentsProvider = ArgumentsProvider {
    Stream.of(*args).map { Arguments.of(it) }
}
