package dev.stalla

import assertk.Assert
import assertk.assertions.isNotEmpty
import assertk.assertions.support.expected
import dev.stalla.builder.Builder
import dev.stalla.dom.asListOfNodes
import dev.stalla.dom.asString
import dev.stalla.model.MediaType
import dev.stalla.util.FeedNamespace
import dev.stalla.util.FeedNamespace.Companion.matches
import org.w3c.dom.Attr
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlunit.diff.Diff
import java.io.File
import javax.xml.transform.Source
import javax.xml.transform.dom.DOMSource

/** Asserts the diff is empty (the two DOMs are identical). */
internal fun Assert<Diff>.hasNoDifferences() = given { diff ->
    if (diff.hasDifferences()) expected(
        message = buildString {
            appendLine("to have no differences.")
            appendLine()
            appendLine("**** Actual (control) *****")
            appendLine(diff.controlSource.getNode().asString())
            appendLine()
            appendLine("**** Expected (test) *****")
            appendLine(diff.testSource.getNode().asString())
        },
        expected = emptyList<Diff>(),
        actual = diff.differences.toList()
    )
}

private fun Source.getNode() = when (this) {
    is DOMSource -> this.node
    else -> throw UnsupportedOperationException("Unable to get a DOM Node from a Source of type ${this::class.qualifiedName}")
}

/** Asserts the DOM node has no attribute with matching [localName] and [namespace]. */
internal fun Assert<Node>.hasNoAttribute(localName: String, namespace: FeedNamespace? = null) = given { node ->
    if (!node.hasAttributes()) return@given
    val matchingAttribute = node.attributes.getNamedItemNS(namespace?.uri, localName)
    if (matchingAttribute != null) {
        expected(
            message = "not to have an attribute with localName='$localName' and namespace='$namespace'",
            expected = null,
            actual = "Attr[name='${matchingAttribute.nodeName}', namespaceURI='${matchingAttribute.namespaceURI}', " +
                "value='${matchingAttribute.nodeValue}']"
        )
    }
}

/** Asserts the file is not empty. */
internal fun Assert<File>.isNotEmpty() = given { file ->
    if (file.length() > 0L) return@given
    expected(
        message = "not to be empty",
        expected = "file.length() == 0 byte(s)",
        actual = "file.length() == ${file.length()} byte(s)"
    )
}

/** Asserts none of the [Builder]s [`hasEnoughDataToBuild`][Builder.hasEnoughDataToBuild] is `true`. */
internal fun Assert<List<Builder<*>>>.noneHasEnoughDataToBuild() = given { builders ->
    assertThat(builders, this.name).isNotEmpty()
    if (builders.any { it.hasEnoughDataToBuild }) {
        expected(
            message = "none of the builders to have hasEnoughDataToBuild == true",
            expected = emptyList<Builder<*>>(),
            actual = builders.filter { it.hasEnoughDataToBuild }
        )
    }
}

/** Asserts the [Builder]s [`hasEnoughDataToBuild`][Builder.hasEnoughDataToBuild] is `false`. */
internal fun Assert<Builder<*>>.hasNotEnoughDataToBuild() = given { builder ->
    if (builder.hasEnoughDataToBuild) {
        expected(
            message = "to have hasEnoughDataToBuild == false",
            actual = builder
        )
    }
}

/** Asserts the [Element] does not exist (is null). */
internal fun Assert<Element?>.doesNotExist() = given { element ->
    if (element == null) return@given
    expected(
        "to not exist",
        expected = "null",
        actual = element.asString()
    )
}

/** Asserts the [Element] has the expected text content. */
internal fun Assert<Element>.hasTextContent(expected: String) = given { element ->
    if (element.textContent == expected) return@given
    expected(
        "to have the text content '$expected'",
        expected = expected,
        actual = element.textContent
    )
}

/** Asserts the [Node] has no child nodes. */
internal fun Assert<Node>.hasNoChildren() = given { element ->
    if (element.childNodes.length == 0) return@given
    expected(
        "to have no children",
        expected = emptyList<Node>(),
        actual = element.childNodes.asListOfNodes().map { it.asString() }
    )
}

/** Asserts the [Node] has only one child. */
internal fun Assert<Node>.hasOneChild() = given { element ->
    if (element.childNodes.length == 1) return@given
    expected(
        "to have exactly one child",
        expected = listOf(element.firstChild.asString()),
        actual = element.childNodes.asListOfNodes().map { it.asString() }
    )
}

/** Asserts the [Attr] has the expected value. */
internal fun Assert<Attr>.hasValue(expected: String) = given { element ->
    if (element.value == expected) return@given
    expected(
        "to have the value '$expected'",
        expected = expected,
        actual = element.value
    )
}

/**
 * Transforms the current assertion to the list of [`childNodes`][Node.getChildNodes] that have
 * the given [localName] and [namespace].
 */
internal fun Assert<Node>.childNodesNamed(localName: String, namespace: FeedNamespace? = null) =
    transform { node ->
        node.childNodes.asListOfNodes()
            .filter { it.localName == localName && namespace.matches(it.namespaceURI) }
    }

/** Asserts that [MediaType.toString] matches the expected value. */
internal fun Assert<MediaType>.equalToString(expected: String) = given { mediaType ->
    if (mediaType.toString() == expected) return@given
    expected(
        "to have the string form '$expected' but was '$mediaType'",
        expected = expected,
        actual = mediaType.toString()
    )
}

/** Asserts that [MediaType.toString] matches the expected value. */
internal fun Assert<MediaType>.equalToString(expected: MediaType) = given { mediaType ->
    if (mediaType.match(expected)) return@given
    expected(
        "to have the string form '$expected' but was '$mediaType'",
        expected = expected,
        actual = mediaType.toString()
    )
}

/** Asserts that [MediaType.match] matches the expected value. */
internal fun Assert<MediaType>.matchPattern(expected: String) = given { mediaType ->
    if (mediaType.match(expected)) return@given
    expected(
        "to have the string form '$expected' but was '$mediaType'",
        expected = expected,
        actual = mediaType.toString()
    )
}

/** Asserts that [MediaType.match] matches the expected value. */
internal fun Assert<MediaType>.matchPattern(expected: MediaType) = given { mediaType ->
    if (mediaType.match(expected)) return@given
    expected(
        "to have the string form '$expected' but was '$mediaType'",
        expected = expected,
        actual = mediaType.toString()
    )
}

internal fun Assert<MediaType>.matchesSymmetrically(expected: MediaType?) = given { mediaType ->
    if (mediaType.match(expected) && expected.match(mediaType)) return@given
    if (!mediaType.match(expected)) {
        expected(
            "to match symmetrically, but '$mediaType' does not match '$expected'",
            expected = true,
            actual = false
        )
    }
    if (!expected.match(mediaType)) {
        expected(
            "to match symmetrically, but '$expected' does not match '$mediaType'",
            expected = true,
            actual = false
        )
    }
}

internal fun Assert<MediaType>.notMatchesSymmetrically(expected: MediaType?) = given { mediaType ->
    if (expected != null && !mediaType.match(expected) && !expected.match(mediaType)) return@given
    if (mediaType.match(expected)) {
        expected(
            "to not match symmetrically, but '$mediaType' does match '$expected'",
            expected = false,
            actual = true
        )
    }
    if (expected != null && !expected.match(mediaType)) {
        expected(
            "to not match symmetrically, but '$expected' does match '$mediaType'",
            expected = false,
            actual = true
        )
    }
}
