import java.sql.*;

public class Conn {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mits";
        String username = "root";
        String password = "1234";

        System.out.println("connecting to database...");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            System.out.println("database connected.");

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                "create table if not exists student01 (" +
                "id int primary key, " +
                "name varchar(20))"
            );

            System.out.println("table created.");

            stmt.executeUpdate("delete from student01");
        
            stmt.executeUpdate("insert into student01 values (101, 'sai')");
            stmt.executeUpdate("insert into student01 values (102, 'ram')");
            stmt.executeUpdate("insert into student01 values (103, 'krishna')");
            stmt.executeUpdate("insert into student01 values (104, 'ravi')");

            System.out.println("records inserted.");
            ResultSet rs = stmt.executeQuery("select * from student01");

            System.out.println();
            System.out.println("id\tname");
            System.out.println("----------------");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}