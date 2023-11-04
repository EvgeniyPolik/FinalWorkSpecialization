import java.util.ArrayList;
import java.util.Date;


//Класс для хомячков
public class Hamsters extends Pets {
    public static final String ANIMAL_TYPE = "Hamsters";

    Hamsters(String name, Date date) {
        super(name, date);
    }

    Hamsters(String name, Date date, ArrayList<Commands> commands) {
        super(name, date, commands);
    }

    @Override
    public String getInfo() {
        return String.format("Name: %s, Birth date: %2$td.%2$tm.%2$tY, Class: %3$s, Type: %4$s", this.getName(), this.getBirthDate(), ANIMAL_CLASS, ANIMAL_TYPE);
    }

    @Override
    public String makeLineInfo() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(this.getName());
        strBuild.append(";");
        String date = String.format("%1$td.%1$tm.%1$tY", this.getBirthDate());
        strBuild.append(date);
        strBuild.append(";");
        strBuild.append(Pets.ANIMAL_CLASS);
        strBuild.append(";");
        strBuild.append(ANIMAL_TYPE);
        strBuild.append(";");
        for (Commands command : this.getCommands()) {
            strBuild.append(command.getTitle());
            strBuild.append(":");
            strBuild.append(command.getAction());
            strBuild.append("@");
        }
        return strBuild.toString();
    }
}