package dev.stalla.model.itunes;

import dev.stalla.model.ItunesCategoryNameProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static dev.stalla.TestUtilKt.getFieldInstance;
import static dev.stalla.TestUtilKt.getStaticFieldsByType;
import static dev.stalla.model.FixturesKt.getAllItunesCategoryNames;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItunesCategoryInteropTest {

    private static Map<String, ItunesCategory> staticPropertyMap;

    private static class ItunesCategoryJavaPropertyProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return getItunesCategoryInstances().stream().map(Arguments::of);
        }
    }

    @BeforeAll
    public static void init(){
        staticPropertyMap = new HashMap<>();
        for (ItunesCategory category : getItunesCategoryInstances()) {
            staticPropertyMap.put(category.getType(), category);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryJavaPropertyProvider.class)
    void shouldOnlyExposeDefinedItunesCategoryInstances(ItunesCategory category) {
        assertTrue(getAllItunesCategoryNames().contains(category.getType()));
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryNameProvider.class)
    void shouldExposeAStaticFieldForEveryDefinedItunesCategory(String categoryName) {
        assertTrue(staticPropertyMap.containsKey(categoryName));
    }

    private static List<ItunesCategory> getItunesCategoryInstances() {
        return Objects.requireNonNull(getStaticFieldsByType(ItunesCategory.class))
            .stream()
            .map(field -> getFieldInstance(field, ItunesCategory.TECHNOLOGY))
            .filter(Objects::nonNull)
            .map(field -> (ItunesCategory) field)
            .collect(toList());
    }

}
