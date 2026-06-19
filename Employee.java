package src;

import java.sql.*;

public class Employee {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mits";
        String u = "root";
        String p = "1234";
        try {
            Connection con = DriverManager.getConnection(url, u, p);
            Statement st = con.createStatement();
            st.execute("Create table if not exists Emp1(emp_id int primary key,emp_name varchar(20),role varchar(20),hire_date date,salary int)");
            System.out.println("Table created successfully");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}