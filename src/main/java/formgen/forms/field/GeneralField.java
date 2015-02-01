package formgen.forms.field;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneralField implements Field {
    private final String name;
    private final String label;
    private final String description;
    private final boolean required;
    private final Class<?> type;
    private final Function<String, ?> convert;
    private final Set<Object> options;

    public GeneralField(Class<?> type, String name, String label, String description, boolean required, Function<String, ?> convert, Set<Object> options) {
        this.type = type;
        this.name = name;
        this.label = label;
        this.description = description;
        this.required = required;
        this.convert = convert;
        this.options = options;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean getRequired() {
        return required;
    }

    @Override
    public List<?> fromStrings(String[] strings) {
        return Arrays.asList(strings)
                .stream()
                .map(convert)
                .collect(Collectors.toList());
    }

    @Override
    public Object fromString(String string) {
        return convert.apply(string);
    }

    public Set<Object> getOptions() {
        return options;
    }
}
