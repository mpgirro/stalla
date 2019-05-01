package io.hemin.wien.model.builder

interface Builder<out T> {

    /** Returns an instance of the type parameter created from the fields set on this builder. */
    fun build(): T

}

