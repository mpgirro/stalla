package dev.stalla.model.itunes

import dev.stalla.model.HrefOnlyImage

internal interface ItunesBase2 {
    val image: HrefOnlyImage?
    val block: Boolean
    val title: String?
    val subtitle: String?
    val summary: String?
    val author: String?
}
