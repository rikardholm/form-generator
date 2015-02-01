package formgen;

import formgen.api.Factory;
import formgen.api.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TaskRequestConfiguration {
    @Autowired
    private List<Factory<?, ?>> factories;

    @Bean(initMethod = "init")
    public RequestRegistry requestRegistry() {
        ArrayList<Class<? extends Request>> requestTypes = new ArrayList<>();
        for (Factory<?, ?> factory : factories) {
            requestTypes.add(factory.requestType());
        }

        return new RequestRegistry(requestTypes);
    }
}
