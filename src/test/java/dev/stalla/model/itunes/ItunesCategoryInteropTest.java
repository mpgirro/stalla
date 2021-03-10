package dev.stalla.model.itunes;

import dev.stalla.model.ItunesCategoryNameProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static dev.stalla.model.FixturesKt.getAllItunesCategoryNames;
import static dev.stalla.model.FixturesKt.getStaticItunesCategoryProperties;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItunesCategoryInteropTest {

    private static final List<String> allItunesCategoryNames = getAllItunesCategoryNames();
    private static Map<String, ItunesCategory> staticPropertyMap;

    private static class ItunesCategoryStaticJavaPropertyProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Arrays.stream(getStaticItunesCategoryProperties()).map(Arguments::of);
        }
    }

    @BeforeAll
    public static void init(){
        staticPropertyMap = new HashMap<>();
        for (ItunesCategory category : getStaticItunesCategoryProperties()) {
            staticPropertyMap.put(category.getType(), category);
        }
    }

    @ParameterizedTest
    @DisplayName("should only expose defined values through static members")
    @ArgumentsSource(ItunesCategoryStaticJavaPropertyProvider.class)
    void shouldOnlyExposeDefinedItunesCategoryInstances(ItunesCategory category) {
        assertTrue(allItunesCategoryNames.contains(category.getType()));
    }

    @ParameterizedTest
    @DisplayName("should expose a static member for every defined iTunes category")
    @ArgumentsSource(ItunesCategoryNameProvider.class)
    void shouldExposeAStaticFieldForEveryDefinedItunesCategory(String categoryName) {
        assertTrue(staticPropertyMap.containsKey(categoryName));
    }

}
