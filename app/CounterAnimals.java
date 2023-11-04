public class CounterAnimals {
    private int countAnimals;

    CounterAnimals() {
        countAnimals = 0;
    }

    CounterAnimals(int count) {
        countAnimals = count;
    }

    public int getCurrentValue() {
        return countAnimals;
    }

    public void addAnimals() {
        ++countAnimals;
    }

    public void delAnimals() {
        --countAnimals;
    }

    @Override
    public String toString() {
        return String.format("Current number of Animals: %d\n", countAnimals);
    }
}
