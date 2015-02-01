package formgen.tasks;

public class Personnummer {
    private final String personnummer;

    private Personnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public static Personnummer parse(String value) {
        return new Personnummer(value);
    }

    public String getPersonnummer() {
        return personnummer;
    }
}
