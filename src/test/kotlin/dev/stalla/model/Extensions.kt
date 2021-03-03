package dev.stalla.model

import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

inline fun <reified T : Any> arguments(vararg args: T): ArgumentsProvider = ArgumentsProvider {
    Stream.of(*args).map { Arguments.of(it) }
}
