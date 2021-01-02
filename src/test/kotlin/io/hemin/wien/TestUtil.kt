package io.hemin.wien

import assertk.fail
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.asListOfNodes
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.io.StringWriter
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAccessor
import javax.xml.parsers.DocumentBuilder
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

private val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

private val transformerFactory = TransformerFactory.newInstance()

/** Finds a DOM node matching [elementName] in a resource loaded from given [filePath]. */
internal fun nodeFromResource(elementName: String, filePath: String): Node {
    val doc = documentFromResource(filePath)

    var result: Node? = null
    for (node in doc.childNodes.asListOfNodes()) {
        if (elementName == node.localName) {
            result = node
        }
    }
    return result ?: fail("Unable to find the element '$elementName' in resource '$filePath'")
}

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

internal fun Node.asString(prettyPrint: Boolean = true): String {
    try {
        val writer = StringWriter()
        val source = DOMSource(this)
        val transformer = createTransformer(prettyPrint)
        transformer.setOutputProperty("omit-xml-declaration", "yes")
        transformer.transform(source, StreamResult(writer))
        return writer.toString()
    } catch (e: TransformerException) {
        throw RuntimeException("Unable to convert $this to a string.", e)
    }
}

private fun createTransformer(prettyPrint: Boolean): Transformer {
    val transformer = transformerFactory.newTransformer()
    if (prettyPrint) transformer.apply {
        setOutputProperty(OutputKeys.INDENT, "yes")
        setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
    }
    return transformer
}
