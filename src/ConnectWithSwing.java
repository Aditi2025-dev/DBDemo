
	import java.awt.*;
	import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

	public class ConnectWithSwing extends Frame implements ActionListener{
	    TextField txt1 ;
	    TextField txt2;
	    TextField txtResult;
	    Button btn;
	    Label header;

	    ConnectWithSwing(){
	    	header = new Label("Add Person Details");
	    	header.setBounds(20, 90, 100, 30);
	    	
	            txt1 = new TextField("Person Name");
	            txt1.setBounds(20, 120, 100, 30);  

	            txt2 = new TextField("City");
	            txt2.setBounds(20, 160, 80, 30);  

	            txtResult = new TextField("");
	            txtResult.setBounds(20, 350, 180, 30);  

	            btn = new Button("Submit Details");
	            btn.addActionListener(this);
	            btn.setBounds(20, 200, 150, 50);

	            add(header);
	            add(txt1);
	            add(txt2);
	            add(txtResult);
	            add(btn);
	             
	            setSize(500, 500);
	            setLayout(null);   
	            setVisible(true);  
	            addWindowListener(new WindowAdapter() { 
	                public void windowClosing(WindowEvent we) { 
	                    System.exit(0); 
	                } 
	            }); 
	    }

	 public void actionPerformed(ActionEvent e)    {
	        String t1 = txt1.getText();
	        String t2 = txt2.getText();
	        try {
	        loadDriver(dbdriver);
	        Connection conn = getConnection();
	        Statement stmt;
			
				stmt = conn.createStatement();
				 stmt.executeUpdate("insert into Person  "
				 		+ "(PersonId, PesonName, City) values (5, '" +t1+"', '"+t2+"'  )");
				 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       
	        
	        //int result = Integer.parseInt(t1) + Integer.parseInt(t2);
	       //System.out.println("result is "+  result);
	        txtResult.setText( "User added to Databse"); 
	     }

	public static void main(String[] args) {
		ConnectWithSwing obj = new ConnectWithSwing();
		
		
	}

	private String dburl = "jdbc:mysql://localhost:3306/adiDB";
	private String dbuname = "root";
	private String dbpassword = "Work@2024";
	private String dbdriver = "com.mysql.cj.jdbc.Driver";
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuname, dbpassword);
			System.out.println("Connectoin created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}


}
