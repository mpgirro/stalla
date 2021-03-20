package dev.stalla.model;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import static dev.stalla.model.FixturesKt.getAllBuilderFactorySubTypes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuilderFactoryInteropTest {

    private static class BuilderFactorySubTypeProvider implements ArgumentsProvider {
        @SuppressWarnings("KotlinInternalInJava")
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return getAllBuilderFactorySubTypes().stream().map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(BuilderFactorySubTypeProvider.class)
    void shouldExposeAStaticBuilderFactoryMethod(Class<?> clazz) throws NoSuchMethodException {
        final Method method = clazz.getMethod("builder");
        assertAll("provides static builder() method",
            () -> assertNotNull(method),
            () -> assertTrue(Modifier.isStatic(method.getModifiers()))
        );
    }

}
