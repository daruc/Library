import java.net.URL;

import static java.awt.EventQueue.invokeLater;

/**
 * Created by darek on 28.11.2015.
 */
public class Main {
    public static void main(String args[]) {

        DatabaseModule databaseModule = new DatabaseModule("database.properties");
        invokeLater(() -> new LoginFrame("System biblioteczny", databaseModule));
    }
}
