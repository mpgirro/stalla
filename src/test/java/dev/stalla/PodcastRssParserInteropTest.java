package dev.stalla;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.*;
import java.lang.reflect.Method;

import static dev.stalla.TestUtilKt.declaresException;
import static dev.stalla.TestUtilKt.declaresNoExceptions;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastRssParserInteropTest {

    private static File unusableFile;
    private static InputStream unusableInputStream;
    private static InputSource unusableInputSource;
    private final File invalidRssFile = new File(getClass().getClassLoader().getResource("xml/no-rss.xml").getFile());

    @BeforeAll
    public static void init() throws IOException {
        unusableFile = File.createTempFile("file-test",".rss");
        unusableFile.deleteOnExit();

        // InputSource gives I/O error when it's stream is closed
        unusableInputSource = toInputSource(unusableFile);
        unusableInputSource.getCharacterStream().close();

        // Inputstream gives I/O error when it is closed
        unusableInputStream = new FileInputStream(unusableFile);
        unusableInputStream.close();

        // File gives I/O error when it is not readable
        unusableFile.setReadable(false);
    }

    @Test
    @DisplayName("should fail to parse an URI that is null")
    public void failOnNullUri() {
        final String uri = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(uri));
    }

    @Test
    @DisplayName("should fail to parse an URI to a file that does not exist")
    public void failOnUriNotExists() {
        assertThrows(IOException.class, () -> PodcastRssParser.parse("some/fantasy/uri"));
    }

    @Test
    @DisplayName("should fail to parse an URI to an invalid XML")
    public void failOnUriInvalidXml() {
        final String uri = invalidRssFile.getAbsolutePath();
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(uri));
    }

    @Test
    @DisplayName("should fail to parse an InputStream that is null")
    public void failOnNullInputStream() {
        final InputStream inputStream = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(inputStream));
    }

    @Test
    @DisplayName("should fail to parse an InputStream from a file that does not exist")
    public void failOnInputStreamNotExists() throws FileNotFoundException {
        assertThrows(IOException.class, () -> PodcastRssParser.parse(unusableInputStream));
    }

    @Test
    @DisplayName("should fail to parse an InputStream from an invalid XML")
    public void failOnInputStreamInvalidXml() throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(invalidRssFile);
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(inputStream));
    }

    @Test
    @DisplayName("should fail to parse a File that is null")
    public void failOnNullFile() {
        final File file = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(file));
    }

    @Test
    @DisplayName("should fail to parse a File that does not exist")
    public void failOnFileNotExists() {
        assertThrows(IOException.class, () -> PodcastRssParser.parse(unusableFile));
    }

    @Test
    @DisplayName("should fail to parse a File from an invalid XML")
    public void failOnFileInvalidXml() {
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(invalidRssFile));
    }

    @Test
    @DisplayName("should fail to parse an InputSource that is null")
    public void failOnNullInputSource() {
        final InputSource inputSource = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(inputSource));
    }

    @Test
    @DisplayName("should fail to parse an InputSource of a file that does not exist")
    public void failOnInputSourceNotExists() throws FileNotFoundException {
        assertThrows(IOException.class, () -> PodcastRssParser.parse(unusableInputSource));
    }

    @Test
    @DisplayName("should fail to parse an InputSource of an invalid XML")
    public void failOnInputSourceInvalidXml() throws FileNotFoundException {
        final InputSource inputSource = toInputSource(invalidRssFile);
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(inputSource));
    }

    @Test
    @DisplayName("should fail to parse a Document that is null")
    public void failOnNullDocument() {
        final Document document = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(document));
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(String) method")
    void shouldDeclareExpectedExceptionsInParseUri() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", String.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputStream) method")
    void shouldDeclareExpectedExceptionsInParseInputStream() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputStream.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputStream, String) method")
    void shouldDeclareExpectedExceptionsInParseInputStreamString() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputStream.class, String.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(File) method")
    void shouldDeclareExpectedExceptionsInParseFile() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", File.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputSource) method")
    void shouldDeclareExpectedExceptionsInParseInputSource() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputSource.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(Document) method")
    void shouldDeclareExpectedExceptionsInParseDocument() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", Document.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresNoExceptions(method))
        );
    }

    private static InputSource toInputSource(File file) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(file);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new InputSource(inputStreamReader);
    }

}
