package gui.store;

import code.TableModel.ProductModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.product.ProductExt;
import code.store.Store;
import gui.history.HistoryPage;
import gui.main.MainPage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class StorePage extends JFrame implements Table, ButtonFactory {
	private Store store = Store.getInstance();

	private JPanel panel;
	private JTextField searchField;
	private JTable table;
	private JButton mainButton;
	private JButton restockButton;
	private JButton historyButton;
	private JLabel revenueLabel;

	private ProductModel model;

	public StorePage() {
		super("Store Page");
		setContentPane(panel);
		model = new ProductModel(store.getAllProduct(true), new String[]{"ID", "Name", "Type", "Size", "In Stock", "Restock", "Price"});

		settingTable();

		// assign button
		toMain(this, mainButton);
		restockButton.addActionListener(e -> restock());
		history();

		updateLabel(store.getRevenue());
	}

	private void settingTable() {
		table.setModel(model);
		settingTable(table, new int[]{50,100, 190, 80, 180, 80, 80});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				// 10 mean enter
				if (e.getKeyCode() == 10) {
					restock();
				}
			}
		});

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	private void restock() {
		int row = table.getSelectedRow();

		if (checkValid(row)) {
			ProductExt product = getProductAt(row);

			if (product.isRestock()) {
				store.setRestockProduct(false);
				updateTable(5, row, 4);
				updateTable(0, row, 5);
			} else {
				int choose = JOptionPane.showConfirmDialog(null, "Are you sure?", "Info", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					store.setRestockProduct(false);
					updateTable(5, row, 4);
					updateTable(0, row, 5);
				}
			}
		}
	}

	private void history() {
		historyButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (checkValid(row)) {
				HistoryPage page = new HistoryPage(getProductAt(row));
				page.run(getWidth(), getLocation());
			}
			resetSelection(table);
		});
	}

	private ProductExt getProductAt(int row) {
		String productID = String.valueOf(table.getValueAt(row, 0));
		return store.searchProductID(productID);
	}

	private void updateLabel(double revenue) {
		revenueLabel.setText("Revenue: Rp " + revenue);
	}

	private void updateTable(int newValue, int row, int column) {
		table.setValueAt(newValue, row, column);
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(970, 500));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
