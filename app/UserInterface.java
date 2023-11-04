import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;


//Класс view для общения с пользовавателем
public class UserInterface {
    private static final String ERROR_INPUT = "Wrong input, try again";
    private static final String DATE_FORMAT = "dd.mm.yyyy";
    private static final String DIGIT_TEMPLATE = "\\d";
    private static final String DATE_TEMPLATE = "([012][0-9]|3[01])[.]([01][012])[.](19[0-9][0-9]|20[0-9][0-9])";

    //Основное меню
    public int showMainMenu(Scanner console) {
        int result = 0;
        while (true) {
            System.out.println("Choose menu point: \n1. Add new animals;\n2. View list animals\n3. Choose animals\n0. Exit");
            String userInput = console.nextLine();
            if (userInput.equals("0")) {
                result = 0;
                break;
            }
            if (userInput.matches(DIGIT_TEMPLATE)) {
                result = Integer.parseInt(userInput);
                if (result >= 0 && result <=3) {
                    return result;
                }
            }
            System.out.println(ERROR_INPUT);
        }
        return result;
    }

    //Создание животного для внесения в реестр
    public Animals makeNewAnimals(String name, Date birthDate, String classAnimal, String animalType, Scanner console) {
        Animals result = null;
        if (classAnimal.equals("1"))
            switch  (animalType) {
                case "1":
                    result = new Dogs(name, birthDate);
                    break;
                case "2":
                    result = new Cats(name, birthDate);
                    break;
                case "3":
                    result = new Cats(name, birthDate);
                    break;
                default:
                    break;
            }
        else
            switch  (animalType) {
                case "1":
                    result = new Horses(name, birthDate);
                    break;
                case "2":
                    result = new Donkeys(name, birthDate);
                    break;
                case "3":
                    result = new Camels(name, birthDate);
                    break;
                default:
                    break;
            }           
        return result;
    }

    //Добавление животного
    public Animals newAnimals(Scanner console) {        
        SimpleDateFormat sdf;
        Date birthDateIndate;
        String animalClass;
        String birthDate;
        String animalType;
        while(true) {
            System.out.println("Input class animal:\n1. Pets\n2. Pack Animals;");
            animalClass = console.nextLine();
            if (animalClass.equals("1") || animalClass.equals("2"))
                break;
            System.out.println(ERROR_INPUT);
        }
        while (true) {
            if (animalClass.equals("1"))
                System.out.println("Input type animal:\n1. Dogs;\n2. Cats;\n3. Hamsters.\n");
            else
                System.out.println("Input type animal:\n1. Horses;\n2. Donkeys;\n3. Camels.\n");
         animalType = console.nextLine();
            if  (animalType.matches("[1-3]"))
                break;
            System.out.println(ERROR_INPUT);
        }

        System.out.println("Input name:");
        String name = console.nextLine();
        while (true) {
            System.out.printf("Input birth date in format %s:\n", DATE_FORMAT);
            birthDate = console.nextLine();
            if (birthDate.matches(DATE_TEMPLATE)) {
                sdf = new SimpleDateFormat(DATE_FORMAT);
                break;
            }
            System.out.println(ERROR_INPUT);
        }
        try {
            birthDateIndate = (Date) sdf.parse(birthDate);
        }
        catch(Exception e) {
            birthDateIndate = new Date();
        }
        return makeNewAnimals(name, birthDateIndate, animalClass, animalType, console);
    }

    //Выбор животного из реестра
    public int chooseAnimal(int size, Scanner console) {
        int result = 0;
        while (true) {
            System.out.println("Enter the animal number or 0 to exit.");
            String userInput = console.nextLine();
            if (userInput.matches(DIGIT_TEMPLATE)) {
                int tmp = Integer.parseInt(userInput);
                if (tmp <= size && tmp >= 0) {
                    result = tmp;
                    break;
                }
            }
            System.out.println(ERROR_INPUT); 
        }
        return result;
    }

    //Обучение животного
    public boolean learNewComand(Animals animal, Scanner console) {
        String title;
        String action;
        while (true) {
            System.out.println("Input tutle:");
            title = console.nextLine();
            System.out.println("Input action:");
            action = console.nextLine();
            if (title.length() > 0 && action.length() > 0)
                break;
            System.out.println(ERROR_INPUT);
        }
        animal.learnNewCommand(new Commands(title, action));
        return true;
    }

    //Просмотр доступных команд
    public void showAvalibleCommands(Animals animal, Scanner console) {
        int userChoose = 0;
        boolean run = true;
        while (run) {
            System.out.println("Choose number of command or 0 to exit:");
            int countCommands = animal.getListCommands();
            String tmp = console.nextLine();
            if (tmp.matches(DIGIT_TEMPLATE)) { 
                userChoose = Integer.parseInt(tmp);
                if (userChoose == 0)
                    break;
                else if (userChoose > 0 && userChoose <= countCommands) 
                    animal.executeCommand(userChoose);
                else 
                    System.out.println(ERROR_INPUT);
            }
            else
                System.out.println(ERROR_INPUT);
            
        }
        System.out.println("Exiting...");
    }

    //Меню работы с выбраннм животным
    public boolean showAnimalMenu(Animals animal, Scanner console) {
        int userChoose = 0;
        boolean run = true;
        boolean result = false;
        while (run) {
            System.out.println("Choose menu point or 0 to exit:");
            System.out.println("1. Show animal info;\n2. Show avalible command\n3. New command");
            String userInput = console.nextLine();
            if (userInput.matches(DIGIT_TEMPLATE)) {
                userChoose = Integer.parseInt(userInput);
                switch (userChoose) {
                    case 0:
                        run = false;
                        break;
                    case 1:
                        System.out.println(animal.getInfo());
                        break;
                    case 2:
                        showAvalibleCommands(animal, console);
                        break;
                    case 3:
                        result = learNewComand(animal, console);
                        break;
                    default:
                        System.out.println(ERROR_INPUT);
                        break;
                }
            }
        }
        return result;
    }
}
