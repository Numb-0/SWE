package LC.PrenotationApp;

import java.sql.*;
/*
 *1. import --->java.sql
 *2. load and register the driver ---> com.jdbc.
 *3. create connection
 *4. create a statement
 *5. execute the query
 *6. process the results
 *7. close
 */
public class DataBaseController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");
        System.out.println("Driver loaded");

        // Try to connect
        Connection connection = DriverManager.getConnection
                ("jdbc:mariadb://localhost/test", "cosix", "lego");

        // Giving a command to database
        Statement stat = connection.createStatement();
        String query = "INSERT INTO User (USerName, City, Age) VALUES ('Gino', 'Firenze', '45' );";
        int result = stat.executeUpdate(query);

        System.out.println("It works!");

        String get_query = "SELECT * FROM User;";
        ResultSet rs = stat.executeQuery(get_query);
        // Iterate over the result set
        while (rs.next()) {
            // Retrieve the value of the 'City' column for the current row
            String name = rs.getString("City");
            System.out.println(name);
        }
        connection.close();
    }
}