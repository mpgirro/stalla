package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.fake.FakeHrefOnlyImageBuilder
import io.hemin.wien.builder.fake.FakeITunesStyleCategoryBuilder
import io.hemin.wien.builder.fake.FakeLinkBuilder
import io.hemin.wien.builder.fake.FakePersonBuilder
import io.hemin.wien.builder.fake.FakeRssCategoryBuilder
import io.hemin.wien.builder.fake.FakeRssImageBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.model.Podcast
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
    var imageBuilder: RssImageBuilder? = null

    val episodeBuilders: MutableList<EpisodeBuilder> = mutableListOf()
    val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()

    override val iTunes: FakePodcastITunesBuilder = FakePodcastITunesBuilder()

    override val atom: FakePodcastAtomBuilder = FakePodcastAtomBuilder()

    override val fyyd: FakePodcastFyydBuilder = FakePodcastFyydBuilder()

    override val feedpress: FakePodcastFeedpressBuilder = FakePodcastFeedpressBuilder()

    override val googlePlay: FakePodcastGooglePlayBuilder = FakePodcastGooglePlayBuilder()

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

    override fun createITunesCategoryBuilder(): ITunesStyleCategoryBuilder = FakeITunesStyleCategoryBuilder()

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
        if (imageBuilder != other.imageBuilder) return false
        if (episodeBuilders != other.episodeBuilders) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (iTunes != other.iTunes) return false
        if (atom != other.atom) return false
        if (fyyd != other.fyyd) return false
        if (feedpress != other.feedpress) return false
        if (googlePlay != other.googlePlay) return false

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
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        result = 31 * result + episodeBuilders.hashCode()
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + iTunes.hashCode()
        result = 31 * result + atom.hashCode()
        result = 31 * result + fyyd.hashCode()
        result = 31 * result + feedpress.hashCode()
        result = 31 * result + googlePlay.hashCode()
        return result
    }

    override fun toString() =
        "FakePodcastBuilder(titleValue=$titleValue, linkValue=$linkValue, descriptionValue=$descriptionValue, languageValue=$languageValue, " +
            "pubDate=$pubDate, lastBuildDate=$lastBuildDate, generator=$generator, copyright=$copyright, docs=$docs, " +
            "managingEditor=$managingEditor, webMaster=$webMaster, imageBuilder=$imageBuilder, episodeBuilders=$episodeBuilders, " +
            "categoryBuilders=$categoryBuilders, iTunes=$iTunes, atom=$atom, fyyd=$fyyd, feedpress=$feedpress, googlePlay=$googlePlay)"
}
