package gui.login;

import code.behavior.ButtonFactory;
import code.constant.FileLocation;
import code.customer.Customer;
import code.file.FileFactory;
import gui.customer.CustomerPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class LoginCust extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private JTextField userField;

    public LoginCust(JFrame page) {
        setContentPane(contentPane);
        setModal(true);

        // do action when user press button
        buttonOK.addActionListener(e -> onOK(page));
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when ESCAPE been press
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // call onOK() when ENTER been press
        contentPane.registerKeyboardAction(e -> onOK(page), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(JFrame page) {
        String user = userField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (checkAdmin(user, password)) {
            CustomerPage customer = new CustomerPage();
            customer.run(getLocation());

            page.dispose();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static ArrayList<Customer> checkUser() {
        ArrayList<Customer> temp = new ArrayList<>();

        // guest member
        temp.add(new Customer());

        FileFactory factory = new FileFactory();
        factory.setPath(FileLocation.CUSTOMER_PATH);
        String[][] allCustomer = factory.read(":");

        for (String[] customer : allCustomer) {
            if (customer.length == 7)
                temp.add(new Customer(customer[0], customer[1], customer[2], customer[3], customer[4], customer[5], customer[6]));
            else System.err.println("customer text-file error");
        }
        Customer.setNumCustomers(temp.size());

        return temp;
    }

    private void onCancel() {
        dispose();
    }

    private boolean checkAdmin(String user, String pass) {
        return user.equals("admin") && pass.equals("password");
    }

    public void run(Point point) {
        setMinimumSize(new Dimension(340, 240));
        pack();
        setLocation(point);
        setVisible(true);
    }
}
