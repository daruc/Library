package Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import Employee.DataStructures.Book;

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

    public ArrayList<Book> getBooks(String isbn, String title, String genre, String author) {
        ArrayList<Book> books = new ArrayList<Book>();

        PreparedStatement st = null;
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")) ) {
            st = connection.prepareStatement("SELECT * FROM getbooks(?,?,?,?)");
            st.setString(1, isbn);
            st.setString(2, title);
            st.setString(3, genre);
            st.setString(4, author);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.isbn = rs.getString(1);
                book.title = rs.getString(2);
                book.author = rs.getString(3);
                book.genre = rs.getString(4);
                book.description = rs.getString(5);
                book.number_of_books = rs.getInt(6);
                book.borrowed = rs.getInt(7);
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
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

    public boolean addBook(String isbn, String title, String author, String genre, String numberOfBooks,
                           String description) {
        if (isbn.equals(""))
            return false;
        if (title.equals(""))
            return false;

        boolean success = false;
        CallableStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {
            String sql = "{call addbook(?,?,?,?,?,?)}";
            st = con.prepareCall(sql);
            st.setString(1, isbn);
            st.setString(2, title);
            st.setString(3, author);
            st.setString(4, genre);
            st.setInt(5, Integer.valueOf(numberOfBooks));
            st.setString(6, description);

            st.execute();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }

        }

        return success;
    }
}
