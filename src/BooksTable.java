import javax.swing.*;

/**
 * Created by darek on 29.11.2015.
 */
public class BooksTable extends JTable {
    private DatabaseModule databaseModule;

    public BooksTable(DatabaseModule databaseModule) {
        this.databaseModule = databaseModule;
    }

}
