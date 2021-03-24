package dev.stalla;

import dev.stalla.model.Podcast;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;

import static dev.stalla.TestUtilKt.declaresException;
import static dev.stalla.model.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ConstantConditions")
@ExtendWith({TemporaryFileParameterResolver.class})
public class PodcastRssWriterInteropTest {

    @Test
    @DisplayName("should throw a NullPointerException when trying to write a null Podcast to a File")
    void failOnNullPodcastToFile(@TemporaryFile File file) {
        final Podcast podcast = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, file));
    }

    @Test
    @DisplayName("should throw a NullPointerException when trying to write a null Podcast to an OutputStream")
    void failOnNullPodcastToOutputStream(@TemporaryFile File file) throws IOException {
        final Podcast podcast = null;
        final OutputStream outputStream = new FileOutputStream(file);
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, outputStream));
    }

    @Test
    @DisplayName("should throw a NullPointerException when trying to write a Podcast to a null File")
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
    @DisplayName("should throw a NullPointerException when trying to write a Podcast to a null Writer")
    void failOnPodcastToNullWriter() {
        final Podcast podcast = aPodcast();
        final Writer writer = null;
        assertThrows(NullPointerException.class, () -> PodcastRssWriter.write(podcast, writer));
    }

    @Test
    @DisplayName("should throw an TransformerException when writing a Podcast to a writer that throws")
    void failWhenWriterThrows() {
        final Podcast podcast = aPodcast();
        assertThrows(TransformerException.class, () -> PodcastRssWriter.write(podcast, createThrowingWriter()));
    }

    @Test
    @DisplayName("should throw a NullPointerException when trying to write a Podcast to a null OutputStream")
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

    @NotNull
    private File toUnwritableFile(@NotNull File file) {
        // File gives I/O error on writing when it is read-only
        file.setReadOnly();
        return file;
    }

    @NotNull
    private OutputStream toUnwritableOutputStream(@NotNull File file) throws IOException {
        // OutputStream gives I/O error when it is closed
        final OutputStream outputStream = new FileOutputStream(file);
        outputStream.close();
        return outputStream;
    }

    @NotNull
    private Writer createThrowingWriter() {
        return new Writer() {

            @Override
            public void write(@NotNull char[] cbuf, int off, int len) throws IOException {
                throw new IOException("This is meant to happen");
            }

            @Override
            public void flush() throws IOException {
                throw new IOException("This is meant to happen");
            }

            @Override
            public void close() throws IOException {
                throw new IOException("This is meant to happen");
            }
        };
    }

}
