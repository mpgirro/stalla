package io.hemin.wien.model

interface ITunesBase {
    val image: HrefOnlyImage?
    val block: Boolean
    val title: String?
    val subtitle: String?
    val summary: String?
    val author: String?
}
