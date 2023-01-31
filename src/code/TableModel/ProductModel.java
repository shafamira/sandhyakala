package code.TableModel;

import javax.swing.table.DefaultTableModel;

public class ProductModel extends DefaultTableModel{

	/**
	 * own model that specific in each of column
	 */
	public ProductModel(Object[][] data, Object[] columnNames) {
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
				return Integer.class;
			case 4:
				return Integer.class;
			case 5:
				return Double.class;
			default:
				return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
