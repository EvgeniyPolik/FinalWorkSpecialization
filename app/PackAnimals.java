import java.util.ArrayList;
import java.util.Date;

public class PackAnimals extends Animals {
    public static final String ANIMAL_CLASS = "PackAnimal";

    PackAnimals(String name, Date birthDate) {
        super(name, birthDate);
    }

    PackAnimals(String name, Date birthDate, ArrayList<Commands> commands) {
        super(name, birthDate, commands);
    }
}
