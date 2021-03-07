package dev.stalla.model.googleplay

import dev.stalla.model.HrefOnlyImage
import dev.stalla.util.InternalAPI

@InternalAPI
internal interface GoogleplayBase {
    val author: String?
    val description: String?
    val block: Boolean
    val image: HrefOnlyImage?
}
