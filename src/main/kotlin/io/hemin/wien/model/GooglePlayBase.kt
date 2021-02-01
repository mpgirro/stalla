package io.hemin.wien.model

internal interface GooglePlayBase {

    val description: String?
    val explicit: Boolean?
    val block: Boolean
    val image: HrefOnlyImage?
}
