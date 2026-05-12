import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class CreateAdmin extends JFrame implements ActionListener
{
JTextField t1,t2,t3,t4,t5,t6,t7,t8;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
JButton b1,b2;
JComboBox cb1,cb2,cb3;
JTextArea tx1;
String day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
String month[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
String year[]={"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"};
Connection conn=null;
PreparedStatement ps1=null;
CreateAdmin()
{
setLayout(null);


l1=new JLabel("Admin ID");
l1.setBounds(10,10,100,20);
l2=new JLabel("Name");
l2.setBounds(10,40,100,20);
l3=new JLabel("Address");
l3.setBounds(10,70,100,20);
l4=new JLabel("Phone");
l4.setBounds(10,120,100,20);
l5=new JLabel("Email");
l5.setBounds(10,150,100,20);
l6=new JLabel("Username");
l6.setBounds(10,180,100,20);
l7=new JLabel("Password");
l7.setBounds(10,210,100,20);
l8=new JLabel("Birthdate");
l8.setBounds(10,240,100,20);
l9=new JLabel("Age");
l9.setBounds(10,270,100,20);
add(l1);
add(l2);
add(l3);
add(l4);
add(l5);
add(l6);
add(l7);
add(l8);
add(l9);

t1=new JTextField(30);
t1.setBounds(120,10,150,20);
t2=new JTextField(30);
t2.setBounds(120,40,150,20);
tx1=new JTextArea(2,30);
tx1.setBounds(120,70,150,40);
//JScrollPane j=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//tx1.add(j);
t3=new JTextField(30);
t3.setBounds(120,120,150,20);
t4=new JTextField(30);
t4.setBounds(120,150,150,20);
t5=new JTextField(30);
t5.setBounds(120,180,150,20);
t6=new JTextField(30);
t6.setBounds(120,210,150,20);
t8=new JTextField(30);
t8.setBounds(120,240,150,20);
t7=new JTextField(30);
t7.setBounds(120,270,150,20);
add(t1);
add(t2);
add(tx1);
add(t3);
add(t4);
add(t5);
add(t6);
add(t8);
add(t7);

b1=new JButton("Okay");
b1.setBounds(120,300,100,20);
b2=new JButton("Cancel");
b2.setBounds(230,300,100,20);
add(b1);
b1.addActionListener(this);
add(b2);
b2.addActionListener(this);

setSize(384,384);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setVisible(true);
try
{
Class.forName("org.postgresql.Driver");
conn=DriverManager.getConnection("jdbc:postgresql://localhost/project1","cmh1","1234");
if(conn!=null)
System.out.println("Connection estabalished to db project!");
ps1=conn.prepareStatement("insert into admin values (?,?,?,?,?,?,?,(select to_date(?,'DD MM YYYY')),?)");
System.out.println(ps1);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent ae)
{
String name,phone,email,username,password,age,addr,bdate;
int id;
try
{
if(ae.getSource()==b1)
{
if(t1.getText().equals("") || t2.getText().equals("")  || t3.getText().equals("")  || t4.getText().equals("")  || t5.getText().equals("")  || t6.getText().equals("")  || t7.getText().equals("") )
JOptionPane.showMessageDialog(null,"All fields are mandatory to fill in order to proceed!","Warning",JOptionPane.WARNING_MESSAGE);

//if(t1.getText()!=null && t2.getText()!=null && t3.getText()!=null && t4.getText()!=null && t5.getText()!=null && t6.getText()!=null && t7.getText()!=null)
else
{
id=Integer.parseInt(t1.getText());
name=t2.getText();
phone=t3.getText();
email=t4.getText();
username=t5.getText();
password=t6.getText();
age=t7.getText();
bdate=t8.getText();
addr=tx1.getText();
ps1.setInt(1,id);
ps1.setString(2,name);
ps1.setString(3,addr);
ps1.setString(4,phone);
ps1.setString(5,email);
ps1.setString(6,username);
ps1.setString(7,password);
ps1.setString(8,bdate);
ps1.setString(9,age);
System.out.println(ps1);
ps1.executeUpdate();
JOptionPane.showMessageDialog(null,"Data added to database successfully!");
t1.setText("");
tx1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");
t6.setText("");
t7.setText("");
t8.setText("");
}

}
if(ae.getSource()==b2)
this.dispose();
}
catch (Exception e)
{
System.out.println(e);
}
}
public void itemStateChanged(ItemEvent ie)
{
}
public static void main(String args[])
{
new CreateAdmin();
}
}
