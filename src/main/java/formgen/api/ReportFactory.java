package formgen.api;

import java.util.concurrent.Callable;

public interface ReportFactory<REQUEST extends Request> extends Factory<REQUEST, Callable<byte[]>> {
}
