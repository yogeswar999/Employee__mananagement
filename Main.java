import java.sql.*;
import java.util.Scanner;

public class Main {

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
                        System.out.println("Display code here.");
                        break;

                    case 3:
                        System.out.println("Delete code here.");
                        break;

                    case 4:
                        System.out.println("Update code here.");
                        break;

                    case 5:
                        System.out.println("Display column details code here.");
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