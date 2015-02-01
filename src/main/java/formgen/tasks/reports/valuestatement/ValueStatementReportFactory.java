package formgen.tasks.reports.valuestatement;

import formgen.api.ReportFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
public class ValueStatementReportFactory implements ReportFactory<ValueStatementRequest> {
    @Override
    public Class<ValueStatementRequest> requestType() {
        return ValueStatementRequest.class;
    }

    @Override
    public Callable<byte[]> create(ValueStatementRequest taskRequest) {
        return null;
    }
}
