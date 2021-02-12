package dev.stalla.model.googleplay

import dev.stalla.model.HrefOnlyImage

internal interface GoogleplayBase2 {

    val description: String?
    val explicit: Boolean?
    val block: Boolean
    val image: HrefOnlyImage?
}
