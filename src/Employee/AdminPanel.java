package Employee;

import Employee.Buttons.UpdateEmployeeButton;
import Employee.DataStructures.Employee;

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

        employeesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);

                if (e.getClickCount() == 2) {
                    EmployeesTableModel model = (EmployeesTableModel) table.getModel();
                    Employee employee = model.getEmployee(row);
                    EventQueue.invokeLater(() -> new InfoEmployeeFrame("Opis pracownika", dbModule, employee));
                }
            }
        });

        employeesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int count = employeesTable.getSelectedRowCount();
                if (count == 1) {
                    edit.setEnabled(true);
                    remove.setEnabled(true);
                }
                else {
                    edit.setEnabled(false);
                    remove.setEnabled(false);
                }
            }
        });

        AdminPanel thisPanel = this;
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> new AddEmployeeFrame("Dodaj pracownika", thisPanel, dbModule));
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = employeesTable.getSelectedRow();
                EmployeesTableModel model = (EmployeesTableModel) employeesTable.getModel();
                Employee employee = model.getEmployee(row);
                EventQueue.invokeLater(() -> new EditEmployeeFrame("Aktualizuj pracownika",
                        thisPanel, dbModule, employee));
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = employeesTable.getSelectedRow();
                EmployeesTableModel model = (EmployeesTableModel) employeesTable.getModel();
                Employee employee = model.getEmployee(row);
                dbModule.removeEmployee(employee.id);
                refreshTable();
            }
        });

        edit.setEnabled(false);
        remove.setEnabled(false);

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
