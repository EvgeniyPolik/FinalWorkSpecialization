import java.util.Date;
import java.util.ArrayList;

public class Animals {
    private String name;
    private ArrayList<Commands> learnedCommands;
    private Date birthDate;

    Animals(String name, Date birthDate) {
        this.name = name;
        this.learnedCommands = new ArrayList<Commands>();
        this.birthDate = birthDate;
    }

    Animals(String name, Date birthDate, ArrayList<Commands> commands) {
        this.name = name;
        this.learnedCommands = commands;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getListCommands() {
        int n = 0;
        if (learnedCommands.size() > 0)
            for (Commands command : learnedCommands) {
                System.out.printf("%d. %s\n", ++n, command.getTitle());
            }
        else
            System.out.println("Is empty...");
        return n;
    }

    public ArrayList<Commands> getCommands() {
        return learnedCommands;
    }

    public void executeCommand(int num) {
        learnedCommands.get(num - 1).executeCommand(this);;
    }

    public String getInfo() {
        return String.format("%s %s", name, birthDate.toString());
    }

    public void learnNewCommand(Commands command) {
        learnedCommands.add(command);
        System.out.printf("%s learned new command: %s\n", name, command.getTitle());
    }

    public String makeLineInfo() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(name);
        String date = String.format("%1$td.%1$tm.%1$tY", birthDate);
        strBuild.append(date);
        return strBuild.toString();
    }
} 
