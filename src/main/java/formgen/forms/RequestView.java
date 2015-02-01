package formgen.forms;

import formgen.api.Request;
import formgen.forms.field.Field;

import java.util.ArrayList;
import java.util.List;

public class RequestView {
    private final String key;
    private final String title;
    private final String description;
    private final Class<? extends Request> type;
    private final List<Field> fields = new ArrayList<>();

    public RequestView(String key, String title, String description, Class<? extends Request> type) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends Request> getType() {
        return type;
    }

    public List<? extends Field> getFields() {
        return fields;
    }
}
