package io.hemin.wien.util

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class DomBuilderFactory {

    companion object Factory {
        private val factory = DomBuilderFactory()
        fun newBuilder(): DocumentBuilder = factory.newBuilder()
    }

    private val factory: DocumentBuilderFactory

    init {
        factory = DocumentBuilderFactory.newInstance()
        factory.isNamespaceAware = true
    }

    fun newBuilder(): DocumentBuilder = factory.newDocumentBuilder()

}
