package dev.stalla.model.itunes

import dev.stalla.model.HrefOnlyImage
import dev.stalla.util.InternalAPI

@InternalAPI
internal interface ItunesBase {
    val image: HrefOnlyImage?
    val block: Boolean
    val title: String?
    val subtitle: String?
    val summary: String?
    val author: String?
}
