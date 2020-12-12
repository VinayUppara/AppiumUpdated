package practise.AppiumFramework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{



	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection con = null;
		String host = "localhost";
		String port = "3306";
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/qadb", "root", "Sqlserver@123");
		
		return con;
	}


	/*
	 * public static Object[][] getData(String query) throws SQLException{
	 * ArrayList<Object[]> rows = new ArrayList<Object[]>(); Connection con;
	 * Statement statement; ResultSet resultSet;
	 * 
	 * statement = con.createStatement(); resultSet = statement.executeQuery(query);
	 * 
	 * while(resultSet.next()){ int columnCount =
	 * resultSet.getMetaData().getColumnCount(); Object[] columns = new
	 * Object[columnCount]; for(int i=0;i<columnCount;i++) { columns[i] =
	 * resultSet.getObject(i+1); } rows.add(columns); } return rows.toArray(new
	 * Object[][] {}); }
	 */

	public static List<Map<String,Object>> getRecordAsMap(String query) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{

		Connection con=null;
		Statement statement=null;
		ResultSet resultSet=null;
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		try {
			con = getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(query);
			//Map<String, Object> map = new HashMap<String, Object>();
			while(resultSet.next()) {

				Map<String, Object> map = new HashMap<String, Object>();
				int columnsCount = resultSet.getMetaData().getColumnCount();

				for (int i = 1; i <=columnsCount; i++) {

					map.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
					//System.out.println(resultSet.getMetaData().getColumnLabel(i));

				}
				rows.add(map);

			}
		} finally {
			statement.close();
			con.close();
		}



		return rows;

	}




	public static ArrayList<String> getStringListFromMap(List<Map<String, Object>> list, String columnName) throws SQLException{

		ArrayList<String> arrayList = new ArrayList<String>();

		for(int i =0;i<list.size();i++) {
			Object str = list.get(i).get(columnName);
			if(str!=null) {
				arrayList.add(String.valueOf(str).replaceAll("\n", ""));
			}else if(str==null) {
				arrayList.add("null");
			}
		}



		return arrayList;
	}
	
	
	public static void insertQuery(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection con=null;
		Statement statement=null;
		//ResultSet resultSet=null;
		
		try {
			con = getConnection();
			statement = con.createStatement();
			statement.executeUpdate(query);
		} finally {
			con.close();
			statement.close();
		}
	}




	public static void main(String...strings) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException 

	{	

		String queryString = "Select * from EmployeeInfo";
		
		String query = "Insert into EmployeeInfo (name,id,location,age,interest) values('sushanth',5,'pattikonda',10,'videos')";

		/*Object[][] resultSet1 = getData("Select * from EmployeeInfo where interest='movies'");
		 * for(Object[] result : resultSet1) { String data1 = result[1].toString();
		 * System.out.println(data1); }
		 */
		
		//insertQuery(query);

		System.out.println(getStringListFromMap(getRecordAsMap(queryString),"interest"));




	}



}
