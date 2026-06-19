import java.sql.*;
import java.util.Scanner;

public class Dynamic {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mits";

        Connection c = DriverManager.getConnection(url, "root", "1234");

        Scanner sc = new Scanner(System.in); 

        PreparedStatement ps = c.prepareStatement("INSERT INTO student01 VALUES(?, ?)");

        System.out.println("Enter ID:");
        int id = sc.nextInt();

        System.out.println("Enter Name:");
        String name = sc.next();

        ps.setInt(1, id);
        ps.setString(2, name);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Record inserted successfully.");
        } else {
            System.out.println("Record insertion failed.");
        }

        ps.close();
        c.close();
        sc.close();
    }
}