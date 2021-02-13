package dev.stalla.model.itunes

public interface ItunesCategoryFactory {

    /** Returns an [ItunesCategory] with the name matching [category] if such exists. */
    public fun from(category: String?): ItunesCategory?
}
