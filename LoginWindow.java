import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {

    Connection conn = null;

    JTextField t1;
    JPasswordField p1;
    JButton b1;
    JLabel l1, l2;

    LoginWindow() {
        setTitle("Login");
        setLayout(null);

        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        t1 = new JTextField();
        p1 = new JPasswordField();
        b1 = new JButton("Login");

        add(l1);
        add(l2);
        add(t1);
        add(p1);
        add(b1);

        l1.setBounds(40, 40, 100, 20);
        l2.setBounds(40, 80, 100, 20);
        t1.setBounds(140, 40, 120, 25);
        p1.setBounds(140, 80, 120, 25);
        b1.setBounds(90, 130, 100, 30);

        // ✅ Correct event handling
        b1.addActionListener(this);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // ✅ Database connection
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/project1",
                "cmh1",
                "1234"
            );
            System.out.println("Connected to database!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "DB Connection Failed!");
            System.out.println(e);
        }
    }

    // ✅ Login Logic
    public void actionPerformed(ActionEvent ae) {

    String username = t1.getText();
    String password = new String(p1.getPassword());

    try {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM admin WHERE ausername=? AND apassword=?"
        );

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new AdminWindow();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}

    public static void main(String[] args) {
        new LoginWindow();
    }
}