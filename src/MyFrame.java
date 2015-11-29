import javax.swing.*;

/**
 * Created by darek on 29.11.2015.
 */
public class MyFrame extends JFrame {
    private DatabaseModule databaseModule;

    public MyFrame(String title, DatabaseModule databaseModule) {
        super(title);
        this.databaseModule = databaseModule;
    }

    public DatabaseModule getDatabaseModule() {
        return databaseModule;
    }
}
