package formgen;

import formgen.api.FieldDescription;
import formgen.api.Request;
import formgen.api.RequestDescription;
import formgen.forms.RequestView;
import formgen.forms.field.Field;
import formgen.forms.field.GeneralField;
import formgen.tasks.Personnummer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;

public class RequestRegistry {
    private static final Map<Class<?>, Function<String, ?>> conversions = new HashMap<Class<?>, Function<String, ?>>() {{
        put(LocalDate.class, LocalDate::parse);
        put(YearMonth.class, YearMonth::parse);
        put(Year.class, Year::parse);
        put(Personnummer.class, Personnummer::parse);
        put(InternetAddress.class, new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                try {
                    return new InternetAddress(s);
                } catch (AddressException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }};
    private final List<Class<? extends Request>> requestTypes;
    public List<RequestView> requestDescriptions;

    public RequestRegistry(List<Class<? extends Request>> requestTypes) {
        this.requestTypes = requestTypes;
    }

    public void init() {
        requestDescriptions = createRequestDescriptions(requestTypes);
    }

    public RequestView getByKey(String key) {
        Objects.requireNonNull(key);
        return requestDescriptions.stream()
                .filter(rd -> key.equals(rd.getKey()))
                .findFirst().get();
    }

    private List<RequestView> createRequestDescriptions(List<Class<? extends Request>> requestTypes) {
        List<RequestView> requestDescriptions = new ArrayList<>();

        for (Class<? extends Request> requestType : requestTypes) {
            RequestView requestDescription = create(requestType);

            requestDescriptions.add(requestDescription);
        }

        return requestDescriptions;
    }

    private RequestView create(Class<? extends Request> requestType) {
        RequestDescription annotation = requestType.getAnnotation(RequestDescription.class);
        RequestView requestDescription = new RequestView(annotation.key(), annotation.title(), annotation.description(), requestType);

        for (Constructor<?> constructor : requestType.getConstructors()) {
            for (Parameter parameter : constructor.getParameters()) {
                String name = parameter.getName();

                String title = "";
                String description = "";
                boolean required = true;

                FieldDescription fieldDescription = parameter.getAnnotation(FieldDescription.class);
                if (fieldDescription != null) {
                    name = fieldDescription.name();
                    title = fieldDescription.title();
                    description = fieldDescription.description();
                    required = fieldDescription.required();
                }

                if ("".equals(title)) {
                    title = name;
                }

                List<Object> options = new ArrayList<>();

                Class<?> parameterType = parameter.getType();
                Function<String, ?> convert = conversions.get(parameterType);

                if (Enum.class.isAssignableFrom(parameterType)) {
                    Object[] enumConstants = parameterType.getEnumConstants();
                    options = asList(enumConstants);
                    convert = string -> Enum.valueOf((Class<Enum>) parameterType, string);
                }

                Field field = new GeneralField(parameterType, name, title, description, required, convert, options);

                requestDescription.addField(field);
            }
        }
        return requestDescription;
    }
}
