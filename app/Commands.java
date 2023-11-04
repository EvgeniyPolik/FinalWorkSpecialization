public class Commands {
    private String title;
    private String action;

    Commands(String title, String action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public String getAction() {
        return action;
    }

    public void executeCommand(Animals animal) {
        System.out.printf("%s %s\n", animal.getName(), action);
    }

} 
