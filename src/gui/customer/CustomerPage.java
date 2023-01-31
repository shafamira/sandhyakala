package gui.customer;

import code.TableModel.CustomerModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.customer.Customer;
import code.store.Store;
import gui.history.HistoryPage;
import gui.main.MainPage;
import gui.shopping.ShoppingPage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class CustomerPage extends JFrame implements Table, ButtonFactory {
	private Store store = Store.getInstance();
	private JPanel panel;
	private JTextField searchField;
	private JTable table;
	private JButton mainButton;
	private JButton removeButton;
	private JButton historyButton;

	public CustomerModel model;

	public CustomerPage() {
		super("Customer Page");
		setContentPane(panel);
		model = new CustomerModel(store.getAllCustomer(), new String[]{"Username", "Password", "First Name", "Last Name", "Gender", "Age", "Class"});

		settingTable();


//		selectButton.addActionListener(e -> {
//			int row = table.getSelectedRow();
//			if (checkValid(row)) {
//				MainPage.shopper = getCustomerAt(row);
//				openShoppingPage();
//			}
//		});

		history();
		remove();

		toMain(this, mainButton);
	}

	private void settingTable() {
		table.setModel(model);
		settingTable(table, new int[]{80, 80, 100, 120, 50, 50, 75});

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	public void reTable(){
		resetSelection(table);
	}

//	private void openShoppingPage() {
//		ShoppingPage shopping = new ShoppingPage();
//		shopping.run(getLocation());
//		dispose();
//		resetSelection(table);
//	}

	private void remove() {
		removeButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (checkValid(row)) {
				int customerIndex = store.removeCustomer(getCustomerAt(row), false);
				model.removeRow(customerIndex);
				MainPage.reWriteCustomer();
			}
			resetSelection(table);
		});
	}

	private void history() {
		historyButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			HistoryPage history = new HistoryPage(getCustomerAt(table.getSelectedRow()));
			history.run(getWidth(), getLocation());
		});
	}

	private Customer getCustomerAt(int row) {
		if (row != -1) {
			String uname = String.valueOf(table.getValueAt(row, 0));
			return store.searchCustomerUsername(uname);
		}
		return store.getGuest();
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(650, 530));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void createUIComponents() {
		// make table fit size by contact
		table = fitSize(table);
	}
}
