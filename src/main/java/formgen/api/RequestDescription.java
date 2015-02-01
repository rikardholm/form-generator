package formgen.api;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface RequestDescription {
    String title();
    String key();
    String description();
}
