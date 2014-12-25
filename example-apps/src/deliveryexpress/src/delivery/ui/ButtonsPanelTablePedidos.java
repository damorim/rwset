package delivery.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import delivery.business.frontage.Fachada;
import delivery.ui.forms.ViewOrderDialog;

class ButtonsPanelPedidos extends JPanel {

	private static final long serialVersionUID = 1L;
	public final java.util.List<JButton> buttons = java.util.Arrays.asList(
			new JButton("Visualizar"), new JButton("Deletar"));

	public ButtonsPanelPedidos() {
		super();
		setOpaque(true);
		for (JButton b : buttons) {
			b.setFocusable(false);
			b.setRolloverEnabled(false);
			add(b);
		}
	}
	// @Override public void updateUI() {
	// super.updateUI();
	// }
}

class ButtonsRendererPedidos extends ButtonsPanelPedidos implements
		TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonsRendererPedidos() {
		super();
		setName("Table.cellRenderer");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		this.setBackground(isSelected ? table.getSelectionBackground() : table
				.getBackground());
		return this;
	}
}

class ButtonsEditorPedidos extends ButtonsPanelPedidos implements
		TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonsEditorPedidos(final JTable table, final MainWindow mw,
			final Fachada fachada) {
		super();

		// ---->
		// DEBUG: view button click -> control key down + edit button(same cell)
		// press -> remain selection color
		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ButtonModel m = ((JButton) e.getSource()).getModel();
				if (m.isPressed() && table.isRowSelected(table.getEditingRow())
						&& e.isControlDown()) {
					setBackground(table.getBackground());
				}
			}
		};
		buttons.get(0).addMouseListener(ml);
		buttons.get(1).addMouseListener(ml);

		buttons.get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.convertRowIndexToModel(table.getEditingRow());
				ViewOrderDialog dialog = new ViewOrderDialog(fachada, table
						.getModel().getValueAt(row, 0).toString());
				dialog.setVisible(true);
			}
		});
		buttons.get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.convertRowIndexToModel(table.getEditingRow());
				Object codigo = table.getModel().getValueAt(row, 0);
				fireEditingStopped();
				if (JOptionPane.showConfirmDialog(table,
						"Deseja realmente deletar o pedido de c�digo " + codigo
								+ "?", "Confirma��o de exclusão",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					fachada.removerPedido(codigo.toString());
					mw.atualizarTabelaPedidos(fachada.filtrarPedidos(
							mw.getTxtFiltroPedidosInicial(),
							mw.getTxtFiltroPedidosFinal()));
					mw.setTxtFiltroPedidosInicial(mw
							.getTxtFiltroPedidosInicial());
					mw.setTxtFiltroPedidosFinal(mw.getTxtFiltroPedidosFinal());
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.setBackground(table.getSelectionBackground());
		return this;
	}

	@Override
	public Object getCellEditorValue() {
		return "";
	}

	// Copid from AbstractCellEditor
	// protected EventListenerList listenerList = new EventListenerList();
	transient protected ChangeEvent changeEvent = null;

	@Override
	public boolean isCellEditable(java.util.EventObject e) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(java.util.EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		fireEditingStopped();
		return true;
	}

	@Override
	public void cancelCellEditing() {
		fireEditingCanceled();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		listenerList.add(CellEditorListener.class, l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		listenerList.remove(CellEditorListener.class, l);
	}

	public CellEditorListener[] getCellEditorListeners() {
		return listenerList.getListeners(CellEditorListener.class);
	}

	protected void fireEditingStopped() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener) listeners[i + 1])
						.editingStopped(changeEvent);
			}
		}
	}

	protected void fireEditingCanceled() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener) listeners[i + 1])
						.editingCanceled(changeEvent);
			}
		}
	}
}
