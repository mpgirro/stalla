package dev.stalla.model.podcastindex;

import dev.stalla.model.TranscriptTypeNameProvider;
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
import static dev.stalla.model.FixturesKt.getAllTranscriptTypeNames;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TranscriptTypeInteropTest {

    private static Map<String, TranscriptType> staticPropertyMap;

    private static class TranscriptTypeJavaPropertyProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return getTranscriptTypeInstances().stream().map(Arguments::of);
        }
    }

    @BeforeAll
    public static void init(){
        staticPropertyMap = new HashMap<>();
        for (TranscriptType type : getTranscriptTypeInstances()) {
            staticPropertyMap.put(type.toString(), type);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeJavaPropertyProvider.class)
    void shouldOnlyExposeDefinedTranscriptTypeInstances(TranscriptType type) {
        assertTrue(getAllTranscriptTypeNames().contains(type.toString()));
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeNameProvider.class)
    void shouldExposeAStaticFieldForEveryDefinedTranscriptType(String typeName) {
        assertTrue(staticPropertyMap.containsKey(typeName));
    }

    private static List<TranscriptType> getTranscriptTypeInstances() {
        return Objects.requireNonNull(getStaticFieldsByType(TranscriptType.class))
            .stream()
            .map(field -> getFieldInstance(field, TranscriptType.PLAIN_TEXT))
            .filter(Objects::nonNull)
            .map(field -> (TranscriptType) field)
            .collect(toList());
    }


}
