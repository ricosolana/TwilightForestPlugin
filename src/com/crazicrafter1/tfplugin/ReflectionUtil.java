package com.crazicrafter1.tfplugin;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;

public final class ReflectionUtil {

    // Not instantiable
    private ReflectionUtil() {
    }


    /**
     * Returns the value of a field that is part of a certain class. In other words, enables this:
     *
     * <pre> {@code
     * public class Foo {
     *     private String value;
     *
     *     public Foo(String value) {
     *        this.value = value
     *     }
     * }
     *
     * public class Main {
     *     public static void main(String[] args) {
     *         Foo foo = new Foo("bar");
     *         System.out.println(ReflectionUtil.getField(Foo.class, "value", foo));
     *     }
     * }}
     * </pre>
     *
     * @param clazz     The class of the object
     * @param fieldName The name of the field
     * @param object    The object that this field belongs to, null if this is a static value
     * @param <T>       The type of the class, resp. value
     * @return The value of the field, or null if the field wasn't found, or a security exception occurred.
     * @see #getField(Object, String) simplified method, if the object belongs to the class
     */
    @SuppressWarnings("unchecked")
    public static <T> T getField(@NotNull final Class<T> clazz, final @NotNull String fieldName, @Nullable final T object) {
        try {
            // get the field that this class declares with name "fieldName" - throws NoSuchFieldException if not found
            final Field field = clazz.getDeclaredField(fieldName);
            // set it accessible, ignoring private or protected access level
            field.setAccessible(true);
            // return the field-value for a given object - throws IllegalAccessException if this is not possible
            // we cast this to T to be able to work with generics
            // this cast should never fail since we are accessing the object of type T
            return (T) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convenience-method to access a member of a certain object
     *
     * @param object    The object that this member belongs to
     * @param fieldName The name of the field
     * @param <T>       The type of the object
     * @return The value of the field, or null if the field wasn't found, or a security exception occurred.
     */

    @SuppressWarnings("unchecked")
    public static <T> T getField(@NotNull final T object, @NotNull final String fieldName) {
        return getField((Class<T>) object.getClass(), fieldName, object);
    }
}