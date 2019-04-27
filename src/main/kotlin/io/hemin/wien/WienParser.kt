package io.hemin.wien

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class WienParser {

    val factory: SAXParserFactory = SAXParserFactory.newInstance()
    val saxParser: SAXParser = factory.newSAXParser()

    val handler: DefaultHandler = object : DefaultHandler() {
        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            println("startElement :: uri=$uri , localName=$localName , qName=$qName")
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            println("endElement :: uri=$uri , localName=$localName , qName=$qName")
        }

        override fun characters(ch: CharArray, start: Int, length: Int) {
            println("characters :: ch=$ch , start=$start , length=$length")
        }
    }

    fun parse(uri: String): Unit {
        saxParser.parse(uri, handler)
    }

}
