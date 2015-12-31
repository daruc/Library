package Employee;

import javax.swing.*;

/**
 * Created by darek on 04.12.2015.
 */
public class ClientsTable extends JTable {
    private DatabaseModule databaseModule;

    public ClientsTable(DatabaseModule databaseModule) {
        this.databaseModule = databaseModule;
    }
}
