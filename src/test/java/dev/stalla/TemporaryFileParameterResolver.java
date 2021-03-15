package dev.stalla;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

// Borrowed from https://medium.com/javarevisited/using-temporary-files-for-unit-testing-5e6e281e40db
public class TemporaryFileParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.isAnnotated(TemporaryFile.class)) {
            final Parameter parameter = parameterContext.getParameter();
            final Class<?> parameterType = parameter.getType();
            return File.class == parameterType || Path.class == parameterType;
        }
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        final Parameter parameter = parameterContext.getParameter();
        final TemporaryFile temporary = parameter.getAnnotation(TemporaryFile.class);
        final Class<?> parameterType = parameter.getType();

        if (File.class == parameterType) {
            File file;
            try {
                final String prefix = "stalla-" + UUID.randomUUID();
                file = File.createTempFile(prefix, "rss");
            } catch (final IOException ioe) {
                throw new ParameterResolutionException(ioe.getMessage(), ioe);
            }
            if (temporary.deleteOnExit()) {
                file.deleteOnExit();
            }
            return file;
        }

        if (Path.class == parameterType) {
            Path path;
            try {
                path = Files.createTempFile(null, null);
            } catch (final IOException ioe) {
                throw new ParameterResolutionException(
                    "failed to create a temporary file", ioe);
            }
            if (temporary.deleteOnExit()) {
                Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (final IOException ioe) {
                            throw new RuntimeException(ioe);
                        }
                    }));
            }
            return path;
        }

        throw new ParameterResolutionException(
            "failed to resolve parameter for " + parameterContext);
    }
}
