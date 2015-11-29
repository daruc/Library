import javax.swing.*;

/**
 * Created by darek on 29.11.2015.
 */
public class InfoPanel extends MyPanel {
    private String user;
    private DatabaseModule dbModule;

    private String[] userInfo;

    private JLabel informations;
    private JLabel loginDescription;
    private JLabel login;
    private JLabel nameDescription;
    private JLabel name;
    private JLabel surnameDescription;
    private JLabel surname;
    private JLabel privilagesDescription;
    private JLabel privileges;

    public InfoPanel(DatabaseModule dbModule, String user) {
        super();
        this.dbModule = dbModule;
        this.user = user;

        try {
            userInfo = dbModule.getEmployee(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        informations = new JLabel("Informacje o zalogowanym użytkowniku");
        loginDescription = new JLabel("Login: ");
        login = new JLabel(userInfo[0]);
        nameDescription = new JLabel("Imię: ");
        name = new JLabel(userInfo[1]);
        surnameDescription = new JLabel("Nazwisko: ");
        surname = new JLabel(userInfo[2]);

    }

    @Override
    public void draw() {

    }
}
