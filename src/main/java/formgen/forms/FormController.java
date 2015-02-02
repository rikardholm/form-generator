package formgen.forms;

import formgen.RequestRegistry;
import formgen.api.Request;
import formgen.forms.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
public class FormController {

    @Autowired
    private RequestRegistry requestRegistry;

    @RequestMapping("/descriptions")
    public List<RequestView> hello() {
        return requestRegistry.requestDescriptions;
    }

    @RequestMapping("/start/{key}")
    @ResponseStatus(HttpStatus.CREATED)
    public void start(@PathVariable String key, @RequestBody Map<String,Object> requestBody) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        RequestView requestDescription = requestRegistry.getByKey(key);

        Class<?>[] types = new Class<?>[requestDescription.getFields().size()];
        int i = 0;
        for (Field field : requestDescription.getFields()) {
            types[i] = field.getType();
            i++;
        }

        Constructor<? extends Request> constructor = requestDescription.getType().getConstructor(types);

        Object[] args = new Object[requestDescription.getFields().size()];
        i = 0;
        for (Field field : requestDescription.getFields()) {
            Object value = requestBody.get(field.getName());

            if (value == null) {
                if (field.getRequired()) {
                    throw new NullPointerException("Field " + field.getName() + " is required.");
                }
                args[i] = null;
            } else {
                args[i] = field.fromString(value.toString());
            }
            i++;
        }

        Request request = constructor.newInstance(args);

        System.out.println("here");
    }
}
