package io.hemin.wien.sax

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class SaxParser {

    val factory: SAXParserFactory
    val saxParser: SAXParser
    val namespaces: HashMap<String, String> = HashMap()

    init {
        factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        saxParser = factory.newSAXParser()
    }

    val handler: DefaultHandler = object : DefaultHandler() {

        val sb: StringBuilder = StringBuilder()

        override fun startDocument() {

        }

        override fun endDocument() {

        }

        override fun startPrefixMapping(prefix: String, uri: String) {
            namespaces.put(uri, prefix)
        }

        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            println("startElement :: uri=$uri , localName=$localName , qName=$qName")
            sb.clear()
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            println("endElement :: uri=$uri , localName=$localName , qName=$qName , sb={${sb.trim()}}")
        }

        override fun characters(ch: CharArray, start: Int, length: Int) {
            //println("characters :: ${String(ch,start,length)}")
            sb.append(String(ch,start,length))
        }
    }

    fun parse(uri: String): Unit {
        saxParser.parse(uri, handler)
    }

}
