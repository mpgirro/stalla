package io.hemin.wien.model

interface GooglePlayBase {

    val description: String?
    val explicit: Boolean?
    val block: Boolean?
    val image: Image.HrefOnlyImage?
}
