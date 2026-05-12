
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import javax.swing.*;

public class AdminWindow extends JFrame implements ActionListener
{
JMenuBar menuBar;
JMenu employeeMenu,memberMenu,customerMenu,billMenu;
JMenuItem create,delete,update,view,create1,delete1,update1,view1,nbill,vbill;
JButton b1,b2,b3;
JPanel p1,p2;
String id1;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
JTextField t1,t2,t3,t4,t5,t6,t7;
int id;
Connection conn =null;
Statement stmt=null;
PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null,ps11=null,ps12=null,ps13=null,ps14=null,ps15=null,ps16=null,ps17=null,ps18=null;
ResultSet rs = null,rs1=null,rs2=null,rs3=null;

AdminWindow()
{
Toolkit kit=Toolkit.getDefaultToolkit();
Dimension ScreenSize=kit.getScreenSize();
int screenHeight=ScreenSize.height;
System.out.println(screenHeight);
int screenWidth=ScreenSize.width;
BackgroundPanel bgPanel = new BackgroundPanel("bg.jpg");
setContentPane(bgPanel);
setLayout(new BorderLayout());
setSize(screenWidth/2,screenHeight/2);
setLocationByPlatform(true);
setTitle("Welcome Admin");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
menuBar=new JMenuBar();
employeeMenu=new JMenu("Employee");
memberMenu=new JMenu("Member");
customerMenu=new JMenu("Customer");
billMenu=new JMenu("Bill");

menuBar.add(employeeMenu);
create=new JMenuItem("Create");
create.addActionListener(this);
update=new JMenuItem("Update");
update.addActionListener(this);
delete=new JMenuItem("Delete");
delete.addActionListener(this);
view=new JMenuItem("View");
view.addActionListener(this);
employeeMenu.add(create);
employeeMenu.add(update);
employeeMenu.add(delete);
employeeMenu.addSeparator();
employeeMenu.add(view);

//menuBar.add(memberMenu);


menuBar.add(customerMenu);
create1=new JMenuItem("Create");
create1.addActionListener(this);
update1=new JMenuItem("Update");
update1.addActionListener(this);
delete1=new JMenuItem("Delete");
delete1.addActionListener(this);
view1=new JMenuItem("View");
view1.addActionListener(this);
customerMenu.add(create1);
customerMenu.add(update1);
customerMenu.add(delete1);
customerMenu.addSeparator();
customerMenu.add(view1);

menuBar.add(billMenu);
nbill=new JMenuItem("New");
nbill.addActionListener(this);
vbill=new JMenuItem("View");
vbill.addActionListener(this);
billMenu.add(nbill);
billMenu.add(vbill);

setJMenuBar(menuBar);
l1=new JLabel("Customer name : ");
l2=new JLabel("Customer name : ");
l3=new JLabel("Customer username : ");
l4=new JLabel("Customer username : ");
l5=new JLabel("Total time : ");
l6=new JLabel("Total time : ");
l7=new JLabel("Total bill : ");
l8=new JLabel("Total bill : ");
p1=new JPanel();
p1.setLayout(new GridLayout(3,2,1,1));
p1.add(l1);
p1.add(l2);
//p1.add(l3);
//p1.add(l4);
p1.add(l5);
p1.add(l6);
p1.add(l7);
p1.add(l8);
p2=new JPanel(new GridLayout(7,2,1,1));
l10=new JLabel("Enter bill number : ");
l9=new JLabel("Enter username : ");
l11=new JLabel("Enter login time : ");
l12=new JLabel("Enter logout time : ");
l13=new JLabel("Membership purchased : ");
l14=new JLabel("Membership ID : ");
t1=new JTextField(20);
t2=new JTextField(20);
t3=new JTextField(20);
t4=new JTextField(20);
t5=new JTextField(20);
t6=new JTextField(20);
//t6.getDocument().addDocumentListener(this);
t7=new JTextField(20);
//t2.setEditable(false);
//t3.setEditable(false);
//t4.setEditable(false);
//t5.setEditable(false);
b2=new JButton("Okay");
p2.add(l10);
p2.add(t2);
p2.add(l9);
p2.add(t1);
p2.add(l11);
p2.add(t3);
p2.add(l12);
p2.add(t4);
p2.add(l13);
p2.add(t6);
p2.add(b2);
b2.addActionListener(this);


try
{
Class.forName("org.postgresql.Driver");
conn=DriverManager.getConnection("jdbc:postgresql://localhost/project1","cmh1","1234");
if(conn!=null)
System.out.println("Connection estabalished!");
ps=conn.prepareStatement("delete from admin where aid = ?");
//stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

ps1=conn.prepareStatement("update admin set aaddr=?,aphone=?,aemail=?,apassword=? where aid=? ");
ps2=conn.prepareStatement("Select aaddr,aphone,aemail,apassword from admin where aid = ?");


ps3=conn.prepareStatement("delete from customer where cusername = ?");
//stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

ps4=conn.prepareStatement("update customer set mid=? ,caddr=?,cphone=?,cemail=?,cpass=? where cusername=? ");
ps5=conn.prepareStatement("Select mid,caddr,cphone,cemail,cpass from customer where cusername = ?");

ps6=conn.prepareStatement("insert into cbill values(?,?,(select TO_TIMESTAMP(?,'HH12:MI:SS')::TIME),(select TO_TIMESTAMP(?,'HH12:MI:SS')::TIME))");
ps7=conn.prepareStatement("update cbill set total_time=logout_time-login_time where bno=?");
ps8=conn.prepareStatement("update cbill set total_bill=(select extract(epoch from (total_time::time))/extract(epoch from ('01:00:00'::time)) *30 from cbill where bno=?)where bno=?");
System.out.println(ps8);
ps9=conn.prepareStatement("select * from cbill where bno = ?");
ps10=conn.prepareStatement("select * from customer where cusername = ?");
ps11=conn.prepareStatement("update cbill set membership_purchased=? where bno=?");
ps12=conn.prepareStatement("update cbill set mid=? where bno=?");
ps13=conn.prepareStatement("update cbill set remaining_hrs = (remaining_hrs - (select extract(epoch from (total_time::time))/extract(epoch from ('01:00:00'::time))))where bno=?");
ps14=conn.prepareStatement("update cbill set remaining_hrs = (membership.mhours - (select extract(epoch from (total_time::time))/extract(epoch from ('01:00:00'::time))where membership.mid=cbill.mid))from membership,customer where bno=?");
ps15=conn.prepareStatement("select * from cbill where cusername = ? order by bno",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ps16=conn.prepareStatement("update cbill set remaining_hrs=?-(select extract(epoch from (total_time::time))/extract (epoch from('01:00:00'::time)))where bno=?");
ps17=conn.prepareStatement("select * from membership where mid=?");
ps18=conn.prepareStatement("update customer set mid=null where cusername=?");
}

catch(Exception e)
{
System.out.println(e);
}

}
public void actionPerformed(ActionEvent ae)
{
int id,mid,bno;
String aaddr,aphone,aemail,apassword,id1;
String mid1,caddr,cphone,cemail,cpass,cusername;
String cus,time1,time2;
try
{
if(ae.getSource()==create)
{
new CreateAdmin();

}
if(ae.getSource()==delete)
{


id1=JOptionPane.showInputDialog(null,"Enter the id");
id=Integer.parseInt(id1);

ps.setInt(1,id);
ps.executeUpdate();
JOptionPane.showMessageDialog(null,"Record deleted successfully!");

}
if(ae.getSource()==update)
{
id1=JOptionPane.showInputDialog(null,"Enter the id");
id=Integer.parseInt(id1);
ps2.setInt(1,id);
rs=ps2.executeQuery();

rs.next();
System.out.println(rs.getString("aaddr"));
System.out.println(rs.getString("aemail"));
System.out.println(rs.getString("aphone"));
System.out.println(rs.getString("apassword"));

aaddr=JOptionPane.showInputDialog(null,"Enter new address",rs.getString("aaddr"));
aphone=JOptionPane.showInputDialog(null,"Enter new phone ",rs.getString("aphone"));
aemail=JOptionPane.showInputDialog(null,"Enter new email",rs.getString("aemail"));
apassword=JOptionPane.showInputDialog(null,"Enter new password",rs.getString("apassword"));
ps1.setString(1,aaddr);
ps1.setString(2,aphone);
ps1.setString(3,aemail);
ps1.setString(4,apassword);
ps1.setInt(5,id);
ps1.executeUpdate();
JOptionPane.showMessageDialog(null,"Requested data updated successfully!");
}
if(ae.getSource()==view)
{
System.out.println("in view");
new ViewWindow();
}
if(ae.getSource()==create1)
{
new CreateCustomer();
}
if(ae.getSource()==view1)
{
new ViewWindowc();
}
if(ae.getSource()==delete1)
{


cusername=JOptionPane.showInputDialog(null,"Enter the username");
//id=Integer.parseInt(id1);

ps3.setString(1,cusername);
ps3.executeUpdate();
JOptionPane.showMessageDialog(null,"Record deleted successfully!");
}
if(ae.getSource()==update1)
{
cusername=JOptionPane.showInputDialog(null,"Enter the username");
//id=Integer.parseInt(id1);
ps5.setString(1,cusername);
rs=ps5.executeQuery();

rs.next();
System.out.println(rs.getInt("mid"));
//mid=Integer.parseInt(mid);
System.out.println(rs.getString("caddr"));
System.out.println(rs.getString("cemail"));
System.out.println(rs.getString("cphone"));
System.out.println(rs.getString("cpass"));

mid1=JOptionPane.showInputDialog(null,"Enter new membership id",rs.getInt("mid"));
caddr=JOptionPane.showInputDialog(null,"Enter new address",rs.getString("caddr"));
cphone=JOptionPane.showInputDialog(null,"Enter new phone ",rs.getString("cphone"));
cemail=JOptionPane.showInputDialog(null,"Enter new email",rs.getString("cemail"));
cpass=JOptionPane.showInputDialog(null,"Enter new password",rs.getString("cpass"));
mid=Integer.parseInt(mid1);
ps4.setInt(1,mid);
ps4.setString(2,caddr);
ps4.setString(3,cphone);
ps4.setString(4,cemail);
ps4.setString(5,cpass);
ps4.setString(6,cusername);
ps4.executeUpdate();
JOptionPane.showMessageDialog(null,"Requested data updated successfully!");
}
if(ae.getSource()==nbill)
{
add(p2,BorderLayout.NORTH);
p2.revalidate();
p2.validate();
}
if(ae.getSource()==b2)
{
System.out.println(t6.getText());
if(t6.getText().equals("NO"))
{
String bno1=t2.getText();
bno=Integer.parseInt(bno1);
cus=t1.getText();
time1=t3.getText();
time2=t4.getText();
ps6.setInt(1,bno);
ps6.setString(2,cus);
ps6.setString(3,time1);
ps6.setString(4,time2);
ps6.executeUpdate();
ps7.setInt(1,bno);
ps7.executeUpdate();

ps8.setInt(1,bno);
ps8.setInt(2,bno);
ps8.executeUpdate();
ps9.setInt(1,bno);
rs=ps9.executeQuery();
ps10.setString(1,cus);
rs1=ps10.executeQuery();
rs1.next();
rs.next();
l2.setText(rs1.getString("cname"));
SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
java.util.Date b=rs.getTime("total_time");
String bval=df.format(b);
l6.setText(bval);
float a=rs.getFloat("total_bill");
String aval=String.valueOf(a);
l8.setText(aval);
//System.out.println(rs.getTime("total_time"));
//System.out.println(rs.getFloat("total_bill"));
add(p1,BorderLayout.SOUTH);
p1.revalidate();
p1.validate();
}
if(t6.getText().equals("YES"))
{
String bno1=t2.getText();
bno=Integer.parseInt(bno1);
cus=t1.getText();
time1=t3.getText();
time2=t4.getText();
String ms=t6.getText();
ps6.setInt(1,bno);
ps6.setString(2,cus);
ps6.setString(3,time1);
ps6.setString(4,time2);
ps6.executeUpdate();
ps7.setInt(1,bno);
ps7.executeUpdate();
mid1=JOptionPane.showInputDialog(null,"Enter the membership id");
mid=Integer.parseInt(mid1);
ps15.setString(1,cus);
rs=ps15.executeQuery();
rs.next();

//rs.first();
//rs.next();
System.out.println(rs.getString("cusername"));
ps11.setString(1,ms);
ps11.setInt(2,bno);
ps11.executeUpdate();
ps12.setInt(1,mid);
ps12.setInt(2,bno);
ps12.executeUpdate();
System.out.println(ps12);
ps17.setInt(1,mid);
rs1=ps17.executeQuery();
rs1.next();
float mhrs=rs1.getFloat("mhours");
System.out.println(mhrs);
System.out.println(rs.getString("cusername"));
if(!rs.next())
{
//ps14.setInt(1,bno);
//ps14.executeUpdate();
//rs=ps9.executeQuery();

ps16.setFloat(1,mhrs);
ps16.setInt(2,bno);
System.out.println(ps16);
ps16.executeUpdate();

}
else
{
rs.last();
rs.previous();
//System.out.println(rs.getString("cusername"));
//System.out.println(rs.getFloat("remaining_hrs"));
float hrs=rs.getFloat("remaining_hrs");
System.out.println(hrs);
ps16.setFloat(1,hrs);
ps16.setInt(2,bno);
System.out.println(ps16);
ps16.executeUpdate();
//rs=ps9.executeQuery();
}
/*ps8.setInt(1,bno);
ps8.setInt(2,bno);
ps8.executeUpdate();
ps9.setInt(1,bno);
*/
ps10.setString(1,cus);
rs2=ps10.executeQuery();
rs2.next();
//rs.next();
ps9.setInt(1,bno);
rs3=ps9.executeQuery();
rs3.next();
l2.setText(rs2.getString("cname"));
SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
java.util.Date b=rs3.getTime("total_time");
String bval=df.format(b);
l6.setText(bval);
l7.setText("Remaining Membership Hours:");
float a=rs3.getFloat("remaining_hrs");
String aval=String.valueOf(a);
l8.setText(aval);
if(a<=0)
{
float add_bill=(-1*a*30);
JOptionPane.showInputDialog(null,"Your membership has ended.Contact the cafe administrator to renew it.Charges for additional usage : ",add_bill);
ps18.setString(1,cus);
ps18.executeUpdate();
}
//System.out.println(rs.getTime("total_time"));
//System.out.println(rs.getFloat("total_bill"));
add(p1,BorderLayout.SOUTH);
p1.revalidate();
p1.validate();

}
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");
t6.setText("");
}
if(ae.getSource()==vbill)
{
new ViewWindowb();
}
}
catch (Exception e)
{
System.out.println(e);
}
}
/*public void changedUpdate(DocumentEvent de)
{
addele();
}
public void insertUpdate(DocumentEvent de)
{
addele();
}
public void removeUpdate(DocumentEvent de)
{
addele();
}
public void addele()
{
if(t6.getText()=="YES")
{
p2.add(l14);
p2.add(t7);
p2.revalidate();
p2.validate();
}
if(t6.getText()=="NO")
{
p2.revalidate();
p2.validate();
}
}*/

public static void main(String args[])
{
new AdminWindow();
}
}

class BackgroundPanel extends JPanel {
    private Image image;

    BackgroundPanel(String imagePath) {
        try {
            image = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            System.out.println("Image not found: " + e);
        }
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
