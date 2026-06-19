package src;
import java.util.*;
import java.sql.*;
public class Emp {
   public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    try{
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mits","root","1234");
        Statement st=conn.createStatement();
        boolean t=true;
        while(t){
            System.out.println("Enter the operation: ");
             System.out.println("1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Delete Employee");
                System.out.println("4. Update Salary");
                System.out.println("5. Display Column Details");
                System.out.println("6. Exit");
                int op=sc.nextInt();
                switch(op){
                case 1:
                    PreparedStatement ps=conn.prepareStatement("Insert into employee1 values(?,?,?,?,?)");
                    System.out.println("enter the details one by one");
                    System.out.println("emp_id,emp_name,role,hire_date,salary");
                    ps.setInt(1,sc.nextInt());
                    ps.setString(2,sc.next());
                    ps.setString(3,sc.next());
                    ps.setString(4,sc.next());
                    ps.setInt(5,sc.nextInt());
                    int r=ps.executeUpdate();
                    if(r>0){
                        System.out.println("successfully inesrted");
                    }else{
                        System.out.println("failed to insert");
                    }
                    break;
                case 2:
                    ResultSet re=st.executeQuery("select *from employee1");
                    while(re.next()){
                        System.out.println(re.getInt("emp_id")+" "+re.getString("emp_name")+" "+re.getString("emp_jobrole")+" "+re.getInt("emp_salary"));
                    }
                    break;
                case 3:
                    System.out.println("enter the id of emp");
                    int id=sc.nextInt();
                    PreparedStatement p=conn.prepareStatement("delete from emp1 where emp_id=?");
                    p.setInt(1,id);
                    int u=p.executeUpdate();
                    if(u>0){
                        System.out.println("updation sucess");
                    }else{
                        System.out.println("updation fails");
                    }
                    break;
                case 4:
                    System.out.println("enter the id of the employee and updated salary");
                    int id1=sc.nextInt();
                    int salary=sc.nextInt();
                    PreparedStatement p1=conn.prepareStatement("update employee1 set  emp_salary=? where emp_id=?");
                    p1.setInt(1,salary);
                    p1.setInt(2, id1);
                    p1.executeUpdate();
                    break;
                case 5:
                    System.out.println("enter the column to display");
                    String s=sc.next();
                    PreparedStatement p2=conn.prepareStatement("select ? from employee1");
                    p2.setString(1, s);
                    p2.executeQuery();
                    break;
                case 6:
                    t=false;
                    System.out.println("Thanks for using");
                    break;
                default:
                    System.out.println("not valid operation");
            }
        }
    }  
    catch(Exception e){
        e.printStackTrace();
    } 
    sc.close(); 
   } 
}