package dev.stalla;

import dev.stalla.model.Podcast;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import static dev.stalla.TestUtilKt.declaresException;
import static dev.stalla.model.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({TemporaryFileParameterResolver.class})
public class PodcastRssWriterInteropTest {

    @Test
    @DisplayName("should fail to write a null Podcast to a File")
    void failOnNullPodcastToFile(@TemporaryFile File file) {
        final Podcast podcast = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should fail to write a null Podcast to an OutputStream")
    void failOnNullPodcastToOutputStream(@TemporaryFile File file) throws IOException {
        final Podcast podcast = null;
        final OutputStream outputStream = new FileOutputStream(file);
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should fail to write a Podcast to a null File")
    void failOnPodcastToNullFile() {
        final Podcast podcast = aPodcast();
        final File file = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should throw an IOException when writing a Podcast to a read only file")
    void failOnPodcastToReadOnlyFile(@TemporaryFile File file) {
        final Podcast podcast = aPodcast();
        assertThrows(IOException.class, () -> PodcastRssWriter.write(podcast, toUnwritableFile(file)));
    }

    @Test
    @DisplayName("should fail to write a Podcast to a null OutputStream")
    void failOnPodcastToNullOutputStream() {
        final Podcast podcast = aPodcast();
        final OutputStream outputStream = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should throw a TransformerException when there is an error transforming a Podcast to XML")
    void failOnPodcastToClosedOutputStream(@TemporaryFile File file) {
        final Podcast podcast = aPodcast();
        assertThrows(TransformerException.class, () -> PodcastRssWriter.write(podcast, toUnwritableOutputStream(file)));
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

    private File toUnwritableFile(File file) {
        // File gives I/O error on writing when it is read-only
        file.setReadOnly();
        return file;
    }

    private OutputStream toUnwritableOutputStream(File file) throws IOException {
        // OutputStream gives I/O error when it is closed
        final OutputStream outputStream = new FileOutputStream(file);
        outputStream.close();
        return outputStream;
    }

}
