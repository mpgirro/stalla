package dev.stalla;

import dev.stalla.model.Episode;
import dev.stalla.model.Podcast;
import dev.stalla.model.atom.AtomPerson;
import dev.stalla.model.atom.Link;
import dev.stalla.model.googleplay.GoogleplayCategory;
import dev.stalla.model.itunes.ItunesCategory;
import dev.stalla.model.podcastindex.Funding;
import dev.stalla.model.podcastindex.PodcastindexPerson;
import dev.stalla.model.podcastindex.Soundbite;
import dev.stalla.model.podcastindex.Transcript;
import dev.stalla.model.podlove.SimpleChapter;
import dev.stalla.model.rss.RssCategory;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.List;

import static dev.stalla.TestUtilKt.declaresException;
import static dev.stalla.TestUtilKt.declaresNoExceptions;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexPerson;
import static dev.stalla.model.EpisodeFixturesKt.aPodloveSimpleChapter;
import static dev.stalla.model.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexSoundbite;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodcastindexTranscript;
import static dev.stalla.model.FixturesKt.aGoogleplayCategory;
import static dev.stalla.model.FixturesKt.aLink;
import static dev.stalla.model.FixturesKt.anAtomPerson;
import static dev.stalla.model.FixturesKt.anItunesCategory;
import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static dev.stalla.model.PodcastFixturesKt.aPodcastPodcastindexPerson;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ConstantConditions")
@ExtendWith({TemporaryFileParameterResolver.class})
public class PodcastRssParserInteropTest {

    private final Podcast parsedPodcast;
    private final Episode parsedEpisode;
    private final File invalidRssFile;

    public PodcastRssParserInteropTest() throws IOException, SAXException {
        // This file cannot be parsed
        invalidRssFile = new File(getClass().getClassLoader().getResource("xml/no-rss.xml").getFile());

        // Podcast/Episode instances that a result of parsing (instead of fixtures)
        final File fullRssFile = new File(getClass().getClassLoader().getResource("xml/rss-full.xml").getFile());
        parsedPodcast = requireNonNull(PodcastRssParser.parse(fullRssFile));
        parsedEpisode = requireNonNull(parsedPodcast.getEpisodes().get(0));
    }

