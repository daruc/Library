package Employee;

import javax.swing.*;

/**
 * Created by darek on 28.11.2015.
 */
public class MainFrame extends MyFrame {
    private String user;

    private JTabbedPane tabbedPane;
    private InfoPanel infoPanel;
    private BooksPanel booksPanel;
    private ClientsPanel clientsPanel;
    private JPanel alertsPanel;
    private AdminPanel adminPanel;

    //infoPanel

    private void drawGUI() {
        setResizable(false);
        setSize(700, 500);

        tabbedPane = new JTabbedPane();
        infoPanel = new InfoPanel(this, user, super.getDatabaseModule());
        booksPanel = new BooksPanel(this, user, getDatabaseModule());
        clientsPanel = new ClientsPanel(this, user, getDatabaseModule());
        alertsPanel = new JPanel();
        adminPanel = new AdminPanel(this, user, getDatabaseModule());

        tabbedPane.addTab("Informacje", null, infoPanel, "Ogólne informacje o zalogowanym użytkowniku");
        tabbedPane.addTab("Książki", null, booksPanel, "Zarządzanie książkami");
        tabbedPane.addTab("Czytelnicy", null, clientsPanel, "Zarządzanie klientami");
        tabbedPane.addTab("Alarmy", null, alertsPanel, "Powiadomienia o nieoddanych w terminie książkach");
        tabbedPane.addTab("Administrator", null, adminPanel, "Zarządzanie systemem");

        add(tabbedPane);
    }

    public MainFrame(String title, String user, DatabaseModule databaseModule) {
        super(title, databaseModule);
        this.user = user;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawGUI();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
