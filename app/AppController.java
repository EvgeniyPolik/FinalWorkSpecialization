import java.util.Scanner;

//Класс контролер
public class AppController {
    private Reestr reestr;
    DataBase db;
    Scanner console;

    AppController() {
        reestr = new Reestr();
    }

    AppController(DataBase db) {
        this.db = db;
        reestr = new Reestr(db.loadReestr());
    }
    //работа приложения
    public void Run() {
        
        UserInterface userInterface = new UserInterface();
        //reestr = new Reestr();
        console = new Scanner(System.in);
        int userAnswer = userInterface.showMainMenu(console);
        //Запуск интерфейса общения с пользователем
        while (userAnswer != 0) {
            //Обработка ответов пользователя
            switch (userAnswer) {
                case 1:
                    reestr.addAnimal(userInterface.newAnimals(console));
                    db.saveReestr(reestr.getReestr());
                    break;
                case 2:
                    reestr.printReestr();
                    break;
                case 3:
                    reestr.printReestr();
                    Animals animal = null;
                    int chooseNumber = userInterface.chooseAnimal(reestr.getSize(), console);
                    if (chooseNumber != 0) {
                        animal = reestr.getAnimal(chooseNumber);
                        if (userInterface.showAnimalMenu(animal, console))
                            db.saveReestr(reestr.getReestr());;
                    }
                    else 
                        System.out.println("Exiting...");
                    break;
                default:
                    break;
            }
                
            userAnswer = userInterface.showMainMenu(console);
        }
        console.close();
        System.out.println("Good Bye!");
    }
    
}
