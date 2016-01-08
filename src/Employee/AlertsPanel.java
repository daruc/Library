package Employee;

import Employee.DataStructures.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by darek on 07.01.2016.
 */
public class AlertsPanel extends JPanel {
    private JButton refreshButton;
    private JTable table;
    private JScrollPane scrollPane;

    private MyFrame frame;
    private DatabaseModule dbModule;

    public AlertsPanel(MyFrame frame, DatabaseModule dbModule) {
        this.frame = frame;
        this.dbModule = dbModule;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        refreshButton = new JButton("Odśwież");
        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        ArrayList<Alert> alerts = dbModule.getAlerts();

        AlertsTableModel model = new AlertsTableModel(alerts);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(table);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Alert> alerts = dbModule.getAlerts();
                AlertsTableModel model = new AlertsTableModel(alerts);
                table.setModel(model);
            }
        });

        table.setRowSelectionAllowed(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Point p = e.getPoint();
                    AlertsTableModel model = (AlertsTableModel) table.getModel();
                    int row = table.rowAtPoint(p);
                    int column = table.columnAtPoint(p);
                    Alert alert = model.getAlert(row);
                    if (column == 0) {
                        EventQueue.invokeLater(() -> new InfoBookFrame("Szczegóły książki", dbModule, alert.book));
                    }
                    else if (column == 1) {
                        EventQueue.invokeLater(() -> new InfoClientFrame("Sczegóły klienta", dbModule, alert.client));
                    }
                }
            }
        });

        add(refreshButton);
        add(scrollPane);
    }
}
