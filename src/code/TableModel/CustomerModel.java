package code.TableModel;

import javax.swing.table.DefaultTableModel;

public class CustomerModel extends DefaultTableModel {

	/**
	 * own model that specific in each of column
	 */
	public CustomerModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return Integer.class;
			case 6:
				return String.class;
			default:
				return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
