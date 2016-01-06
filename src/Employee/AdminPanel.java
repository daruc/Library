package Employee;

import Employee.DataStructures.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by darek on 06.01.2016.
 */
public class AdminPanel extends JPanel {
    private MyFrame frame;
    private String user;
    private DatabaseModule dbModule;

    //GUI
    private JTable employeesTable;
    private JScrollPane scrollPane;
    private JPanel sidePanel;

    private JLabel label;
    private JButton add;
    private JButton edit;
    private JButton remove;

    public AdminPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        this.frame = frame;
        this.user = user;
        this.dbModule = dbModule;

        employeesTable = new JTable();
        scrollPane = new JScrollPane(employeesTable);
        sidePanel = new JPanel();

        label = new JLabel("Pracownicy");
        add = new JButton("Dodaj");
        edit = new JButton("Edytuj");
        remove = new JButton("Usuń");

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshTable();

        employeesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeesTable.getTableHeader().setReorderingAllowed(false);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        sidePanel.setLayout(new GridLayout(4,1));
        sidePanel.add(label);
        sidePanel.add(add);
        sidePanel.add(edit);
        sidePanel.add(remove);
        sidePanel.setMaximumSize(new Dimension(100, 100));
        sidePanel.setPreferredSize(new Dimension(100, 100));
        sidePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        add(sidePanel);
        add(scrollPane);
    }

    public void refreshTable() {
        ArrayList<Employee> employees = dbModule.getEmployees();
        employeesTable.setModel(new EmployeesTableModel(employees));
    }
}
