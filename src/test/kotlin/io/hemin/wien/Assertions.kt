package io.hemin.wien

import assertk.Assert
import assertk.assertions.support.expected
import assertk.fail
import io.hemin.wien.util.FeedNamespace
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
