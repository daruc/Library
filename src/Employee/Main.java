package Employee;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;

/**
 * Created by darek on 28.11.2015.
 */
public class Main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        DatabaseModule databaseModule = new DatabaseModule("database.properties");
        invokeLater(() -> new LoginFrame("System biblioteczny", databaseModule));
    }
}
