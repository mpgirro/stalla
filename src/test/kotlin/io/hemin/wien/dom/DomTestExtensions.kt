package io.hemin.wien.dom

import org.w3c.dom.Node
import java.io.StringWriter
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

private val transformerFactory = TransformerFactory.newInstance()

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
