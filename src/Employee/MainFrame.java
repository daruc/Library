package Employee;

import Employee.DataStructures.Employee;

import javax.swing.*;
import java.sql.Date;

/**
 * Created by darek on 28.11.2015.
 */
public class MainFrame extends MyFrame {
    private String user;
    private Employee employee;

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
        infoPanel = new InfoPanel(this, employee, super.getDatabaseModule());
        booksPanel = new BooksPanel(this, employee, getDatabaseModule());
        clientsPanel = new ClientsPanel(this, employee, getDatabaseModule());
        alertsPanel = new AlertsPanel(this, getDatabaseModule());

        tabbedPane.addTab("Informacje", null, infoPanel, "Ogólne informacje o zalogowanym użytkowniku");
        tabbedPane.addTab("Książki", null, booksPanel, "Zarządzanie książkami");
        tabbedPane.addTab("Czytelnicy", null, clientsPanel, "Zarządzanie klientami");
        tabbedPane.addTab("Alarmy", null, alertsPanel, "Powiadomienia o nieoddanych w terminie książkach");

        if (employee.privileges >= 3) {
            adminPanel = new AdminPanel(this, user, getDatabaseModule());
            tabbedPane.addTab("Administrator", null, adminPanel, "Zarządzanie systemem");
        }

        add(tabbedPane);
    }

    public MainFrame(String title, String user, DatabaseModule databaseModule) {
        super(title, databaseModule);
        this.user = user;
        try {
            String[] tableEmployee = databaseModule.getEmployee(user);
            employee = new Employee();
            employee.login = tableEmployee[0];
            employee.name = tableEmployee[1];
            employee.surname = tableEmployee[2];
            employee.address = tableEmployee[3];
            employee.date_of_birth = Date.valueOf(tableEmployee[4]);
            employee.privileges = Integer.valueOf(tableEmployee[5]);

        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawGUI();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
