package dev.stalla.model.googleplay

import dev.stalla.model.HrefOnlyImage

internal interface GooglePlayBase {

    val description: String?
    val explicit: Boolean?
    val block: Boolean
    val image: HrefOnlyImage?
}
