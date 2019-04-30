package io.hemin.wien.model.builder

interface Builder<out T> {
    fun build(): T
}

