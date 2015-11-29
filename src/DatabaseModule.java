import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by darek on 28.11.2015.
 */
public class DatabaseModule {
    private Properties properties;

    public DatabaseModule(String propFile) {

        FileInputStream fileInputStream = null;
        properties = new Properties();

        try {
            fileInputStream = new FileInputStream(propFile);
            properties.load(fileInputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public boolean checkLoginAndPassword(String login, String password){
        boolean result = false;

        PreparedStatement st = null;
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")) ) {
            st = connection.prepareStatement("SELECT * FROM checkpassword(?,?)");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public String[] getEmployee(String login) throws Exception {
        String[] result = new String[6];

        PreparedStatement st = null;
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")) ) {
            st = connection.prepareStatement("SELECT * FROM getemployee(?)");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result[0] = rs.getString(1);    //login
                result[1] = rs.getString(2);    //name
                result[2] = rs.getString(3);    //surname
                result[3] = rs.getString(4);    //address
                Date birth = rs.getDate(5);     //date_of_birth
                if (birth != null)
                    result[4] = birth.toString();
                else
                    result[4] = "";
                Integer privilages = new Integer(rs.getInt(6)); //privilages
                if (privilages != null)
                    result[5] = privilages.toString();
                else
                    result[5] = "";
            }
            else {
                throw new Exception("Employee doesn't exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }
}
