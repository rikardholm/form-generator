package formgen.tasks.tax;

import formgen.api.TaskFactory;
import org.springframework.stereotype.Service;

@Service
public class PayTaxesTaskFactory implements TaskFactory<PayTaxesRequest> {
    @Override
    public Class<PayTaxesRequest> requestType() {
        return PayTaxesRequest.class;
    }

    @Override
    public Runnable create(PayTaxesRequest request) {
        return null;
    }
}
