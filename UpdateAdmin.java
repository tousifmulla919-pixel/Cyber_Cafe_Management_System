import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class UpdateAdmin extends JFrame implements ActionListener
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
UpdateAdmin()
{
setLayout(null);

//aaddr aphone aemail apassword
l1=new JLabel("Address");
l1.setBounds(10,10,100,20);
l2=new JLabel("Phone");
l2.setBounds(10,60,100,20);
l3=new JLabel("Email");
l3.setBounds(10,90,100,20);
l4=new JLabel("Password");
l4.setBounds(10,120,100,20);
add(l1);
add(l2);
add(l3);
add(l4);
/*add(l5);
add(l6);                                 a,b,c,d	abcd,	abc bcd acd abd	,, ab ac ad bc bc cd 
add(l7);
add(l8);
add(l9);*/

tx1=new JTextArea(2,30);
tx1.setBounds(120,10,150,40);
t1=new JTextField(30);
t1.setBounds(120,60,150,20);
t2=new JTextField(30);
t2.setBounds(120,90,150,20);

//JScrollPane j=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//tx1.add(j);
t3=new JTextField(30);
t3.setBounds(120,120,150,20);
/*t4=new JTextField(30);
t4.setBounds(120,150,150,20);
t5=new JTextField(30);
t5.setBounds(120,180,150,20);
t6=new JTextField(30);
t6.setBounds(120,210,150,20);
t7=new JTextField(30);
t7.setBounds(120,270,150,20);*/
add(tx1);
add(t1);
add(t2);
add(t3);
/*
add(t4);
add(t5);
add(t6);
add(t7);*/

b1=new JButton("Okay");
b1.setBounds(120,150,100,20);
b2=new JButton("Cancel");
b2.setBounds(230,150,100,20);
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
ps1=conn.prepareStatement("update admin set address=?,phone=?,email=?,password=?");
System.out.println(ps1);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void actionPerformed(ActionEvent ae)
{
String name,phone,email,username,password,age,address;
int id;
try
{
if(ae.getSource()==b1)

{
address=tx1.getText();
phone=t1.getText();
email=t2.getText();
//username=t5.getText();
password=t3.getText();
//if(address!=null && phone==null && email==null && password==null)
//ps=conn.prepareStatement("update admin set address = ? where ");
//age=t7.getText();
//System.out.println(ps1);
//ps1.executeUpdate();
//JOptionPane.showMessageDialog(null,"Data added to database successfully!");
}


if(ae.getSource()==b2)
{
this.dispose();
}
}
catch (Exception e)
{
System.out.println(e);
}
}
public static void main(String args[])
{
new UpdateAdmin();
}
}
