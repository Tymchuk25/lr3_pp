package BattleLog;

import java.util.ArrayList;
import java.util.List;

public class BattleLog {
    private List<String> logEntries;

    public BattleLog() {
        this.logEntries = new ArrayList<>();
    }

    public void addEntry(String entry) {
        logEntries.add(entry);
    }

    public List<String> getLogEntries() {
        return logEntries;
    }

    public String toString() {
        StringBuilder log = new StringBuilder();
        for (String entry : logEntries) {
            log.append(entry).append("\n");
        }
        return log.toString();
    }
}
