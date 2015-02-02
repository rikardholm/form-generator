package formgen.forms.field;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class GeneralField implements Field {
    private final String name;
    private final String label;
    private final String description;
    private final boolean required;
    private final Class<?> type;
    private final Function<String, ?> convert;
    private final List<Object> options;

    public GeneralField(Class<?> type, String name, String label, String description, boolean required, Function<String, ?> convert, List<Object> options) {
        this.type = requireNonNull(type);
        this.name = requireNonNull(name);
        this.label = requireNonNull(label);
        this.description = requireNonNull(description);
        this.required = requireNonNull(required);
        this.convert = requireNonNull(convert);
        this.options = requireNonNull(options);
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

    public List<Object> getOptions() {
        return options;
    }
}
