import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Класс имитатор базы данных (сохраняет реестр в файл)
public class DataBase {
    private static final String FILE_PATH = "reestr.txt";

    //Создает животное из записи в файле
    private Animals makeNewAnimals(String name, String classAnimal, String typeAnimal, Date birthDateInDate, ArrayList<Commands> commandList) {
        Animals animal = null;                            
        if (classAnimal.equals("Pet")) {
            switch (typeAnimal) {
                case "Dog":
                    animal = new Dogs(name, birthDateInDate, commandList);
                    break;
                case "Cat":
                    animal = new Cats(name, birthDateInDate, commandList);
                    break;
                case "Hamter":
                    animal = new Hamsters(name, birthDateInDate, commandList);
                    break;                                
                default:
                    System.out.println("Wrong type");
                    break;
            }                                    
        }
        else if (classAnimal.equals("PackAnimal")) {
            switch (typeAnimal) {
                case "Horse":
                    animal = new Horses(name, birthDateInDate, commandList);
                    break;
                case "Donkey":
                    animal = new Donkeys(name, birthDateInDate, commandList);
                    break;
                case "Camel":
                    animal = new Camels(name, birthDateInDate, commandList);
                    break;                                
                default:
                    System.out.println("Wrong type");
                    break;                                
            }
        }
        return animal;
    }
    // Загрузка реестра из файла
    public ArrayList<Animals> loadReestr() {
        ArrayList<Animals> listAnimals = new ArrayList<Animals>();;
        File file = new File(FILE_PATH);
            if (file.exists())
                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                    String line;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
                    while ((line = reader.readLine()) != null) {
                        Date birthDateInDate;
                        ArrayList<Commands> commandList = new ArrayList<Commands>();
                        String[] data = line.split(";");
                        if (data.length >= 3) {
                            String name = data[0];
                            String birthDate = data[1];
                            try {
                                birthDateInDate = (Date) sdf.parse(birthDate);
                            }
                            catch (Exception e) {
                                System.out.println("Неверный формат даты");
                                continue;
                            }                           
                            String classAnimal = data[2];
                            String typeAnimal = data[3];
                            if (data.length == 5) {
                                String commandItem = data[4];
                                String[] commands = commandItem.split("@");
                                for (String command : commands) {
                                    String[] tmp = command.split(":");
                                    if (tmp.length == 2)
                                        commandList.add(new Commands(tmp[0], tmp[1]));
                                }
                            }
                            Animals animal;
                            animal = makeNewAnimals(name, classAnimal, typeAnimal, birthDateInDate, commandList);
                            if (animal != null)     
                                listAnimals.add(animal);
                            else
                                continue;                   
                        }
                        else {
                            System.out.println("Wrong line");
                            continue;
                        }                    
                    }
                }
                catch (Exception e) {
                    System.out.println("Error load from file");
                    return listAnimals;
                }
        return listAnimals;
    }
    //запись реестра в файл
    public void saveReestr(ArrayList<Animals> reestr) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animals animal : reestr) {
                writer.write(animal.makeLineInfo());
                writer.newLine();
            }
            System.out.println("Base update");
        } catch (Exception e) {
            System.out.println("Wrong save to file");
        }
    }
}
