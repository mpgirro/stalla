package dev.stalla.model.podcastindex;

import dev.stalla.model.TranscriptTypeNameProvider;
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

import static dev.stalla.model.FixturesKt.getAllTranscriptTypeNames;
import static dev.stalla.model.FixturesKt.getStaticTranscriptTypeProperties;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TranscriptTypeInteropTest {

    private static final List<String> allTranscriptTypeNames = getAllTranscriptTypeNames();
    private static Map<String, TranscriptType> staticPropertyMap;

    private static class TranscriptTypeStaticJavaPropertyProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Arrays.stream(getStaticTranscriptTypeProperties()).map(Arguments::of);
        }
    }

    @BeforeAll
    public static void init(){
        staticPropertyMap = new HashMap<>();
        for (TranscriptType type : getStaticTranscriptTypeProperties()) {
            staticPropertyMap.put(type.toString(), type);
        }
    }

    @ParameterizedTest
    @DisplayName("should only expose defined values through static members")
    @ArgumentsSource(TranscriptTypeStaticJavaPropertyProvider.class)
    void shouldOnlyExposeDefinedTranscriptTypeInstances(TranscriptType type) {
        assertTrue(allTranscriptTypeNames.contains(type.toString()));
    }

    @ParameterizedTest
    @DisplayName("should expose a static member for every defined Transcript Type")
    @ArgumentsSource(TranscriptTypeNameProvider.class)
    void shouldExposeAStaticFieldForEveryDefinedTranscriptType(String typeName) {
        assertTrue(staticPropertyMap.containsKey(typeName));
    }

}
