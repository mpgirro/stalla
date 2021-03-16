package dev.stalla

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID

// Borrowed from https://medium.com/javarevisited/using-temporary-files-for-unit-testing-5e6e281e40db
class TemporaryFileParameterResolver : ParameterResolver {

    @Throws(ParameterResolutionException::class)
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        if (parameterContext.isAnnotated(TemporaryFile::class.java)) {
            val parameter = parameterContext.parameter
            return when (parameter.type) {
                File::class.java, Path::class.java -> true
                else -> false
            }
        }
        return false
    }

    @Throws(ParameterResolutionException::class)
    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        val parameter = parameterContext.parameter
        val temporary = parameter.getAnnotation(TemporaryFile::class.java)
        when (parameter.type) {
            File::class.java -> {
                val file: File = try {
                    File.createTempFile("stalla-${UUID.randomUUID()}", "rss")
                } catch (ioe: IOException) {
                    throw ParameterResolutionException(ioe.message, ioe)
                }
                if (temporary.deleteOnExit) {
                    file.deleteOnExit()
                }
                return file
            }
            Path::class.java -> {
                val path: Path = try {
                    Files.createTempFile(null, null)
                } catch (ioe: IOException) {
                    throw ParameterResolutionException("failed to create a temporary file", ioe)
                }
                if (temporary.deleteOnExit) {
                    Runtime.getRuntime().addShutdownHook(
                        Thread {
                            try {
                                Files.deleteIfExists(path)
                            } catch (ioe: IOException) {
                                throw RuntimeException(ioe)
                            }
                        }
                    )
                }
                return path
            }
            else -> throw ParameterResolutionException("failed to resolve parameter for $parameterContext")
        }
    }
}
