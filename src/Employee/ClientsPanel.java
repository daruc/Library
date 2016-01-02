package Employee;

import Employee.DataStructures.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by darek on 04.12.2015.
 */
public class ClientsPanel extends JPanel {
    private JFrame frame;
    private DatabaseModule databaseModule;
    private String user;

    private JPanel sidebarPanel;
    private JLabel searchDescription;
    private JPanel searchPanel;
    private JLabel nameDescription;
    private JTextField name;
    private JLabel surnameDescription;
    private JTextField surname;

    private JButton searchButton;

    private JLabel managementDescription;
    private JPanel managementPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private JTable clientsTable;
    private JScrollPane scrollPane;

    private JLabel booksDescription;
    private JButton borrowButton;
    private JButton returnButton;
    private JPanel booksPanel;

    public void drawGUI() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        searchDescription = new JLabel("Wyszukiwanie");
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 2));
        nameDescription = new JLabel("Imię ");
        name = new JTextField();
        surnameDescription = new JLabel("Nazwisko ");
        surname = new JTextField();
        searchPanel.add(nameDescription);
        searchPanel.add(name);
        searchPanel.add(surnameDescription);
        searchPanel.add(surname);

        searchButton = new JButton("Szukaj");


        managementDescription = new JLabel("Zarządzanie");
        managementPanel = new JPanel();
        managementPanel.setLayout(new GridLayout(2, 2));
        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        editButton = new JButton("Edytuj");
        managementPanel.add(addButton);
        managementPanel.add(deleteButton);
        managementPanel.add(editButton);


        booksDescription = new JLabel("Wypożyczenia książek");
        booksPanel = new JPanel();
        borrowButton = new JButton("Wypożycz");
        returnButton = new JButton("Oddaj");

        searchDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        managementDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        booksDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        booksPanel.setLayout(new GridLayout(1, 2));
        booksPanel.add(borrowButton);
        booksPanel.add(returnButton);

        sidebarPanel.add(searchDescription);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(searchPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(searchButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        sidebarPanel.add(managementDescription);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(managementPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        sidebarPanel.add(booksDescription);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebarPanel.add(booksPanel);


        clientsTable = new JTable();
        clientsTable.getTableHeader().setReorderingAllowed(false);

        //temporary model
        clientsTable.setModel(new ClientsTableModel(new ArrayList<Client>()));

        clientsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if (e.getClickCount() == 2) {
                    ClientsTableModel model = (ClientsTableModel) clientsTable.getModel();
                    Client client = model.getClient(row);
                    EventQueue.invokeLater(() -> new InfoClientFrame("Szczegóły klienta",
                            databaseModule, client));
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strName = name.getText();
                String strSurname = surname.getText();

                ArrayList<Client> clients = databaseModule.getClients(strName, strSurname);
                ClientsTableModel model = new ClientsTableModel(clients);
                clientsTable.setModel(model);
            }
        });

        scrollPane = new JScrollPane(clientsTable);
        scrollPane.setSize(390, scrollPane.getHeight());




        add(sidebarPanel);
        add(scrollPane);

    }

    public ClientsPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        this.frame = frame;
        this.databaseModule = dbModule;
        this.user = user;

        drawGUI();
    }
}
