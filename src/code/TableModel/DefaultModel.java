package code.TableModel;

import javax.swing.table.DefaultTableModel;

public class DefaultModel extends DefaultTableModel {
	private boolean edit;
	
	/**
	 * own model that specific in each of column
	 */
	public DefaultModel(Object[][] data, Object[] columnNames, boolean edit) {
		super(data, columnNames);
		this.edit = edit;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return edit;
	}
}
