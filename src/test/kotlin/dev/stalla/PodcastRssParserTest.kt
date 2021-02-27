package dev.stalla

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.dom.DomBuilderFactory
import dev.stalla.dom.findElementByName
import dev.stalla.model.Episode
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Podcast
import dev.stalla.model.StyledDuration
import dev.stalla.model.content.Content
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.time.Month
import java.util.Locale

internal class PodcastRssParserTest {

    private val documentBuilder = DomBuilderFactory.newDocumentBuilder()

    @Test
    internal fun `should return null when parsing an empty document`() {
        assertThat(PodcastRssParser.parse(createDocument())).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'rss' tag at top level`() {
        assertThat(PodcastRssParser.parse(createDocumentWithNode("banana"))).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with an empty 'rss' tag`() {
        assertThat(PodcastRssParser.parse(createDocumentWithNode("rss"))).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'channel' tag in a non-empty 'rss'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByName("rss") ?: fail("No rss element found")
            rssElement.appendChild(createElement("banana"))
        }
        assertThat(PodcastRssParser.parse(document)).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with an empty 'channel' tag in 'rss'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByName("rss") ?: fail("No rss element found")
            rssElement.appendChild(createElement("channel"))
        }
        assertThat(PodcastRssParser.parse(document)).isNull()
    }

    @Test
    internal fun `should pick up the first non empty 'channel' tag in 'rss'`() {
        val document = validRssDocument().apply {
            val rssElement = findElementByName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.findElementByName("channel") ?: fail("No channel element found")
            rssElement.insertBefore(createElement("channel"), channelElement)
        }
        assertThat(PodcastRssParser.parse(document)).isNotNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'item' tag in a non-empty 'channel'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.appendChild(createElement("channel"))
            channelElement.appendChild(createElement("banana"))
        }
        assertThat(PodcastRssParser.parse(document)).isNull()
    }

    @Test
    internal fun `should skip empty 'item' tags in 'channel' when parsing a valid document`() {
        val document = validRssDocument().apply {
            val rssElement = findElementByName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.findElementByName("channel") ?: fail("No channel element found")
            val firstItemElement = channelElement.findElementByName("item") ?: fail("No item element found")
            channelElement.insertBefore(createElement("item"), firstItemElement)
        }
        assertThat(PodcastRssParser.parse(document)).isNotNull()
            .prop(Podcast::episodes).hasSize(2) // Two in the xml — the empty one we inserted is skipped
    }

    @Test
    internal fun `should parse a basic feed correctly`() {
        val document = validRssDocument()
        assertThat(PodcastRssParser.parse(document)).isNotNull().all {
            prop(Podcast::title).isEqualTo("Lorem Ipsum title")
            prop(Podcast::description).isEqualTo("Lorem Ipsum description")
            prop(Podcast::link).isEqualTo("http://example.org")
            prop(Podcast::episodes).given { episodes ->
                assertThat(episodes).hasSize(2)

                assertThat(episodes[0]).all {
                    prop(Episode::title).isEqualTo("Lorem Ipsum")
                    prop(Episode::link).isEqualTo("http://example.org/episode1")
                    prop(Episode::description).isEqualTo("Lorem Ipsum episode 1 description")
                    prop(Episode::enclosure).all {
                        prop(Enclosure::url).isEqualTo("http://example.org/episode1.m4a")
                        prop(Enclosure::type).isEqualTo("audio/mp4")
                        prop(Enclosure::length).isEqualTo(78589133)
                    }
                }

                assertThat(episodes[1]).all {
                    prop(Episode::title).isEqualTo("Lorem Ipsum 2")
                    prop(Episode::link).isEqualTo("http://example.org/episode2")
                    prop(Episode::description).isEqualTo("Lorem Ipsum episode 2 description")
                    prop(Episode::enclosure).all {
                        prop(Enclosure::url).isEqualTo("http://example.org/episode2.mp3")
                        prop(Enclosure::type).isEqualTo("audio/mp3")
                        prop(Enclosure::length).isEqualTo(78133)
                    }
                }
            }
        }
    }

    /* ktlint-disable max-line-length */
    @Test
    internal fun `should parse a complex feed correctly`() {
        val document = validRssDocument("/xml/rss-complex.xml")
        assertThat(PodcastRssParser.parse(document)).isNotNull().all {
            prop(Podcast::generator).isEqualTo("Fireside (https://fireside.fm)")
            prop(Podcast::title).isEqualTo("Smashing Security")
            prop(Podcast::link).isEqualTo("http://www.smashingsecurity.com")
            prop(Podcast::pubDate).isEqualTo(dateTime(year = 2020, month = Month.DECEMBER, day = 17, hour = 0, minute = 45, second = 6))
            prop(Podcast::description).isEqualTo(
                "A helpful and hilarious take on the week's tech SNAFUs. Computer security industry veterans <a href=\"https://www.smashingsecurity.com/hosts/graham-cluley\">Graham Cluley</a> and <a href=\"https://www.smashingsecurity.com/hosts/carole-theriault\">Carole Theriault</a> chat with <a href=\"https://www.smashingsecurity.com/guests\">guests</a> about cybercrime, hacking, and online privacy.   It's not your typical cybersecurity podcast...\n" +
                    "Winner of the \"Best Security Podcast 2018\" and \"Best Security Podcast 2019\", Smashing Security has had over four million downloads. Past guests include Garry Kasparov, Mikko Hyppönen, and Rory Cellan-Jones.\n" +
                    "Follow the podcast on Twitter at <a href=\"https://twitter.com/SmashinSecurity\">@SmashinSecurity</a>, and subscribe for free in your favourite podcast app. New episodes released at 7pm EST every Wednesday (midnight UK)."
            )
            prop(Podcast::language).isEqualTo(Locale.US)
            prop(Podcast::itunes).isNotNull().all {
                prop(PodcastItunes::type).isEqualTo(ShowType.EPISODIC)
                prop(PodcastItunes::subtitle).isEqualTo("News and views from the world of cybersecurity, hacking, and internet threats")
                prop(PodcastItunes::author).isEqualTo("Graham Cluley, Carole Theriault")
                prop(PodcastItunes::summary).isEqualTo(
                    "A helpful and hilarious take on the week's tech SNAFUs. Computer security industry veterans <a href=\"https://www.smashingsecurity.com/hosts/graham-cluley\">Graham Cluley</a> and <a href=\"https://www.smashingsecurity.com/hosts/carole-theriault\">Carole Theriault</a> chat with <a href=\"https://www.smashingsecurity.com/guests\">guests</a> about cybercrime, hacking, and online privacy.   It's not your typical cybersecurity podcast...\n" +
                        "Winner of the \"Best Security Podcast 2018\" and \"Best Security Podcast 2019\", Smashing Security has had over four million downloads. Past guests include Garry Kasparov, Mikko Hyppönen, and Rory Cellan-Jones.\n" +
                        "Follow the podcast on Twitter at <a href=\"https://twitter.com/SmashinSecurity\">@SmashinSecurity</a>, and subscribe for free in your favourite podcast app. New episodes released at 7pm EST every Wednesday (midnight UK)."
                )
                prop(PodcastItunes::image).isNotNull()
                    .prop(HrefOnlyImage::href)
                    .isEqualTo("https://assets.fireside.fm/file/fireside-images/podcasts/images/d/dd3252a8-95c3-41f8-a8a0-9d5d2f9e0bc6/cover.jpg?v=1")
                prop(PodcastItunes::explicit).isNotNull().isTrue()
                prop(PodcastItunes::keywords).isEqualTo("computer security, cybersecurity, hacking, privacy, cybercrime, cyber, cyberwarfare, infosec")
                prop(PodcastItunes::owner).isNotNull().all {
                    prop(ItunesOwner::name).isEqualTo("Graham Cluley, Carole Theriault")
                    prop(ItunesOwner::email).isEqualTo("studio@smashingsecurity.com")
                }
                prop(PodcastItunes::categories).containsExactly(
                    ItunesCategory.TECHNOLOGY,
                    ItunesCategory.TECH_NEWS,
                    ItunesCategory.COMEDY
                )
            }
            prop(Podcast::atom).isNull()
            prop(Podcast::copyright).isNull()
            prop(Podcast::docs).isNull()
            prop(Podcast::feedpress).isNull()
            prop(Podcast::fyyd).isNull()
            prop(Podcast::googleplay).isNull()
            prop(Podcast::lastBuildDate).isNull()
            prop(Podcast::managingEditor).isNull()
            prop(Podcast::webMaster).isNull()

            prop(Podcast::episodes).given { episodes ->
                assertThat(episodes).hasSize(5)

                assertThat(episodes[0]).all {
                    prop(Episode::title).isEqualTo("204: Green buttons, Olympic attacks, and... an apology")
                    prop(Episode::link).isEqualTo("http://www.smashingsecurity.com/204")
                    prop(Episode::guid).isNotNull().all {
                        prop(Guid::guid).isEqualTo("2ed98bdd-ea95-4129-98cf-ee23dd2ab478")
                        prop(Guid::isPermalink).isNotNull().isFalse()
                    }
                    prop(Episode::pubDate).isEqualTo(dateTime(year = 2020, month = Month.NOVEMBER, day = 12))
                    prop(Episode::author).isEqualTo("Graham Cluley, Carole Theriault")
                    prop(Episode::enclosure).all {
                        prop(Enclosure::url).isEqualTo("https://aphid.fireside.fm/d/1437767933/dd3252a8-95c3-41f8-a8a0-9d5d2f9e0bc6/2ed98bdd-ea95-4129-98cf-ee23dd2ab478.mp3")
                        prop(Enclosure::type).isEqualTo("audio/mpeg")
                        prop(Enclosure::length).isEqualTo(70104299)
                    }
                    prop(Episode::description).isEqualTo(
                        "Darknet Diaries host Jack Rhysider joins us to discuss a cybersecurity goof in the wake of the US presidential elections, the US finally fingering the hackers responsible for disrupting the Winter Olympics in South Korea, and to take a long hard look at long hard legal mumbojumbo...\n" +
                            "All this and much much more is discussed in the latest edition of the \"Smashing Security\" podcast by computer security veterans <a href=\"https://www.smashingsecurity.com/hosts/graham-cluley\">Graham Cluley</a> and <a href= \"https://www.smashingsecurity.com/hosts/carole-theriault\">Carole Theriault</a>, joined this week by Jack Rhysider from Darknet Diaries.\n" +
                            "Plus don't miss our featured interview with Mimecast's Danielle Papadakis.\n" +
                            "Visit <a href=\"https://www.smashingsecurity.com/204\">https://www.smashingsecurity.com/204</a> to check out this episode’s show notes and episode links.\n" +
                            "Follow the show on Twitter at <a href= \"https://twitter.com/smashinsecurity\">@SmashinSecurity</a>, or on the <a href=\"https://www.reddit.com/r/smashingsecurity\">Smashing Security subreddit</a>, or <a href=\"https://www.smashingsecurity.com/\">visit our website</a> for more episodes.\n" +
                            "Remember: Subscribe on <a href= \"https://apple.co/2J1YMCu\">Apple Podcasts</a>, or your favourite podcast app, to catch all of the episodes as they go live. Thanks for listening!\n" +
                            "Warning: This podcast may contain nuts, adult themes, and rude language.\n" +
                            "Theme tune: \"Vinyl Memories\" by Mikael Manvelyan.\n" +
                            "Assorted sound effects: AudioBlocks. Special Guests: Danielle Papadakis and Jack Rhysider."
                    )
                    prop(Episode::itunes).isNotNull().all {
                        prop(EpisodeItunes::episodeType).isEqualTo(EpisodeType.FULL)
                        prop(EpisodeItunes::author).isEqualTo("Graham Cluley, Carole Theriault")
                        prop(EpisodeItunes::subtitle).isEqualTo("Darknet Diaries host Jack Rhysider joins us to discuss a cybersecurity goof in the wake of the US presidential elections, the US finally fingering the hackers responsible for disrupting the Winter Olympics in South Korea, and to take a long hard look at long hard legal mumbojumbo...")
                        prop(EpisodeItunes::duration).isEqualTo(StyledDuration.hoursMinutesSeconds(1, 12, 57))
                        prop(EpisodeItunes::explicit).isNotNull().isTrue()
                        prop(EpisodeItunes::image).isNotNull()
                            .prop(HrefOnlyImage::href)
                            .isEqualTo("https://assets.fireside.fm/file/fireside-images/podcasts/images/d/dd3252a8-95c3-41f8-a8a0-9d5d2f9e0bc6/cover.jpg?v=1")
                        prop(EpisodeItunes::summary).isEqualTo(
                            "<p>Darknet Diaries host Jack Rhysider joins us to discuss a cybersecurity goof in the wake of the US presidential elections, the US finally fingering the hackers responsible for disrupting the Winter Olympics in South Korea, and to take a long hard look at long hard legal mumbojumbo...</p>\n" +
                                "\n" +
                                "<p>All this and much much more is discussed in the latest edition of the &quot;Smashing Security&quot; podcast by computer security veterans <a href=\"https://www.smashingsecurity.com/hosts/graham-cluley\">Graham Cluley</a> and <a href= \"https://www.smashingsecurity.com/hosts/carole-theriault\">Carole Theriault</a>, joined this week by Jack Rhysider from Darknet Diaries.</p>\n" +
                                "\n" +
                                "<p>Plus don&#39;t miss our featured interview with Mimecast&#39;s Danielle Papadakis.</p>\n" +
                                "\n" +
                                "<p>Visit <a href=\"https://www.smashingsecurity.com/204\"><a href=\"https://www.smashingsecurity.com/204\" rel=\"nofollow\">https://www.smashingsecurity.com/204</a></a> to check out this episode’s show notes and episode links.</p>\n" +
                                "\n" +
                                "<p>Follow the show on Twitter at <a href= \"https://twitter.com/smashinsecurity\">@SmashinSecurity</a>, or on the <a href=\"https://www.reddit.com/r/smashingsecurity\">Smashing Security subreddit</a>, or <a href=\"https://www.smashingsecurity.com/\">visit our website</a> for more episodes.</p>\n" +
                                "\n" +
                                "<p>Remember: Subscribe on <a href= \"https://apple.co/2J1YMCu\">Apple Podcasts</a>, or your favourite podcast app, to catch all of the episodes as they go live. Thanks for listening!</p>\n" +
                                "\n" +
                                "<p>Warning: This podcast may contain nuts, adult themes, and rude language.</p>\n" +
                                "\n" +
                                "<p>Theme tune: &quot;Vinyl Memories&quot; by Mikael Manvelyan.<br>\n" +
                                "Assorted sound effects: AudioBlocks.</p><p>Special Guests: Danielle Papadakis and Jack Rhysider.</p><p>Sponsored By:</p><ul><li><a href=\"https://www.smashingsecurity.com/mimecasthub\" rel=\"nofollow\">Mimecast</a>: <a href=\"https://www.smashingsecurity.com/mimecasthub\" rel=\"nofollow\">Mimecast's State of Email Security 2020 report helps you understand the most pervasive threats and how they attack organizations at their email perimeters, from inside the organization (through compromised accounts, vulnerable insiders, social engineering), or beyond the organization’s perimeters (the domains they own and their brands via impersonation).\n" +
                                "\n" +
                                "Grab your copy at smashingsecurity.com/mimecasthub</a></li><li><a href=\"https://www.lastpass.com/smashing\" rel=\"nofollow\">LastPass</a>: <a href=\"https://www.lastpass.com/smashing\" rel=\"nofollow\">LastPass Enterprise simplifies password management for companies of every size, with the right tools to secure your business with centralized control of employee passwords and apps.\n" +
                                "\n" +
                                "But, LastPass isn’t just for enterprises, it’s an equally great solution for business teams, families and single users.\n" +
                                "\n" +
                                "Go to lastpass.com/smashing to see why LastPass is the trusted enterprise password manager of over 33 thousand businesses.</a></li><li><a href=\"https://www.smashingsecurity.com/kroll\" rel=\"nofollow\">Kroll</a>: <a href=\"https://www.smashingsecurity.com/kroll\" rel=\"nofollow\">Rapidly detecting a threat is meaningless without the ability to respond with confidence.  Kroll responds to over 2,000 cyber incidents every year and is uniquely positioned to bring that capability and expertise 24x7 with Responder.  Kroll Responder merges hunting, detection, containment and remediation to deliver best-in-class endpoint security.\n" +
                                "\n" +
                                "See how Responder works at smashingsecurity.com/kroll</a></li></ul><p><a href=\"https://www.patreon.com/smashingsecurity\" rel=\"payment\">Support Smashing Security</a></p><p>Links:</p><ul><li><a href=\"https://cdn.donaldjtrump.com/public-files/press_assets/verified-complaint-with-attachments.pdf\" title=\"Legal complaint on behalf of Donald J Trump for President Inc and Republican National Committee\" rel=\"nofollow\">Legal complaint on behalf of Donald J Trump for President Inc and Republican National Committee</a> &mdash; PDF.</li><li><a href=\"https://donttouchthegreenbutton.com/\" title=\"Don't touch the green button!\" rel=\"nofollow\">Don't touch the green button!</a></li><li><a href=\"https://www.reddit.com/r/privacy/comments/jq4y8w/the_trump_campaign_hastily_setup_a_website_to/\" title=\"Reddit thread about Donttouchthegreenbutton.com\" rel=\"nofollow\">Reddit thread about Donttouchthegreenbutton.com</a></li><li><a href=\"https://twitter.com/richeyward/status/1325412472505987072\" title=\"Richey Ward's Twitter thread showing how over 163k records were exposed in the Don't Touch The Green Button database\" rel=\"nofollow\">Richey Ward's Twitter thread showing how over 163k records were exposed in the Don't Touch The Green Button database</a> &mdash; Twitter.</li><li><a href=\"https://www.bleepingcomputer.com/news/security/trump-lawsuit-site-to-report-rejected-votes-leaked-voter-data/\" title=\"Trump lawsuit site to report 'rejected votes' leaked voter data\" rel=\"nofollow\">Trump lawsuit site to report 'rejected votes' leaked voter data</a> &mdash; Bleeping Computer.</li><li><a href=\"https://twitter.com/BBCRosAtkins/status/1325905080189669381\" title=\"Hilarious news report of the Four Seasons Total Landscaping debacle\" rel=\"nofollow\">Hilarious news report of the Four Seasons Total Landscaping debacle</a> &mdash; Tweet by Ros Atkins of the BBC.</li><li><a href=\"https://grahamcluley.com/donald-trump-twitter-password/\" title=\"“Yourefired” was Donald Trump’s Twitter password, claim hackers\" rel=\"nofollow\">“Yourefired” was Donald Trump’s Twitter password, claim hackers</a> &mdash; Graham Cluley.</li><li><a href=\"https://grahamcluley.com/donald-trumps-twitter-password-is-maga2020-and-theres-no-2fa-claims-hacker/\" title=\"Donald Trump’s Twitter password is “maga2020!”, and there’s no 2FA, claims hacker\" rel=\"nofollow\">Donald Trump’s Twitter password is “maga2020!”, and there’s no 2FA, claims hacker</a> &mdash; Graham Cluley.</li><li><a href=\"https://www.justice.gov/opa/pr/six-russian-gru-officers-charged-connection-worldwide-deployment-destructive-malware-and\" title=\"Six Russian GRU Officers Charged in Connection with Worldwide Deployment of Destructive Malware and Other Disruptive Actions in Cyberspace\" rel=\"nofollow\">Six Russian GRU Officers Charged in Connection with Worldwide Deployment of Destructive Malware and Other Disruptive Actions in Cyberspace</a> &mdash; Department of Justice.</li><li><a href=\"https://www.thinkmoney.co.uk/blog/what-phones-know-about-you/\" title=\"What does your phone know about you? \" rel=\"nofollow\">What does your phone know about you? </a> &mdash; Think Money.</li><li><a href=\"https://www.bbc.co.uk/news/technology-54838978\" title=\"Popular app T&Cs 'longer than Harry Potter' \" rel=\"nofollow\">Popular app T&Cs 'longer than Harry Potter' </a> &mdash; BBC News.</li><li><a href=\"https://ec.europa.eu/info/sites/info/files/terms_and_conditions_final_report_en.pdf\" title=\"Study on consumers' attitudes towards Terms and Conditions (T&Cs)\" rel=\"nofollow\">Study on consumers' attitudes towards Terms and Conditions (T&Cs)</a> &mdash; European Commission (PDF).</li><li><a href=\"https://tosdr.org/\" title=\"Terms of Service; Didn't Read\" rel=\"nofollow\">Terms of Service; Didn't Read</a></li><li><a href=\"https://tldrlegal.com/\" title=\"TLDRLegal\" rel=\"nofollow\">TLDRLegal</a> &mdash; Software Licenses Explained in Plain English.</li><li><a href=\"https://www.termsfeed.com/\" title=\"TermsFeed\" rel=\"nofollow\">TermsFeed</a> &mdash; Generator of Privacy Policy, Terms & Conditions, Disclaimer, EULA.</li><li><a href=\"https://simply-docs.co.uk/Home\" title=\"Simply Docs\" rel=\"nofollow\">Simply Docs</a> &mdash; Legal, Business & Property Documents & Templates.</li><li><a href=\"https://www.youtube.com/watch?v=pss6RPtENPI\" title=\"The Armstrongs Episode 1 Part 1 \" rel=\"nofollow\">The Armstrongs Episode 1 Part 1 </a> &mdash; YouTube.</li><li><a href=\"https://oralbreeze.com/\" title=\"Oral Breeze\" rel=\"nofollow\">Oral Breeze</a> &mdash; Jack's pick for the best dental irrigator for water flossing. </li><li><a href=\"https://podcasts.apple.com/gb/podcast/youre-wrong-about/id1380008439\" title=\"\u200EYou're Wrong About\" rel=\"nofollow\">\u200EYou're Wrong About</a> &mdash; Apple Podcasts.</li><li><a href=\"https://www.smashingsecurity.com/store\" title=\"Smashing Security merchandise (t-shirts, mugs, stickers and stuff)\" rel=\"nofollow\">Smashing Security merchandise (t-shirts, mugs, stickers and stuff)</a></li></ul>"
                        )
                    }
                    prop(Episode::content).isNotNull()
                        .prop(Content::encoded).isEqualTo(
                            "<p>Darknet Diaries host Jack Rhysider joins us to discuss a cybersecurity goof in the wake of the US presidential elections, the US finally fingering the hackers responsible for disrupting the Winter Olympics in South Korea, and to take a long hard look at long hard legal mumbojumbo...</p>\n" +
                                "\n" +
                                "<p>All this and much much more is discussed in the latest edition of the &quot;Smashing Security&quot; podcast by computer security veterans <a href=\"https://www.smashingsecurity.com/hosts/graham-cluley\">Graham Cluley</a> and <a href= \"https://www.smashingsecurity.com/hosts/carole-theriault\">Carole Theriault</a>, joined this week by Jack Rhysider from Darknet Diaries.</p>\n" +
                                "\n" +
                                "<p>Plus don&#39;t miss our featured interview with Mimecast&#39;s Danielle Papadakis.</p>\n" +
                                "\n" +
                                "<p>Visit <a href=\"https://www.smashingsecurity.com/204\"><a href=\"https://www.smashingsecurity.com/204\" rel=\"nofollow\">https://www.smashingsecurity.com/204</a></a> to check out this episode’s show notes and episode links.</p>\n" +
                                "\n" +
                                "<p>Follow the show on Twitter at <a href= \"https://twitter.com/smashinsecurity\">@SmashinSecurity</a>, or on the <a href=\"https://www.reddit.com/r/smashingsecurity\">Smashing Security subreddit</a>, or <a href=\"https://www.smashingsecurity.com/\">visit our website</a> for more episodes.</p>\n" +
                                "\n" +
                                "<p>Remember: Subscribe on <a href= \"https://apple.co/2J1YMCu\">Apple Podcasts</a>, or your favourite podcast app, to catch all of the episodes as they go live. Thanks for listening!</p>\n" +
                                "\n" +
                                "<p>Warning: This podcast may contain nuts, adult themes, and rude language.</p>\n" +
                                "\n" +
                                "<p>Theme tune: &quot;Vinyl Memories&quot; by Mikael Manvelyan.<br>\n" +
                                "Assorted sound effects: AudioBlocks.</p><p>Special Guests: Danielle Papadakis and Jack Rhysider.</p><p>Sponsored By:</p><ul><li><a href=\"https://www.smashingsecurity.com/mimecasthub\" rel=\"nofollow\">Mimecast</a>: <a href=\"https://www.smashingsecurity.com/mimecasthub\" rel=\"nofollow\">Mimecast's State of Email Security 2020 report helps you understand the most pervasive threats and how they attack organizations at their email perimeters, from inside the organization (through compromised accounts, vulnerable insiders, social engineering), or beyond the organization’s perimeters (the domains they own and their brands via impersonation).\n" +
                                "\n" +
                                "Grab your copy at smashingsecurity.com/mimecasthub</a></li><li><a href=\"https://www.lastpass.com/smashing\" rel=\"nofollow\">LastPass</a>: <a href=\"https://www.lastpass.com/smashing\" rel=\"nofollow\">LastPass Enterprise simplifies password management for companies of every size, with the right tools to secure your business with centralized control of employee passwords and apps.\n" +
                                "\n" +
                                "But, LastPass isn’t just for enterprises, it’s an equally great solution for business teams, families and single users.\n" +
                                "\n" +
                                "Go to lastpass.com/smashing to see why LastPass is the trusted enterprise password manager of over 33 thousand businesses.</a></li><li><a href=\"https://www.smashingsecurity.com/kroll\" rel=\"nofollow\">Kroll</a>: <a href=\"https://www.smashingsecurity.com/kroll\" rel=\"nofollow\">Rapidly detecting a threat is meaningless without the ability to respond with confidence.  Kroll responds to over 2,000 cyber incidents every year and is uniquely positioned to bring that capability and expertise 24x7 with Responder.  Kroll Responder merges hunting, detection, containment and remediation to deliver best-in-class endpoint security.\n" +
                                "\n" +
                                "See how Responder works at smashingsecurity.com/kroll</a></li></ul><p><a href=\"https://www.patreon.com/smashingsecurity\" rel=\"payment\">Support Smashing Security</a></p><p>Links:</p><ul><li><a href=\"https://cdn.donaldjtrump.com/public-files/press_assets/verified-complaint-with-attachments.pdf\" title=\"Legal complaint on behalf of Donald J Trump for President Inc and Republican National Committee\" rel=\"nofollow\">Legal complaint on behalf of Donald J Trump for President Inc and Republican National Committee</a> &mdash; PDF.</li><li><a href=\"https://donttouchthegreenbutton.com/\" title=\"Don't touch the green button!\" rel=\"nofollow\">Don't touch the green button!</a></li><li><a href=\"https://www.reddit.com/r/privacy/comments/jq4y8w/the_trump_campaign_hastily_setup_a_website_to/\" title=\"Reddit thread about Donttouchthegreenbutton.com\" rel=\"nofollow\">Reddit thread about Donttouchthegreenbutton.com</a></li><li><a href=\"https://twitter.com/richeyward/status/1325412472505987072\" title=\"Richey Ward's Twitter thread showing how over 163k records were exposed in the Don't Touch The Green Button database\" rel=\"nofollow\">Richey Ward's Twitter thread showing how over 163k records were exposed in the Don't Touch The Green Button database</a> &mdash; Twitter.</li><li><a href=\"https://www.bleepingcomputer.com/news/security/trump-lawsuit-site-to-report-rejected-votes-leaked-voter-data/\" title=\"Trump lawsuit site to report 'rejected votes' leaked voter data\" rel=\"nofollow\">Trump lawsuit site to report 'rejected votes' leaked voter data</a> &mdash; Bleeping Computer.</li><li><a href=\"https://twitter.com/BBCRosAtkins/status/1325905080189669381\" title=\"Hilarious news report of the Four Seasons Total Landscaping debacle\" rel=\"nofollow\">Hilarious news report of the Four Seasons Total Landscaping debacle</a> &mdash; Tweet by Ros Atkins of the BBC.</li><li><a href=\"https://grahamcluley.com/donald-trump-twitter-password/\" title=\"“Yourefired” was Donald Trump’s Twitter password, claim hackers\" rel=\"nofollow\">“Yourefired” was Donald Trump’s Twitter password, claim hackers</a> &mdash; Graham Cluley.</li><li><a href=\"https://grahamcluley.com/donald-trumps-twitter-password-is-maga2020-and-theres-no-2fa-claims-hacker/\" title=\"Donald Trump’s Twitter password is “maga2020!”, and there’s no 2FA, claims hacker\" rel=\"nofollow\">Donald Trump’s Twitter password is “maga2020!”, and there’s no 2FA, claims hacker</a> &mdash; Graham Cluley.</li><li><a href=\"https://www.justice.gov/opa/pr/six-russian-gru-officers-charged-connection-worldwide-deployment-destructive-malware-and\" title=\"Six Russian GRU Officers Charged in Connection with Worldwide Deployment of Destructive Malware and Other Disruptive Actions in Cyberspace\" rel=\"nofollow\">Six Russian GRU Officers Charged in Connection with Worldwide Deployment of Destructive Malware and Other Disruptive Actions in Cyberspace</a> &mdash; Department of Justice.</li><li><a href=\"https://www.thinkmoney.co.uk/blog/what-phones-know-about-you/\" title=\"What does your phone know about you? \" rel=\"nofollow\">What does your phone know about you? </a> &mdash; Think Money.</li><li><a href=\"https://www.bbc.co.uk/news/technology-54838978\" title=\"Popular app T&Cs 'longer than Harry Potter' \" rel=\"nofollow\">Popular app T&Cs 'longer than Harry Potter' </a> &mdash; BBC News.</li><li><a href=\"https://ec.europa.eu/info/sites/info/files/terms_and_conditions_final_report_en.pdf\" title=\"Study on consumers' attitudes towards Terms and Conditions (T&Cs)\" rel=\"nofollow\">Study on consumers' attitudes towards Terms and Conditions (T&Cs)</a> &mdash; European Commission (PDF).</li><li><a href=\"https://tosdr.org/\" title=\"Terms of Service; Didn't Read\" rel=\"nofollow\">Terms of Service; Didn't Read</a></li><li><a href=\"https://tldrlegal.com/\" title=\"TLDRLegal\" rel=\"nofollow\">TLDRLegal</a> &mdash; Software Licenses Explained in Plain English.</li><li><a href=\"https://www.termsfeed.com/\" title=\"TermsFeed\" rel=\"nofollow\">TermsFeed</a> &mdash; Generator of Privacy Policy, Terms & Conditions, Disclaimer, EULA.</li><li><a href=\"https://simply-docs.co.uk/Home\" title=\"Simply Docs\" rel=\"nofollow\">Simply Docs</a> &mdash; Legal, Business & Property Documents & Templates.</li><li><a href=\"https://www.youtube.com/watch?v=pss6RPtENPI\" title=\"The Armstrongs Episode 1 Part 1 \" rel=\"nofollow\">The Armstrongs Episode 1 Part 1 </a> &mdash; YouTube.</li><li><a href=\"https://oralbreeze.com/\" title=\"Oral Breeze\" rel=\"nofollow\">Oral Breeze</a> &mdash; Jack's pick for the best dental irrigator for water flossing. </li><li><a href=\"https://podcasts.apple.com/gb/podcast/youre-wrong-about/id1380008439\" title=\"\u200EYou're Wrong About\" rel=\"nofollow\">\u200EYou're Wrong About</a> &mdash; Apple Podcasts.</li><li><a href=\"https://www.smashingsecurity.com/store\" title=\"Smashing Security merchandise (t-shirts, mugs, stickers and stuff)\" rel=\"nofollow\">Smashing Security merchandise (t-shirts, mugs, stickers and stuff)</a></li></ul>"
                        )
                }
            }
        }
    }
    /* ktlint-enable max-line-length */

    private fun createDocumentWithNode(localName: String) = createDocument().apply {
        val element = createElement(localName)
        appendChild(element)
    }

    private fun createDocument() = documentBuilder.newDocument()

    private fun validRssDocument(path: String = "/xml/rss.xml") = documentFromResource(path)
}
