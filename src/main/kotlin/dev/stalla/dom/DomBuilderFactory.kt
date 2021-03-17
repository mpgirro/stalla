package dev.stalla.dom

import dev.stalla.util.InternalAPI
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

/**
 * Factory for the API to obtain DOM Document instances from
 * XML documents. This factory applies required configuration
 * to the API instance, for consistent usage in this library.
 */
@InternalAPI
internal object DomBuilderFactory {

    private val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

    init {
        // Apply the required configuration
        factory.isNamespaceAware = true
        factory.preventXxe()
    }

    @Suppress("SwallowedException")
    private fun DocumentBuilderFactory.preventXxe() {
        try {
            // See http://bit.ly/owasp-xxe-java-dom (shortened URL on https://cheatsheetseries.owasp.org/)
            // Disallowing DTDs prevents most XXE entity attacks. Xerces 2 only.
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)

            // Disallow external entities. Xerces 1, 2 and JDK 7+.
            setFeature("http://xml.org/sax/features/external-general-entities", false)
            setFeature("http://xml.org/sax/features/external-parameter-entities", false)

            // Disable external DTDs, XIncludes, and entity reference expansion.
            setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
            isXIncludeAware = false
            isExpandEntityReferences = false
        } catch (e: ParserConfigurationException) {
            // We tried setting a feature that isn't supported by the current parser
        }
    }

    fun newDocumentBuilder(): DocumentBuilder = factory.newDocumentBuilder()
}
