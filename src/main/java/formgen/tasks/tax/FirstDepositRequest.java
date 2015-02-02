package formgen.tasks.tax;

import formgen.api.FieldDescription;
import formgen.api.Request;
import formgen.api.RequestDescription;

import javax.mail.internet.InternetAddress;
import java.time.YearMonth;

@RequestDescription(
        title = "Förstagångsinsättningar",
        key = "first-deposits",
        description = "Visa första insättningar för denna period.")
public class FirstDepositRequest implements Request {
    private YearMonth month;
    private InternetAddress email;

    public FirstDepositRequest(
            @FieldDescription(name = "month") YearMonth month,
            @FieldDescription(name = "email") InternetAddress email) {
        this.month = month;
        this.email = email;
    }

    public YearMonth getMonth() {
        return month;
    }

    public InternetAddress getEmail() {
        return email;
    }
}
