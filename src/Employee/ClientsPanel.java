package Employee;

import Employee.DataStructures.Client;
import Employee.DataStructures.Employee;
import Employee.TableModels.ClientsTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private Employee user;

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

        if (user.privileges >= 2) {
            sidebarPanel.add(managementDescription);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            sidebarPanel.add(managementPanel);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 80)));
            sidebarPanel.add(booksDescription);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            sidebarPanel.add(booksPanel);
        }
        else {
            sidebarPanel.setAlignmentY(Component.TOP_ALIGNMENT);
            Dimension dim = sidebarPanel.getMaximumSize();
            dim.height = 200;
            sidebarPanel.setMaximumSize(dim);
        }



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

        clientsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ClientsPanel thisPanel = this;
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = clientsTable.getSelectedRow();
                ClientsTableModel model = (ClientsTableModel) clientsTable.getModel();
                Client client = model.getClient(row);
                EventQueue.invokeLater(() -> new EditClientFrame("Edytuj klienta", databaseModule,
                        thisPanel, (MyFrame) frame, client));
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> new AddClientFrame("Dodak klienta", databaseModule));
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = clientsTable.getSelectedRow();
                ClientsTableModel model = (ClientsTableModel) clientsTable.getModel();

                Client client = model.getClient(row);
                databaseModule.removeClient(client.id);
                model.removeRow(row);
            }
        });

        clientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRows = clientsTable.getSelectedRowCount();
                if (selectedRows == 1) {
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    returnButton.setEnabled(true);
                    borrowButton.setEnabled(true);
                }
                else {
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    borrowButton.setEnabled(false);
                    returnButton.setEnabled(false);
                }
            }
        });

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        borrowButton.setEnabled(false);
        returnButton.setEnabled(false);


        scrollPane = new JScrollPane(clientsTable);
        scrollPane.setSize(390, scrollPane.getHeight());

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsTableModel model = (ClientsTableModel) clientsTable.getModel();
                int row = clientsTable.getSelectedRow();
                Client client = model.getClient(row);

                if (client.borrowed < client.max_borrowed) {
                    EventQueue.invokeLater(() -> new BorrowBookFrame(
                            "Wypożycz czytelnikowi " + client.name + " " + client.surname, databaseModule, client));
                }
                else {
                    JOptionPane.showMessageDialog(thisPanel, "Czytelnik osiągnął limit wypożyczeń.",
                            "Wypożyczanie nieudane", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsTableModel model = (ClientsTableModel) clientsTable.getModel();
                int row = clientsTable.getSelectedRow();
                Client client = model.getClient(row);

                if (client.borrowed > 0) {
                    EventQueue.invokeLater(() -> new ReturnBookFrame(
                            "Oddaj książki czytelnika " + client.name + " " + client.surname, databaseModule, client));
                }
                else {
                    JOptionPane.showMessageDialog(thisPanel, "Czytelnik nie posiada książek do oddania.",
                            "Brak wypożyczonych książek", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        add(sidebarPanel);
        add(scrollPane);

    }

    public ClientsPanel(MyFrame frame, Employee user, DatabaseModule dbModule) {
        this.frame = frame;
        this.databaseModule = dbModule;
        this.user = user;

        drawGUI();
    }

    public JTable getTable() { return clientsTable; }
}
