package dev.stalla.builder.fake.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.fake.FakeAtomBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.fake.FakeHrefOnlyImageBuilder
import dev.stalla.builder.fake.FakeLinkBuilder
import dev.stalla.builder.fake.FakePersonBuilder
import dev.stalla.builder.fake.FakeRssCategoryBuilder
import dev.stalla.builder.fake.FakeRssImageBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.Podcast
import java.time.temporal.TemporalAccessor

internal class FakePodcastBuilder : FakeBuilder<Podcast>(), PodcastBuilder {

    var titleValue: String? = null
    var linkValue: String? = null
    var descriptionValue: String? = null
    var languageValue: String? = null

    var pubDate: TemporalAccessor? = null
    var lastBuildDate: TemporalAccessor? = null
    var generator: String? = null
    var copyright: String? = null
    var docs: String? = null
    var managingEditor: String? = null
    var webMaster: String? = null
    var ttl: Int? = null
    var imageBuilder: RssImageBuilder? = null

    val episodeBuilders: MutableList<EpisodeBuilder> = mutableListOf()
    val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()

    override val itunesBuilder: FakePodcastItunesBuilder = FakePodcastItunesBuilder()

    override val atomBuilder: FakeAtomBuilder = FakeAtomBuilder()

    override val fyydBuilder: FakePodcastFyydBuilder = FakePodcastFyydBuilder()

    override val feedpressBuilder: FakePodcastFeedpressBuilder = FakePodcastFeedpressBuilder()

    override val googleplayBuilder: FakePodcastGoogleplayBuilder = FakePodcastGoogleplayBuilder()

    override val podcastPodcastindexBuilder: FakePodcastPodcastindexBuilder = FakePodcastPodcastindexBuilder()

    override fun title(title: String): PodcastBuilder = apply { this.titleValue = title }

    override fun link(link: String): PodcastBuilder = apply { this.linkValue = link }

    override fun description(description: String): PodcastBuilder = apply { this.descriptionValue = description }

    override fun pubDate(pubDate: TemporalAccessor?): PodcastBuilder = apply { this.pubDate = pubDate }

    override fun lastBuildDate(lastBuildDate: TemporalAccessor?): PodcastBuilder = apply { this.lastBuildDate = lastBuildDate }

    override fun language(language: String): PodcastBuilder = apply { this.languageValue = language }

    override fun generator(generator: String?): PodcastBuilder = apply { this.generator = generator }

    override fun copyright(copyright: String?): PodcastBuilder = apply { this.copyright = copyright }

    override fun docs(docs: String?): PodcastBuilder = apply { this.docs = docs }

    override fun managingEditor(managingEditor: String?): PodcastBuilder = apply { this.managingEditor = managingEditor }

    override fun webMaster(webMaster: String?): PodcastBuilder = apply { this.webMaster = webMaster }

    override fun ttl(ttl: Int?): PodcastBuilder = apply { this.ttl = ttl }

    override fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder = apply { this.imageBuilder = imageBuilder }

    override fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder = apply {
        episodeBuilders.add(episodeBuilder)
    }

    override fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun createRssImageBuilder(): RssImageBuilder = FakeRssImageBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = FakeHrefOnlyImageBuilder()

    override fun createLinkBuilder(): LinkBuilder = FakeLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = FakePersonBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = FakeRssCategoryBuilder()

    override fun createPodcastPodcastLockedBuilder(): PodcastPodcastindexLockedBuilder = FakePodcastPodcastindexLockedBuilder()

    override fun createPodcastPodcastFundingBuilder(): PodcastPodcastindexFundingBuilder = FakePodcastPodcastindexFundingBuilder()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastBuilder) return false

        if (titleValue != other.titleValue) return false
        if (linkValue != other.linkValue) return false
        if (descriptionValue != other.descriptionValue) return false
        if (languageValue != other.languageValue) return false
        if (pubDate != other.pubDate) return false
        if (lastBuildDate != other.lastBuildDate) return false
        if (generator != other.generator) return false
        if (copyright != other.copyright) return false
        if (docs != other.docs) return false
        if (managingEditor != other.managingEditor) return false
        if (webMaster != other.webMaster) return false
        if (ttl != other.ttl) return false
        if (imageBuilder != other.imageBuilder) return false
        if (episodeBuilders != other.episodeBuilders) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (itunesBuilder != other.itunesBuilder) return false
        if (atomBuilder != other.atomBuilder) return false
        if (fyydBuilder != other.fyydBuilder) return false
        if (feedpressBuilder != other.feedpressBuilder) return false
        if (googleplayBuilder != other.googleplayBuilder) return false
        if (podcastPodcastindexBuilder != other.podcastPodcastindexBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = titleValue?.hashCode() ?: 0
        result = 31 * result + (linkValue?.hashCode() ?: 0)
        result = 31 * result + (descriptionValue?.hashCode() ?: 0)
        result = 31 * result + (languageValue?.hashCode() ?: 0)
        result = 31 * result + (pubDate?.hashCode() ?: 0)
        result = 31 * result + (lastBuildDate?.hashCode() ?: 0)
        result = 31 * result + (generator?.hashCode() ?: 0)
        result = 31 * result + (copyright?.hashCode() ?: 0)
        result = 31 * result + (docs?.hashCode() ?: 0)
        result = 31 * result + (managingEditor?.hashCode() ?: 0)
        result = 31 * result + (webMaster?.hashCode() ?: 0)
        result = 31 * result + (ttl?.hashCode() ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        result = 31 * result + episodeBuilders.hashCode()
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + itunesBuilder.hashCode()
        result = 31 * result + atomBuilder.hashCode()
        result = 31 * result + fyydBuilder.hashCode()
        result = 31 * result + feedpressBuilder.hashCode()
        result = 31 * result + googleplayBuilder.hashCode()
        result = 31 * result + podcastPodcastindexBuilder.hashCode()
        return result
    }

    override fun toString() =
        "FakePodcastBuilder(titleValue=$titleValue, linkValue=$linkValue, descriptionValue=$descriptionValue, languageValue=$languageValue, " +
            "pubDate=$pubDate, lastBuildDate=$lastBuildDate, generator=$generator, copyright=$copyright, docs=$docs, " +
            "managingEditor=$managingEditor, webMaster=$webMaster, ttl=$ttl, imageBuilder=$imageBuilder, episodeBuilders=$episodeBuilders, " +
            "categoryBuilders=$categoryBuilders, iTunesBuilder=$itunesBuilder, atomBuilder=$atomBuilder, fyydBuilder=$fyydBuilder, " +
            "feedpressBuilder=$feedpressBuilder, googlePlayBuilder=$googleplayBuilder, podcastBuilder=$podcastPodcastindexBuilder)"
}
