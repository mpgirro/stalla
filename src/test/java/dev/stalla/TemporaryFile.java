package dev.stalla;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface TemporaryFile {
    boolean deleteOnExit() default true;
}
