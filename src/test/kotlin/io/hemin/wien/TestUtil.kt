package io.hemin.wien

import assertk.fail
import io.hemin.wien.dom.DomBuilderFactory
import org.w3c.dom.Document
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAccessor
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
