import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
	int choice; 	
	Scanner scnrObj = new Scanner(System.in); 
	String strUserAction = "N";
	Connection conn = null;
	try {
		
				//Step1- register the driver	
				Class.forName("com.mysql.cj.jdbc.Driver");
					
				//establish the connection
				conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/adiDB", "root", "Work@2024");
				System.out.println("Connectoin created");
				
				// TODO : Add code for creating this table first.
		/*  Query used for creating table is 
		 *  create table Person (personId int not null auto_increment,
				 personName varchar(100) , 
				 personCity varchar(100) ,
				 primary key (personId) );

		 *  */		
				
			 do { 
				 System.out.println("What would you like to perform ? Enter 1-Insert, 2-Update, 3-Selct, 4-delete");
				 choice = scnrObj.nextInt();
				 scnrObj.nextLine();
				    if (choice==1) {
				    	insertTable( conn);
				    }
				    else if (choice==3) {
				    	selectFromTable(conn);
				    }
				    else if (choice==2) {
				    	updateToTable(conn);
				    }
				    else if (choice==4) {
				    	deleteFromTable(conn);
				    }
				 System.out.println("Do you want to perform other operation ? (Y/N )  ");
				 strUserAction = scnrObj.nextLine();
				 
			 } while (strUserAction.equalsIgnoreCase("Y"));
			 
			 System.out.println("At the end of the program");
		}
		catch(SQLException sqlex) {
			System.out.println("SQL Error in execution " +sqlex.toString() );	
		}
		catch (Exception ex) {
			System.out.println("Error in execution " + ex);
		}
		finally {
		conn.close();
		}
	} // Main() Ends
	
	
	
	static void insertTable ( Connection conn) throws SQLException {
		Scanner scnrObj2 = new Scanner(System.in); 
		
		// Statement stmt = conn.createStatement();
       //  stmt.executeQuery("insert into Person values (4, "+ "'amol' "+" , 'panvel')");
		
		PreparedStatement pst = conn.prepareStatement("insert into Person values ( ?, ? , ?)");
		//pst.setString(1, "4");
		pst.setInt(1, 3);
		pst.setString(2, "Amol");
		pst.setString(3, "Dubai");
		int count = pst.executeUpdate();
		if(count>0) {
			System.out.println("User registered successully");
		}else {
			System.out.println("Error message");
		}
	} // insertTable Function ends
	
	static void selectFromTable ( Connection conn) throws SQLException {
		    Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("select * from Person");
	         while(rs.next()){
	             //Display values
	             System.out.print("ID: " + rs.getInt("PersonId"));
	             System.out.print(", Name: " + rs.getString("PersonName"));
	             System.out.print(", City: " + rs.getString("PersonCity"));
	             System.out.println();
	         }
	        		
	}
	
	static void updateToTable ( Connection conn) throws SQLException {
	    Statement stmt = conn.createStatement();
        stmt.executeUpdate("update Person set PersonName= 'Manasi'  where PersonID= 1");
        
        System.out.println("Record is updated successfully......");
	}
	
	static void deleteFromTable ( Connection conn) throws SQLException {
	    Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM Person WHERE PersonName='Aarya' ");
        		
        System.out.println("Record is deleted successfully......");
	 }
}
