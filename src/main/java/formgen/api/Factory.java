package formgen.api;

public interface Factory<REQUEST extends Request, CREATED> {
    Class<REQUEST> requestType();
    CREATED create(REQUEST request);
}
