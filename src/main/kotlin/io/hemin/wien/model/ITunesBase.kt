package io.hemin.wien.model

interface ITunesBase {
    val image: Image?
    val explicit: Boolean?
    val block: Boolean?
    val title: String?
    val subtitle: String?
    val summary: String?
    val author: String?
}
