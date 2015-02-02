package formgen.api;

import java.lang.annotation.Retention;
import java.util.Optional;
import java.util.Set;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface FieldDescription {
    String name();

    String title() default "";

    String description() default "";

    boolean required() default true;

}
