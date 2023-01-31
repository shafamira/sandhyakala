package gui.login;

import code.TableModel.CustomerModel;
import code.constant.FileLocation;
import code.customer.Customer;
import code.customer.Person;
import code.product.ProductExt;
import code.store.Store;
import code.file.FileFactory;
import gui.customer.AdderCustomerPage;
import gui.main.MainPage;
import gui.customer.CustomerPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class LoginPage extends JFrame {
    private static FileFactory factory = new FileFactory();
    private static Store store = assignStore();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private JTextField userField;
    private JButton ButtonAdd;

    private JFrame page;

    private Person shopper = new Person();

    private CustomerModel model;

    public LoginPage() {
        super("Login Page");
        setContentPane(contentPane);

        // do action when user press button
        buttonOK.addActionListener(e -> onOK(page));
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when ESCAPE been press
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // call onOK() when ENTER been press
        contentPane.registerKeyboardAction(e -> onOK(page), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        add();

    }

    public static void reWriteCustomer() {
        factory.setPath(FileLocation.CUSTOMER_PATH);
        factory.write(store.getAllCustomer());
    }

    private static ArrayList<ProductExt> assignProduct() {
        ArrayList<ProductExt> temp = new ArrayList<>();

        factory.setPath(FileLocation.PRODUCT_PATH);
        String[][] allProduct = factory.read(":");

        for (String[] product : allProduct) {
            if (product.length == 6)
                temp.add(new ProductExt(product[0], product[1], product[2], product[3], product[4], product[5]));
            else System.err.println("product text-file error");
        }
        return temp;
    }

    private static ArrayList<Customer> assignCustomer() {
        ArrayList<Customer> temp = new ArrayList<>();

        // guest member
        temp.add(new Customer());

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

    private static Store assignStore() {
        ArrayList<ProductExt> productList = assignProduct();
        ArrayList<Customer> customerList = assignCustomer();

        Store temp = null;
        factory.setPath(FileLocation.STORE_PATH);
        String[][] informations = factory.read(":");
        for (String[] info : informations) {
            temp = Store.getInstance(productList, customerList, info[0]);
        }

        return temp;
    }

    private void onOK(JFrame page) {
        String user = userField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (checkUser(user, password)) {
            MainPage.shopper = getCust(user);
            MainPage main = new MainPage();
            main.run(getLocation());

            dispose();
        } else if (checkAdmin(user, password)) {
            MainPage main = new MainPage();
            main.run(getLocation());

            dispose();
        }else {
            JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void add() {
        CustomerPage custPage = new CustomerPage();
        ButtonAdd.addActionListener(e -> {
            AdderCustomerPage adderPage = new AdderCustomerPage();
            adderPage.run(getLocation());
            if (adderPage.getNewCustomer() != null) {
                custPage.model.addRow(adderPage.getNewCustomer().getCustomerInfo(7));
            }
            custPage.reTable();
        });
    }

    private void onCancel() {
        System.exit(0);
        dispose();
    }

    private boolean checkAdmin(String admin, String passadmin) {
        return admin.equals("admin") && passadmin.equals("pass");
    }

    private boolean checkUser(String user, String pass){
        return store.searchCustUsernamePassword(user, pass);
    }

    private Customer getCust(String user) {
        return store.searchCustomerUsername(user);
    }

    public void run(Point point) {
        setMinimumSize(new Dimension(340, 240));
        pack();
        setLocation(point);
        setVisible(true);
    }
}
