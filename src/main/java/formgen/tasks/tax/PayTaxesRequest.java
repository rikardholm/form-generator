package formgen.tasks.tax;

import formgen.api.FieldDescription;
import formgen.api.RequestDescription;
import formgen.api.Request;
import formgen.tasks.Personnummer;

import java.time.LocalDate;
import java.util.Optional;

@RequestDescription(
        title = "Betala skatt",
        key = "pay-taxes",
        description = "Räknar ut och debiterar all skatt.")
public class PayTaxesRequest implements Request {
    private final Optional<LocalDate> start;
    private final LocalDate stop;
    private final Optional<Personnummer> personnummer;

    public PayTaxesRequest(@FieldDescription(name = "start", title = "Startdatum", required = false) LocalDate start,
                           @FieldDescription(name = "stop", title = "Stopp-datum", description = "Dag som det ska sluta") LocalDate stop,
                           @FieldDescription(name = "personnummer", title = "Personnummer", required = false) Personnummer personnummer) {
        this.start = Optional.ofNullable(start);
        this.stop = stop;
        this.personnummer = Optional.ofNullable(personnummer);
    }

    public Optional<LocalDate> getStart() {
        return start;
    }

    public LocalDate getStop() {
        return stop;
    }

    public Optional<Personnummer> getPersonnummer() {
        return personnummer;
    }
}
