import Droid.Droid;
import Droid.Droid.AttackDriod;
import Droid.Droid.DefenseDriod;
import utils.JsonUtils;
import Game.Battle;
import Game.Battle.TeamBattle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import BattleLog.BattleLog;

public class Main {
    public static final String RESET = "\033[0m";  // Скидання кольору
    public static final String RED = "\033[0;31m";     // Червоний
    public static final String GREEN = "\033[0;32m";   // Зелений
    public static final String YELLOW = "\033[0;33m";  // Жовтий
    public static final String BLUE = "\033[0;34m";    // Синій
    public static final String PURPLE = "\033[0;35m";  // Фіолетовий
    public static final String CYAN = "\033[0;36m";    // Блакитний
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Droid> droids =new ArrayList<>();
    public static void main(String[] args)
    {
        boolean running = true;

        while (running)
        {
            System.out.println("############################################");
            System.out.println(" ");
            System.out.println("Меню: ");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Запустити бій 1 на 1");
            System.out.println("4. Запустити бій команда на команду");
            System.out.println("5. Зберегти дроїдів у файл JSON");
            System.out.println("6. Завантажити дроїдів з файлу JSON");
            System.out.println("7. Вийти з програми");
            System.out.print("Оберіть опцію: ");

            int choise = scanner.nextInt();
            switch(choise) {
                case 1:createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startBattle1v1();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveDroids();
                    break;
                case 6:
                    loadDroids();
                    break;
                case 7 :
                    running = false;
            }
            }

        }

        private static void createDroid() {
            System.out.println("############################################");
            System.out.println(" ");
            System.out.println("Оберіть тип дроїда:");
            System.out.println("1. Дроїд-атакувальник");
            System.out.println("2. Дроїд-захисник");
            int choise = scanner.nextInt();
            System.out.println("Введіть ім'я дроїда: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            switch (choise){
                case 1: droids.add(new AttackDriod(name));
                break;
                case 2: droids.add(new DefenseDriod(name));
                    break;
                default: System.out.println(RED+"Невірний вибір дроїда"+RESET);
            }
        }


        private static void showDroids(){
        if (droids.isEmpty()){
            System.out.println("У списку немає дроїдів!");
        }
        else
        {
            System.out.println("############################################");
            System.out.println(" ");
            System.out.println(BLUE+"Список дроїдів: "+RESET);
            for (int i =0;i< droids.size();i++){
                System.out.println((i+1)+". "+droids.get(i));
            }
        }
        }



        private static void startBattle1v1(){
        if(droids.size() < 2){
            System.out.println(RED+"Недостатньо дроїдів для бою."+RESET);
            return;
        }
            System.out.println("############################################");
            System.out.println(" ");
            System.out.println("Оберіть першого дроїда:");
        int firstDroid = scanner.nextInt() - 1;
            System.out.println("Оберіть першого дроїда:");
            int secondDroid = scanner.nextInt() - 1;

            Droid droid1 = droids.get(firstDroid);
            Droid droid2 = droids.get(secondDroid);
            System.out.println(CYAN+"Бій між " + droid1.getName() + " та " + droid2.getName() + " починається!"+RESET);
            //Battle.batltle1v1(droid1,droid2);

            BattleLog log = Battle.batltle1v1(droid1, droid2);

            // Збереження логу в файл
            saveBattleLog(log);
        }


        private static void startTeamBattle(){
        if(droids.size() <4){
            System.out.println("Недостатньо дроїдів для командного бою.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        for(int i = 0;i<droids.size();i++)
        {
            if(i % 2 ==0){
                team1.add(droids.get(i));
            }
            else
            {
                team2.add(droids.get(i));
            }
        }

            System.out.println("Команда 1: " + team1);
            System.out.println("Команда 2: " + team2);
            //TeamBattle.teamBattle(team1,team2);
            BattleLog log = TeamBattle.teamBattle(team1, team2);

            // Збереження логу в файл
            saveBattleLog(log);
        }

    private static void saveDroids() {
        System.out.println("############################################");
        System.out.println(" ");
        System.out.print("Введіть ім'я файлу для збереження: ");
        String fileName = scanner.next();
        try {
            JsonUtils.saveDroidsToFile(droids, fileName);
            System.out.println("Дроїди успішно збережені.");
        } catch (IOException e) {
            System.out.println("Помилка під час збереження: " + e.getMessage());
        }
    }

    private static void loadDroids() {
        System.out.println("############################################");
        System.out.println(" ");
        System.out.print("Введіть ім'я файлу для завантаження: ");
        String fileName = scanner.next();
        try {
            droids = JsonUtils.loadDroidsFromFile(fileName);
            System.out.println(GREEN+"Дроїди успішно завантажені."+RESET);
        } catch (IOException e) {
            System.out.println("Помилка під час завантаження: " + e.getMessage());
        }
    }

    private static void saveBattleLog(BattleLog log) {
        System.out.println("############################################");
        System.out.println(" ");
        System.out.print("Введіть ім'я файлу для збереження логу бою: ");
        String fileName = scanner.next();
        try {
            JsonUtils.saveBattleLogToFile(log, fileName);
            System.out.println(GREEN+"Лог бою успішно збережено."+RESET);
        } catch (IOException e) {
            System.out.println("Помилка під час збереження логу: " + e.getMessage());
        }
    }

}


