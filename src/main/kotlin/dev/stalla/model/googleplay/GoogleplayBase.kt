package dev.stalla.model.googleplay

import dev.stalla.model.HrefOnlyImage

internal interface GoogleplayBase {
    val author: String?
    val description: String?
    val block: Boolean
    val image: HrefOnlyImage?
}
