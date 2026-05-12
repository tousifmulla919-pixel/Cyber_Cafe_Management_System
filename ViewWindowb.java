//import java.awt.BorderLayout;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewWindowb extends JFrame {


	/**
	 * Launch the application.
	 */
		private Connection con;

	/**
	 * Create the frame.
	 */
	public ViewWindowb() {
		try {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
          		
			JTable jt=new JTable();
			
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://localhost/project1","cmh1","1234");
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery("Select * from cbill order by bno");
			ResultSetMetaData rsmd=rs.getMetaData();
			int col=rsmd.getColumnCount();
			String header[]=new String[col];
			for (int i = 0; i < header.length; i++) 
                          header[i]=rsmd.getColumnLabel(i+1);
			DefaultTableModel dtm=new DefaultTableModel(null, header);
			
			int cnt=1;
			String data[]=new String[col];
			while (rs.next()) {
				for(int i=1;i<=col;i++)
                                data[i-1]=rs.getString(i);
				dtm.addRow(data);
			}
			jt.setModel(dtm);
			JScrollPane jsp=new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(jsp,BorderLayout.CENTER);
setVisible(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
		
					new ViewWindowb();
					//frame.setVisible(true);
			
	}



}
