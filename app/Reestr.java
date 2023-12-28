import java.util.ArrayList;
import java.util.Comparator;

//Хранилище он же класс модель(MVC)
public class Reestr {
    private CounterAnimals count;
    private ArrayList<Animals> reestr;

    //Конструктор
    Reestr() {
        count = new CounterAnimals();
        reestr = new ArrayList<Animals>();
    }

    //Конструктор при создании из файла
    Reestr(ArrayList<Animals> reestr) {
        count = new CounterAnimals(reestr.size());
        this.reestr = reestr;
    }

    //Сортировка по дате
    private void sortReestr() {
        Comparator<Animals> comparator = (a1, a2) -> a1.getBirthDate().compareTo(a2.getBirthDate());
        reestr.sort(comparator);
    }

    //Получение количества животных
    public String getCurrentCount() {
        return count.toString();
    }

    //Добавить новое животное
    public void addAnimal(Animals animal) {
        reestr.add(animal);
        count.addAnimals();
        sortReestr();
        System.out.printf("%s added\n", animal.getName());
    }

    //Получить животное из реестра
    public Animals getAnimal(int number) {
        Animals animal = null;
        if (number > 0 || number < reestr.size())
            animal = reestr.get(number - 1);
        return animal;
    }

    //Получить размер хранилища
    public int getSize() {
        return reestr.size();
    }

    //Вывести на консоль реестр
    public void printReestr() {
        if (reestr.size() == 0) {
            System.out.println("Reestr is empty");
            return;
        }
        int n = 0;
        for (Animals animal : reestr) {
            System.out.printf("%d. %s\n", ++n, animal.getInfo());
        }
        System.out.printf("Total: %d\n", count.getCurrentValue());
    }

    //Передать реестр
    public ArrayList<Animals> getReestr() {
        return reestr;
    }

}
