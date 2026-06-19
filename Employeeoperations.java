import java.sql.*;
import java.util.Scanner;

public class Employeeoperations{

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

                System.out.println("\n========== Employee Menu ==========");
                System.out.println("1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Delete Employee");
                System.out.println("4. Update Salary");
                System.out.println("5. Display Column Details");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    // Add Employee
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
                                "INSERT INTO employee1 VALUES(" +
                                emp_id + ",'" +
                                emp_name + "','" +
                                emp_jobrole + "','" +
                                emp_hiredate + "'," +
                                emp_salary + ")";

                        int r = stmt.executeUpdate(insertQuery);

                        if (r > 0)
                            System.out.println("Employee inserted successfully.");
                        else
                            System.out.println("Insertion failed.");

                        break;

                    // Display Employees
                    case 2:

                        ResultSet rs = stmt.executeQuery("SELECT * FROM employee1");

                        System.out.println("\n-------------------------------------------------------------");
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

                    // Delete Employee
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

                    // Update Salary
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

                    // Display Column Details
                    case 5:

                        ResultSet rs1 = stmt.executeQuery("SELECT * FROM employee1");

                        System.out.println("\n================ EMPLOYEE DETAILS ================");

                        System.out.printf("%-10s %-15s %-15s %-15s %-10s%n",
                                "EMP_ID", "EMP_NAME", "JOB_ROLE", "HIRE_DATE", "SALARY");

                        System.out.println("--------------------------------------------------------------------------");

                        while (rs1.next()) {

                            System.out.printf("%-10d %-15s %-15s %-15s %-10.2f%n",
                                    rs1.getInt("emp_id"),
                                    rs1.getString("emp_name"),
                                    rs1.getString("emp_jobrole"),
                                    rs1.getDate("emp_hiredate"),
                                    rs1.getDouble("emp_salary"));
                        }

                        break;

                    // Exit
                    case 6:

                        stmt.close();
                        conn.close();
                        sc.close();

                        System.out.println("Thank You...");
                        return;

                    default:
                        System.out.println("Invalid Choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}