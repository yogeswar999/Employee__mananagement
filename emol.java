import java.sql.*;
import java.util.Scanner;

public class emol {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/mits";
        String username = "root";
        String password = "1234";

        System.out.println("Connecting to database...");

        try {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            while (true) {

                System.out.println("\n===== Employee Menu =====");
                System.out.println("1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Delete Employee");
                System.out.println("4. Update Salary");
                System.out.println("5. Display Column Details");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        System.out.print("Enter Employee ID: ");
                        int emp_id = sc.nextInt();

                        System.out.print("Enter Employee Name: ");
                        String emp_name = sc.next();

                        System.out.print("Enter Job Role: ");
                        String emp_jobrole = sc.next();

                        System.out.print("Enter Hire Date (YYYY-MM-DD): ");
                        String emp_hiredate = sc.next();

                        System.out.print("Enter Salary: ");
                        double emp_salary = sc.nextDouble();

                        String insertQuery =
                                "INSERT INTO employee1 VALUES (" +
                                emp_id + ",'" +
                                emp_name + "','" +
                                emp_jobrole + "','" +
                                emp_hiredate + "'," +
                                emp_salary + ")";

                        stmt.executeUpdate(insertQuery);

                        System.out.println("Employee inserted successfully.");
                        break;

                    case 2:

                        ResultSet rs = stmt.executeQuery("SELECT * FROM employee1");

                        System.out.println("-------------------------------------------------------------");
                        System.out.println("ID\tNAME\tROLE\tHIRE DATE\tSALARY");
                        System.out.println("-------------------------------------------------------------");

                        while (rs.next()) {

                            System.out.println(
                                    rs.getInt("emp_id") + "\t" +
                                    rs.getString("emp_name") + "\t" +
                                    rs.getString("emp_jobrole") + "\t" +
                                    rs.getDate("emp_hiredate") + "\t" +
                                    rs.getDouble("emp_salary"));
                        }

                        break;

                    case 3:

                        System.out.print("Enter Employee ID to Delete: ");
                        int id = sc.nextInt();

                        String deleteQuery =
                                "DELETE FROM employee1 WHERE emp_id=" + id;

                        int d = stmt.executeUpdate(deleteQuery);

                        if (d > 0)
                            System.out.println("Employee deleted successfully.");
                        else
                            System.out.println("Employee not found.");

                        break;

                    case 4:

                        System.out.print("Enter Employee ID: ");
                        int eid = sc.nextInt();

                        System.out.print("Enter New Salary: ");
                        double salary = sc.nextDouble();

                        String updateQuery =
                                "UPDATE employee1 SET emp_salary=" + salary +
                                " WHERE emp_id=" + eid;

                        int u = stmt.executeUpdate(updateQuery);

                        if (u > 0)
                            System.out.println("Salary updated successfully.");
                        else
                            System.out.println("Employee not found.");

                        break;

                    case 5:

                        System.out.println("Available Columns:");
                        System.out.println("emp_id");
                        System.out.println("emp_name");
                        System.out.println("emp_jobrole");
                        System.out.println("emp_hiredate");
                        System.out.println("emp_salary");

                        System.out.print("Enter Column Name: ");
                        String column = sc.next();

                        ResultSet rs1 = stmt.executeQuery(
                                "SELECT " + column + " FROM employee1");

                        while (rs1.next()) {
                            System.out.println(rs1.getString(1));
                        }

                        break;

                    case 6:

                        conn.close();
                        sc.close();

                        System.out.println("Thank you!");
                        return;

                    default:

                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}