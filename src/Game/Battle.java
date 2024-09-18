package Game;

import Droid.Droid;
import java.util.List;
import java.util.Random;
import BattleLog.BattleLog;


public class Battle {
    public static final String RESET = "\033[0m";  // Скидання кольору
    public static final String RED = "\033[0;31m";     // Червоний
    public static final String GREEN = "\033[0;32m";   // Зелений
    public static final String YELLOW = "\033[0;33m";  // Жовтий
    public static final String BLUE = "\033[0;34m";    // Синій
    public static final String PURPLE = "\033[0;35m";  // Фіолетовий
    public static final String CYAN = "\033[0;36m";    // Блакитний
    public static BattleLog batltle1v1(Droid droid1,Droid droid2)
    {
        Random rand = new Random();
        BattleLog log = new BattleLog();

        while (droid1.isAlive() && droid2.isAlive())
        {
            if (rand.nextBoolean())
            {
                droid2.takeDamage(droid1.getDamage());
                log.addEntry(droid1.getName() + " атакує " + droid2.getName());
               System.out.println(GREEN +droid1.getName() +" attack "+ droid2.getName()+RESET);
            }
            else
            {
                droid1.takeDamage(droid2.getDamage());
                log.addEntry(droid2.getName() + " атакує " + droid1.getName());
                System.out.println(RED+droid2.getName() +" attack "+ droid1.getName()+RESET);
            }
            System.out.println(droid1);
            System.out.println(droid2);
        }

        if(droid1.isAlive())
        {
            log.addEntry(droid1.getName() + " переміг!");
            System.out.println(GREEN+droid1.getName() + " переміг!"+RESET);
        }
        else
        {
            log.addEntry(droid1.getName() + " переміг!");
            System.out.println(GREEN+droid2.getName() + " переміг!"+RESET);
        }
        droid1.resetHealth();
        droid2.resetHealth();

        return log;
    }

public class  TeamBattle
{
    public static BattleLog teamBattle(List<Droid> team1,List<Droid> team2) {
        BattleLog log = new BattleLog();

        while (team1.stream().anyMatch(Droid::isAlive) && team2.stream().anyMatch(Droid::isAlive)) {
            Droid droid1 = team1.stream().filter(Droid::isAlive).findFirst().orElse(null);
            Droid droid2 = team2.stream().filter(Droid::isAlive).findFirst().orElse(null);

            if (droid1 != null && droid2 != null) {
                log.addEntry("Команда 1: " + droid1.getName() + " проти " + "Команда 2: " + droid2.getName());
                Battle.batltle1v1(droid1, droid2);
            }
        }
        team1.forEach(Droid::resetHealth);
        team2.forEach(Droid::resetHealth);
        return log;
    }

}

}
