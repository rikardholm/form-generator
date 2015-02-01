package formgen.forms;

import formgen.RequestRegistry;
import formgen.api.Request;
import formgen.forms.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public void start(@PathVariable String key, HttpServletRequest httpServletRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        RequestView requestDescription = requestRegistry.getByKey(key);

        Class<?>[] types = new Class<?>[requestDescription.getFields().size()];
        int i = 0;
        for (Field field : requestDescription.getFields()) {
            types[i] = field.getType();
            i++;
        }

        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();

        Constructor<? extends Request> constructor = requestDescription.getType().getConstructor(types);

        Object[] args = new Object[requestDescription.getFields().size()];
        i = 0;
        for (Field field : requestDescription.getFields()) {
            String[] values = parameterMap.get(field.getName());

            if (values == null) {
                if (field.getRequired()) {
                    throw new NullPointerException("Field " + field.getName() + " is required.");
                }
                args[i] = null;
            } else {
                args[i] = field.fromString(values[0]);
            }
            i++;
        }

        Request request = constructor.newInstance(args);

        System.out.println("here");
    }
}
