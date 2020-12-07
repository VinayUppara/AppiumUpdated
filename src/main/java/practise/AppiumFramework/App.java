package practise.AppiumFramework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    
    {
    	String host = "localhost";
    	String port = "3306";
       Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/qadb", "root", "Sqlserver@123");
       Statement statement = con.createStatement();
       ResultSet resultSet = statement.executeQuery("Select * from EmployeeInfo where id=2");
       while(resultSet.next()){
       System.out.println(resultSet.getString("name"));
    }
    }
}
