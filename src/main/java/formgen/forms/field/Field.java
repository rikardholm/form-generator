package formgen.forms.field;

import java.util.List;

public interface Field {
    String getName();

    String getLabel();

    String getDescription();

    boolean getRequired();

    Class<?> getType();

    Object fromString(String string);

    List<?> fromStrings(String[] string);
}