    @Test
    @DisplayName("should fail to parse an URI that is null")
    void failOnNullUri() {
        final String uri = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(uri));
    }

    @Test
    @DisplayName("should fail to parse an URI to a file that does not exist")
    void failOnUriNotExists() {
        assertThrows(IOException.class, () -> PodcastRssParser.parse("some/fantasy/uri"));
    }

    @Test
    @DisplayName("should fail to parse an URI to an invalid XML")
    void failOnUriInvalidXml() {
        final String uri = invalidRssFile.getAbsolutePath();
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(uri));
    }

    @Test
    @DisplayName("should fail to parse an InputStream that is null")
    void failOnNullInputStream() {
        final InputStream inputStream = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(inputStream));
    }

    @Test
    @DisplayName("should fail to parse an unreadable InputStream")
    void failOnInputStreamNotExists(@TemporaryFile File file) {
        assertThrows(IOException.class, () -> PodcastRssParser.parse(toUnreadableInputStream(file)));
    }

    @Test
    @DisplayName("should fail to parse an InputStream from an invalid XML")
    void failOnInputStreamInvalidXml() throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(invalidRssFile);
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(inputStream));
    }

    @Test
    @DisplayName("should fail to parse a File that is null")
    void failOnNullFile() {
        final File file = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(file));
    }

    @Test
    @DisplayName("should fail to parse a Reader that throws")
    void failWhenReaderThrows() {
        final Reader throwingReader = createThrowingReader();
        assertThrows(IOException.class, () -> PodcastRssParser.parse(throwingReader));
    }

    @Test
    @DisplayName("should fail to parse a File from an invalid XML")
    void failOnFileInvalidXml() {
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(invalidRssFile));
    }

    @Test
    @DisplayName("should fail to parse an InputSource that is null")
    public void failOnNullInputSource() {
        final InputSource inputSource = null;
        assertThrows(NullPointerException.class, () -> PodcastRssParser.parse(inputSource));
    }

    @Test
    @DisplayName("should throw an IOException when parsing an unreadable InputSource")
    void failOnInputSourceNotExists(@TemporaryFile File file) {
        assertThrows(IOException.class, () -> PodcastRssParser.parse(toUnreadableInputSource(file)));
    }

    @Test
    @DisplayName("should fail to parse an InputSource of an invalid XML")
    void failOnInputSourceInvalidXml() throws FileNotFoundException {
        final InputSource inputSource = toInputSource(invalidRssFile);
        assertThrows(SAXException.class, () -> PodcastRssParser.parse(inputSource));
    }

    @Test
    @DisplayName("should fail to parse a Document that is null")
    void failOnNullDocument() {
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

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast episodes")
    void shouldParsePodcastUnmodifiableEpisodes() {
        final List<Episode> episodes = requireNonNull(parsedPodcast.getEpisodes());
        assertThrows(UnsupportedOperationException.class, () -> episodes.add(anEpisode()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast RSS categories")
    void shouldParsePodcastUnmodifiableRssCategories() {
        final List<RssCategory> categories = requireNonNull(parsedPodcast.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(anRssCategory()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode RSS categories")
    void shouldParseEpisodeUnmodifiableCategories() {
        final List<RssCategory> categories = requireNonNull(parsedEpisode.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(anRssCategory()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Atom authors")
    void shouldParsePodcastAtomUnmodifiableAuthors() {
        final List<AtomPerson> authors = requireNonNull(parsedPodcast.getAtom().getAuthors());
        assertThrows(UnsupportedOperationException.class, () -> authors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Atom contributors")
    void shouldParsePodcastAtomUnmodifiableContributors() {
        final List<AtomPerson> contributors = requireNonNull(parsedPodcast.getAtom().getContributors());
        assertThrows(UnsupportedOperationException.class, () -> contributors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Atom links")
    void shouldParsePodcastAtomUnmodifiableLinks() {
        final List<Link> links = requireNonNull(parsedPodcast.getAtom().getLinks());
        assertThrows(UnsupportedOperationException.class, () -> links.add(aLink()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast iTunes categories")
    void shouldParsePodcastItunesUnmodifiableCategories() {
        final List<ItunesCategory> categories = requireNonNull(parsedPodcast.getItunes().getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(anItunesCategory()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Google Play categories")
    void shouldParsePodcastGoogleplayUnmodifiableCategories() {
        final List<GoogleplayCategory> categories = requireNonNull(parsedPodcast.getGoogleplay().getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(aGoogleplayCategory()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Podcastindex funding")
    void shouldParsePodcastPodcastindexUnmodifiableFunding() {
        final List<Funding> fundingList = requireNonNull(parsedPodcast.getPodcastindex().getFunding());
        assertThrows(UnsupportedOperationException.class, () -> fundingList.add(aPodcastPodcastindexFunding()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Podcast Podcastindex persons")
    void shouldParsePodcastPodcastindexUnmodifiablePerson() {
        final List<PodcastindexPerson> personList = requireNonNull(parsedPodcast.getPodcastindex().getPersons());
        assertThrows(UnsupportedOperationException.class, () -> personList.add(aPodcastPodcastindexPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Podcastindex soundbites")
    void shouldParseEpisodePodcastindexUnmodifiableSoundbites() {
        final List<Soundbite> soundbites = requireNonNull(parsedEpisode.getPodcastindex().getSoundbites());
        assertThrows(UnsupportedOperationException.class, () -> soundbites.add(anEpisodePodcastindexSoundbite()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Podcastindex transcripts")
    void shouldParseEpisodePodcastindexUnmodifiableTranscripts() {
        final List<Transcript> transcripts = requireNonNull(parsedEpisode.getPodcastindex().getTranscripts());
        assertThrows(UnsupportedOperationException.class, () -> transcripts.add(anEpisodePodcastindexTranscript()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Podcastindex persons")
    void shouldParseEpisodePodcastindexUnmodifiablePerson() {
        final List<PodcastindexPerson> personList = requireNonNull(parsedEpisode.getPodcastindex().getPersons());
        assertThrows(UnsupportedOperationException.class, () -> personList.add(anEpisodePodcastindexPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Atom authors")
    void shouldParseEpisodetAtomUnmodifiableAuthors() {
        final List<AtomPerson> authors = requireNonNull(parsedEpisode.getAtom().getAuthors());
        assertThrows(UnsupportedOperationException.class, () -> authors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Atom contributors")
    void shouldParseEpisodeAtomUnmodifiableContributors() {
        final List<AtomPerson> contributors = requireNonNull(parsedEpisode.getAtom().getContributors());
        assertThrows(UnsupportedOperationException.class, () -> contributors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Atom links")
    void shouldParseEpisodeAtomUnmodifiableLinks() {
        final List<Link> links = requireNonNull(parsedEpisode.getAtom().getLinks());
        assertThrows(UnsupportedOperationException.class, () -> links.add(aLink()));
    }

    @Test
    @DisplayName("should parse to an unmodifiable list of Episode Podlove SimpleChapters")
    void shouldParseEpisodePodloveUnmodifiableSimpleChapters() {
        final List<SimpleChapter> simpleChapters = requireNonNull(parsedEpisode.getPodlove()).getSimpleChapters();
        assertThrows(UnsupportedOperationException.class, () -> simpleChapters.add(aPodloveSimpleChapter()));
    }

    @NotNull
    private InputSource toInputSource(@NotNull File file) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(file);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new InputSource(inputStreamReader);
    }

    @NotNull
    private InputStream toUnreadableInputStream(@NotNull File file) throws IOException {
        // InputStream gives I/O error when it is closed
        final InputStream inputStream = new FileInputStream(file);
        inputStream.close();
        return inputStream;
    }

    @NotNull
    private InputSource toUnreadableInputSource(@NotNull File file) throws IOException {
        // InputSource gives I/O error when it's stream is closed
        final InputSource inputSource = toInputSource(file);
        inputSource.getCharacterStream().close();
        return inputSource;
    }

    @NotNull
    private Reader createThrowingReader() {
        return new Reader() {

            @Override
            public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
                throw new IOException("This is meant to happen");
            }

            @Override
            public void close() throws IOException {
                throw new IOException("This is meant to happen");
            }
        };
    }

}
