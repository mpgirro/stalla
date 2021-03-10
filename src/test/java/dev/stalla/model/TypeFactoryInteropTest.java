package dev.stalla.model;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import static dev.stalla.model.FixturesKt.getAllTypeFactorySubTypes;
import static org.junit.jupiter.api.Assertions.*;

public class TypeFactoryInteropTest {

    private static class TypeFactorySubTypeProvider implements ArgumentsProvider {
        @SuppressWarnings("KotlinInternalInJava")
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return getAllTypeFactorySubTypes().stream().map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TypeFactorySubTypeProvider.class)
    void shouldExposeAStaticTypeFactoryMethod(Class<?> clazz) throws NoSuchMethodException {
        final Method method = clazz.getMethod("of", String.class);
        assertAll("provides static of(String) method",
            () -> assertNotNull(method),
            () -> assertTrue(Modifier.isStatic(method.getModifiers()))
        );
    }

}
