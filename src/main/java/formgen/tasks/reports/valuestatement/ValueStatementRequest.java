package formgen.tasks.reports.valuestatement;

import formgen.api.FieldDescription;
import formgen.api.RequestDescription;
import formgen.api.Request;
import formgen.tasks.Product;

import javax.mail.internet.InternetAddress;
import java.time.Year;

@RequestDescription(
        title = "Värdebesked",
        key = "value-statement",
        description = "Räknar ut värdebesked för alla konton.")
public class ValueStatementRequest implements Request {
    private final Year year;
    private final InternetAddress email;
    private final Product product;

    public ValueStatementRequest(@FieldDescription(name = "year", title = "År", description = "Vilket år som ska användas i uträkningen.") Year year,
                                 @FieldDescription(name = "product", title = "Produkter", description = "Produkter som ska inkluderas i denna körning") Product product,
                                 @FieldDescription(name = "email", title = "Epost", description = "Var ska resultatet mailas") InternetAddress email) {
        this.year = year;
        this.email = email;
        this.product = product;
    }

    public Year getYear() {
        return year;
    }

    public Product getProduct() {
        return product;
    }

    public InternetAddress getEmail() {
        return email;
    }
}
