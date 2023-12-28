class app {
    //Запуск приложения
    public static void main(String[] args) {
        DataBase db = new DataBase();
        AppController app = new AppController(db);
        app.Run();
    }
}