package formgen.tasks.reports.valuestatement;

import formgen.api.FieldDescription;
import formgen.api.RequestDescription;
import formgen.api.Request;
import formgen.tasks.Product;

import javax.mail.internet.InternetAddress;
import java.time.Year;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@RequestDescription(
        title = "Värdebesked",
        key = "value-statement",
        description = "Räknar ut värdebesked för alla konton.")
public class ValueStatementRequest implements Request {
    private final Year year;
    private final InternetAddress email;
    private final Set<Product> products;

    public ValueStatementRequest(@FieldDescription(name = "year", title = "År", description = "Vilket år som ska användas i uträkningen.") Year year,
                                 @FieldDescription(name = "products", title = "Produkter", description = "Produkter som ska inkluderas i denna körning", listOf = Product.class) List<Product> products,
                                 @FieldDescription(name = "email", title = "Epost", description = "Var ska resultatet mailas") InternetAddress email) {
        this.year = year;
        this.email = email;
        this.products = EnumSet.copyOf(products);
    }

    public Year getYear() {
        return year;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public InternetAddress getEmail() {
        return email;
    }
}
