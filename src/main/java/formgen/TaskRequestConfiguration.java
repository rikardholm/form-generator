package formgen;

import formgen.api.Request;
import formgen.tasks.reports.valuestatement.ValueStatementRequest;
import formgen.tasks.tax.FirstDepositRequest;
import formgen.tasks.tax.PayTaxesRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TaskRequestConfiguration {
    public static final List<Class<? extends Request>> REQUEST_TYPES = Arrays.asList(
            ValueStatementRequest.class,
            PayTaxesRequest.class,
            FirstDepositRequest.class);

    @Bean(initMethod = "init")
    public RequestRegistry requestRegistry() {

        return new RequestRegistry(REQUEST_TYPES);
    }
}
