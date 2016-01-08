package Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import Employee.DataStructures.*;

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
                book.genre = rs.getString(4).trim();
                book.description = rs.getString(5);
                book.number_of_books = rs.getInt(6);
                book.borrowed = rs.getInt(7);
                book.id = rs.getInt(8);
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
        if (numberOfBooks.equals(""))
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

    public void removeBook(String isbn) {
        CallableStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call removebook(?) }";
            st = con.prepareCall(sql);
            st.setString(1, isbn);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateBook(String targetIsbn,String isbn, String title,
                              String author, String genre, String numberOfBooks,
                           String description, int id) {
        if (isbn.equals(""))
            return false;
        if (title.equals(""))
            return false;
        if (numberOfBooks.equals(""))
            return false;

        CallableStatement st = null;
        boolean success = false;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {
            String sql = "{call updatebook(?,?,?,?,?,?,?,?)}";
            st = con.prepareCall(sql);
            st.setString(1, targetIsbn);
            st.setString(2, isbn);
            st.setString(3, title);
            st.setString(4, author);
            st.setString(5, genre);
            st.setInt(6, Integer.valueOf(numberOfBooks));
            st.setString(7, description);
            st.setInt(8, id);

            st.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ArrayList<Client> getClients(String name, String surname) {
        ArrayList<Client> clients = new ArrayList<Client>();

        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {
            String sql = "SELECT * FROM getclients(?,?)";
            st = con.prepareCall(sql);
            st.setString(1, name);
            st.setString(2, surname);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.name = rs.getString(1);
                client.surname = rs.getString(2);
                client.address = rs.getString(3);
                client.date_of_birth = rs.getDate(4);
                client.max_borrowed = rs.getInt(5);
                client.borrowed = rs.getInt(6);
                client.days_to_return_book = rs.getInt(7);
                client.privileges = rs.getInt(8);
                client.password = rs.getString(9);
                client.id = rs.getInt(10);
                client.login = rs.getString(11);

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }

    public boolean addClient(String name, String surname, String address,
                             String dateOfBirth, String maxBorrowed,
                             String daysToReturnBook, String privileges,
                             String password, String login) {
        if (surname.equals(""))
            return false;
        if (privileges.equals(""))
            return false;
        if (daysToReturnBook.equals(""))
            return false;
        if (maxBorrowed.equals(""))
            return false;
        if (password.equals(""))
            return false;
        if (login.equals(""))
            return false;

        boolean success = true;
        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call addclient(?,?,?,?,?,?,?,?,?) }";
            st = con.prepareCall(sql);
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, address);
            st.setDate(4, Date.valueOf(dateOfBirth));
            st.setInt(5, Integer.valueOf(maxBorrowed));
            st.setInt(6, Integer.valueOf(daysToReturnBook));
            st.setInt(7, Integer.valueOf(privileges));
            st.setString(8, password);
            st.setString(9, login);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public boolean updateClient(String name, String surname, String address,
                                String dateOfBirth, String maxBorrowed,
                                String daysToReturnBook, String privileges,
                                String password, int intBorrowed, int id, String login) {
        if (surname.equals(""))
            return false;
        if (privileges.equals(""))
            return false;
        if (daysToReturnBook.equals(""))
            return false;
        if (maxBorrowed.equals(""))
            return false;
        if (password.equals(""))
            return false;
        if (login.equals(""))
            return false;

        boolean success = true;
        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call updateclient(?,?,?,?,?,?,?,?,?,?,?) }";
            st = con.prepareCall(sql);
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, address);
            st.setDate(4, Date.valueOf(dateOfBirth));
            st.setInt(5, Integer.valueOf(maxBorrowed));
            st.setInt(6, Integer.valueOf(daysToReturnBook));
            st.setInt(7, Integer.valueOf(privileges));
            st.setString(8, password);
            st.setInt(9, intBorrowed);
            st.setInt(10, id);
            st.setString(11, login);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public void removeClient(int id) {
        CallableStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call removeclient(?) }";
            st = con.prepareCall(sql);
            st.setInt(1, id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();

        Statement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "SELECT * FROM getemployees();";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee();
                employee.id = rs.getInt(1);
                employee.login = rs.getString(2);
                employee.name = rs.getString(3);
                employee.surname = rs.getString(4);
                employee.address = rs.getString(5);
                employee.date_of_birth = rs.getDate(6);
                employee.privileges = rs.getInt(7);
                employee.password = rs.getString(8);

                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    public boolean addEmployee(String strLogin, String strName, String strSurname, String strAddress,
                               String strDate, String strPrivileges, String strPassword) {
        boolean success = true;
        if (strSurname.equals(""))
            return false;
        if (strPrivileges.equals(""))
            return false;
        if (strLogin.equals(""))
            return false;
        if (strDate.equals(""))
            return false;
        if (strPassword.equals(""))
            return false;

        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call addemployee(?,?,?,?,?,?,?) }";
            st = con.prepareCall(sql);
            st.setString(1, strLogin);
            st.setString(2, strName);
            st.setString(3, strSurname);
            st.setString(4, strAddress);
            st.setDate(5, Date.valueOf(strDate));
            st.setInt(6, Integer.valueOf(strPrivileges));
            st.setString(7, strPassword);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public boolean updateEmployee(String login, String name, String surname,
                                  String address, String date, String privileges,
                                  String password, int id) {
        boolean success = true;
        if (surname.equals(""))
            return false;
        if (privileges.equals(""))
            return false;
        if (login.equals(""))
            return false;
        if (date.equals(""))
            return false;
        if (password.equals(""))
            return false;

        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call updateemployee(?,?,?,?,?,?,?,?) }";
            st = con.prepareCall(sql);
            st.setString(1, login);
            st.setString(2, name);
            st.setString(3, surname);
            st.setString(4, address);
            st.setDate(5, Date.valueOf(date));
            st.setInt(6, Integer.valueOf(privileges));
            st.setString(7, password);
            st.setInt(8, id);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public void removeEmployee(int id) {
        CallableStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call removeemployee(?) }";
            st = con.prepareCall(sql);
            st.setInt(1, id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void borrowBook(int bookId, int clientId) {
        CallableStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {
            String sql = "{ call borrow(?,?) }";
            st = con.prepareCall(sql);
            st.setInt(1, bookId);
            st.setInt(2, clientId);

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<BorrowedBook> getBorrowedBooks(int client_id) {
        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<BorrowedBook>();
        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "SELECT * FROM getborrowedbooks(?)";
            st = con.prepareCall(sql);
            st.setInt(1, client_id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BorrowedBook book = new BorrowedBook();
                book.isbn = rs.getString(1);
                book.borrowed = rs.getDate(2);
                book.return_date = rs.getDate(3);
                book.id = rs.getInt(4);

                borrowedBooks.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return borrowedBooks;
    }

    public void returnBook(int orderId) {
        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "{ call returnbook(?) }";
            st = con.prepareCall(sql);
            st.setInt(1, orderId);

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Alert> getAlerts() {
        ArrayList<Alert> alerts = new ArrayList<Alert>();

        PreparedStatement st = null;
        try (Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"))) {

            String sql = "SELECT * FROM getalerts()";
            st = con.prepareCall(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.id = rs.getInt(1);
                book.title = rs.getString(2);
                book.description = rs.getString(3);
                book.isbn = rs.getString(4);
                book.genre = rs.getString(5);
                book.author = rs.getString(6);

                Client client = new Client();
                client.id = rs.getInt(7);
                client.name = rs.getString(8);
                client.surname = rs.getString(9);
                client.address = rs.getString(10);
                client.date_of_birth = rs.getDate(11);
                client.max_borrowed = rs.getInt(12);
                client.borrowed = rs.getInt(13);
                client.days_to_return_book = rs.getInt(14);
                client.privileges = rs.getInt(15);

                Date borrow_date = rs.getDate(16);
                Date return_date = rs.getDate(17);

                Alert alert = new Alert();
                alert.book = book;
                alert.client = client;
                alert.borrow_date = borrow_date;
                alert.return_date = return_date;

                alerts.add(alert);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return alerts;
    }
}
