import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Display {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mits";

        try (Connection conn = DriverManager.getConnection(url, "root", "1234")) {

            Statement stmt = conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = stmt.executeQuery("select * from student01");

            System.out.println("id\tname");
            System.out.println("---------------");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
            }
            System.out.println();
            while(rs.previous()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
            }
        }
    }
}