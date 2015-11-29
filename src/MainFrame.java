import javax.swing.*;

/**
 * Created by darek on 28.11.2015.
 */
public class MainFrame extends JFrame {
    private String user;

    private JTabbedPane tabbedPane;
    private JPanel infoPanel;
    private JPanel booksPanel;
    private JPanel clientsPanel;
    private JPanel alertsPanel;

    //infoPanel

    private void drawGUI() {
        setResizable(false);
        setSize(700, 500);

        tabbedPane = new JTabbedPane();
        infoPanel = new JPanel();
        booksPanel = new JPanel();
        clientsPanel = new JPanel();
        alertsPanel = new JPanel();

        tabbedPane.addTab("Informacje", null, infoPanel, "Ogólne informacje o zalogowanym użytkowniku");
        tabbedPane.addTab("Książki", null, booksPanel, "Zarządzanie książkami");
        tabbedPane.addTab("Czytelnicy", null, clientsPanel, "Zarządzanie klientami");
        tabbedPane.addTab("Alarmy", null, alertsPanel, "Powiadomienia o nieoddanych w terminie książkach");

        drawInfoPanel();
        add(tabbedPane);
    }

    private void drawInfoPanel() {

    }

    public MainFrame(String title, String user) {
        super(title);
        this.user = user;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawGUI();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
