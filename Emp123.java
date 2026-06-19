// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package src;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Emp22 {
   public Emp22() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);

      try {
         Connection var2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mits", "root", "1234");
         Statement var3 = var2.createStatement();
         boolean var4 = true;

         while(var4) {
            System.out.println("\n========== Employee Management ==========");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Display Particular Column");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int var5 = var1.nextInt();
            switch (var5) {
               case 1:
                  PreparedStatement var6 = var2.prepareStatement("INSERT INTO employee1 VALUES(?,?,?,?,?)");
                  System.out.print("Enter Employee ID: ");
                  var6.setInt(1, var1.nextInt());
                  System.out.print("Enter Employee Name: ");
                  var6.setString(2, var1.next());
                  System.out.print("Enter Employee Job Role: ");
                  var6.setString(3, var1.next());
                  System.out.print("Enter Hire Date (yyyy-mm-dd): ");
                  var6.setDate(4, Date.valueOf(var1.next()));
                  System.out.print("Enter Salary: ");
                  var6.setDouble(5, var1.nextDouble());
                  int var7 = var6.executeUpdate();
                  if (var7 > 0) {
                     System.out.println("Employee inserted successfully.");
                  } else {
                     System.out.println("Insertion failed.");
                  }
                  break;
               case 2:
                  ResultSet var8 = var3.executeQuery("SELECT * FROM employee1");
                  System.out.println("\n--------------------------------------------------------------");
                  System.out.println("ID\tNAME\tROLE\tHIRE DATE\tSALARY");
                  System.out.println("--------------------------------------------------------------");

                  while(var8.next()) {
                     PrintStream var10000 = System.out;
                     int var10001 = var8.getInt("emp_id");
                     var10000.println(var10001 + "\t" + var8.getString("emp_name") + "\t" + var8.getString("emp_jobrole") + "\t" + String.valueOf(var8.getDate("emp_hiredate")) + "\t" + var8.getDouble("emp_salary"));
                  }
                  break;
               case 3:
                  System.out.print("Enter Employee ID to delete: ");
                  int var9 = var1.nextInt();
                  PreparedStatement var10 = var2.prepareStatement("DELETE FROM employee1 WHERE emp_id=?");
                  var10.setInt(1, var9);
                  int var11 = var10.executeUpdate();
                  if (var11 > 0) {
                     System.out.println("Employee deleted successfully.");
                  } else {
                     System.out.println("Employee not found.");
                  }
                  break;
               case 4:
                  System.out.print("Enter Employee ID: ");
                  int var12 = var1.nextInt();
                  System.out.print("Enter New Salary: ");
                  double var13 = var1.nextDouble();
                  PreparedStatement var15 = var2.prepareStatement("UPDATE employee1 SET emp_salary=? WHERE emp_id=?");
                  var15.setDouble(1, var13);
                  var15.setInt(2, var12);
                  int var16 = var15.executeUpdate();
                  if (var16 > 0) {
                     System.out.println("Salary updated successfully.");
                  } else {
                     System.out.println("Employee not found.");
                  }
                  break;
               case 5:
                  System.out.println("Available Columns:");
                  System.out.println("emp_id");
                  System.out.println("emp_name");
                  System.out.println("emp_jobrole");
                  System.out.println("emp_hiredate");
                  System.out.println("emp_salary");
                  System.out.print("Enter column name: ");
                  String var17 = var1.next();
                  if (!var17.equals("emp_id") && !var17.equals("emp_name") && !var17.equals("emp_jobrole") && !var17.equals("emp_hiredate") && !var17.equals("emp_salary")) {
                     System.out.println("Invalid column name.");
                  } else {
                     ResultSet var18 = var3.executeQuery("SELECT " + var17 + " FROM employee1");

                     while(var18.next()) {
                        System.out.println(var18.getString(1));
                     }
                  }
                  break;
               case 6:
                  var4 = false;
                  var2.close();
                  var1.close();
                  System.out.println("Thank You...");
                  break;
               default:
                  System.out.println("Invalid Choice.");
            }
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }

   }
}
