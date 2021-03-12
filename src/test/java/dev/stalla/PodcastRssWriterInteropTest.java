package dev.stalla;

import dev.stalla.model.Podcast;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import static dev.stalla.TestUtilKt.declaresException;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PodcastRssWriterInteropTest {

    @Test
    @DisplayName("should fail to write a null Podcast to a File")
    public void failOnNullPodcastToFile() throws IOException {
        final Podcast podcast = null;
        final File file = aFile();
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should fail to write a null Podcast to an OutputStream")
    public void failOnNullPodcastToOutputStream() throws IOException {
        final Podcast podcast = null;
        final OutputStream outputStream = new FileOutputStream(aFile());
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should fail to write a Podcast to a null File")
    public void failOnPodcastToNullFile() {
        final Podcast podcast = aPodcast();
        final File file = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should fail to write a Podcast to an unwritable File")
    public void failOnPodcastToReadOnlyFile() throws IOException {
        final Podcast podcast = aPodcast();
        final File file = aReadOnlyFile();
        assertThrows(IOException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should fail to write a Podcast to a null OutputStream")
    public void failOnPodcastToNullOutputStream() {
        final Podcast podcast = aPodcast();
        final OutputStream outputStream = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should fail to write a Podcast to unwriteable OutputStream")
    public void failOnPodcastToUnwritableOutputStream() throws IOException {
        final Podcast podcast = aPodcast();
        final OutputStream outputStream = new FileOutputStream(aFile());
        outputStream.close();
        assertThrows(IOException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should declare expected exceptions for the write(Podcast,File) method")
    void shouldDeclareExpectedExceptionsInWritePodcastFile() throws NoSuchMethodException {
        final Method method = PodcastRssWriter.class.getMethod("write", Podcast.class, File.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, TransformerException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the write(Podcast,OutputStream) method")
    void shouldDeclareExpectedExceptionsInWritePodcastOutputStream() throws NoSuchMethodException {
        final Method method = PodcastRssWriter.class.getMethod("write", Podcast.class, OutputStream.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, TransformerException.class))
        );
    }

    private File aFile() throws IOException {
        final File temp = File.createTempFile("test", ".rss");
        temp.deleteOnExit();
        return temp;
    }

    private File aReadOnlyFile() throws IOException {
        final File file = aFile();
        file.setReadOnly();
        return file;
    }

}
