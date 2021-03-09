package dev.stalla;

import dev.stalla.model.itunes.ItunesCategory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReflectionUtil {

    public static List<Field> getStaticFieldsByType(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> Modifier.isStatic(f.getModifiers()))
            .filter(f -> clazz.isAssignableFrom(f.getType()))
            .collect(toList());
    }

    public static Object getFieldInstance(Field field, Object protoype) {
        try {
            return field.get(protoype);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
