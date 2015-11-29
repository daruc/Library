import javax.swing.*;

/**
 * Created by darek on 29.11.2015.
 */
public class AuthorsPanel extends JPanel {
    private MyFrame frame;
    private String user;
    private DatabaseModule dbModule;


    public AuthorsPanel(MyFrame frame, String user, DatabaseModule dbModule) {
        this.frame = frame;
        this.user = user;
        this.dbModule = dbModule;


    }
}
