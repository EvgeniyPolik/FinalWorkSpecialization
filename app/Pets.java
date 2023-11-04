import java.util.ArrayList;
import java.util.Date;

public class Pets extends Animals {
    public static final String ANIMAL_CLASS = "Pet";

    Pets(String name, Date birthDate) {
        super(name, birthDate);
    }

    Pets(String name, Date birthDate, ArrayList<Commands> commands) {
        super(name, birthDate, commands);
    }
}
