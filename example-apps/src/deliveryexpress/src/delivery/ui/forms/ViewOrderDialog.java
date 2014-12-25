package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import delivery.business.Produto;
import delivery.business.frontage.Fachada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JTable;

public class ViewOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtCliente;
	private JTextField txtData;
	private JTextField txtValor;
	private JTable tblProdutos;
	private JLabel lblCodigo;
	private JLabel lblCliente;
	private JLabel lblData;
	private JLabel lblValor;
	private DefaultTableModel tableModelProdutos;

	public ViewOrderDialog(final Fachada fachada, String codigo) {
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(600, 500));
		setTitle("Visualizar pedido");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblCodigo = new JLabel("CÃ³digo:");
			GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
			gbc_lblCodigo.anchor = GridBagConstraints.EAST;
			gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigo.gridx = 0;
			gbc_lblCodigo.gridy = 0;
			contentPanel.add(lblCodigo, gbc_lblCodigo);
		}
		{
			txtCodigo = new JTextField();
			lblCodigo.setLabelFor(txtCodigo);
			txtCodigo.setEditable(false);
			GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
			gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
			gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCodigo.gridx = 1;
			gbc_txtCodigo.gridy = 0;
			contentPanel.add(txtCodigo, gbc_txtCodigo);
			txtCodigo.setColumns(10);
		}
		{
			lblCliente = new JLabel("Cliente:");
			GridBagConstraints gbc_lblCliente = new GridBagConstraints();
			gbc_lblCliente.anchor = GridBagConstraints.EAST;
			gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
			gbc_lblCliente.gridx = 0;
			gbc_lblCliente.gridy = 1;
			contentPanel.add(lblCliente, gbc_lblCliente);
		}
		{
			txtCliente = new JTextField();
			lblCliente.setLabelFor(txtCliente);
			txtCliente.setEditable(false);
			GridBagConstraints gbc_txtCliente = new GridBagConstraints();
			gbc_txtCliente.insets = new Insets(0, 0, 5, 0);
			gbc_txtCliente.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCliente.gridx = 1;
			gbc_txtCliente.gridy = 1;
			contentPanel.add(txtCliente, gbc_txtCliente);
			txtCliente.setColumns(10);
		}
		{
			JLabel lblProdutos = new JLabel("Produtos:");
			GridBagConstraints gbc_lblProdutos = new GridBagConstraints();
			gbc_lblProdutos.insets = new Insets(0, 0, 5, 5);
			gbc_lblProdutos.gridx = 0;
			gbc_lblProdutos.gridy = 2;
			contentPanel.add(lblProdutos, gbc_lblProdutos);
		}
		{

			String tblProdutosCols[] = { "Nome", "Descrição", "Tamanho",
					"Valor" };
			tableModelProdutos = new DefaultTableModel(null, tblProdutosCols);
			tblProdutos = new JTable(tableModelProdutos);
			tblProdutos.setAutoCreateColumnsFromModel(true);
			tblProdutos.setFocusable(false);
			tblProdutos.setRowHeight(36);
			tblProdutos.getTableHeader().setBackground(Color.lightGray);
			GridBagConstraints gbc_tblProdutos = new GridBagConstraints();
			gbc_tblProdutos.insets = new Insets(0, 0, 5, 0);
			gbc_tblProdutos.fill = GridBagConstraints.BOTH;
			gbc_tblProdutos.gridx = 1;
			gbc_tblProdutos.gridy = 2;
			contentPanel.add(new JScrollPane(tblProdutos), gbc_tblProdutos);
		}
		{
			lblData = new JLabel("Data:");
			GridBagConstraints gbc_lblData = new GridBagConstraints();
			gbc_lblData.anchor = GridBagConstraints.EAST;
			gbc_lblData.insets = new Insets(0, 0, 5, 5);
			gbc_lblData.gridx = 0;
			gbc_lblData.gridy = 3;
			contentPanel.add(lblData, gbc_lblData);
		}
		{
			txtData = new JTextField();
			lblData.setLabelFor(txtData);
			txtData.setEditable(false);
			GridBagConstraints gbc_txtData = new GridBagConstraints();
			gbc_txtData.insets = new Insets(0, 0, 5, 0);
			gbc_txtData.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtData.gridx = 1;
			gbc_txtData.gridy = 3;
			contentPanel.add(txtData, gbc_txtData);
			txtData.setColumns(10);
		}
		{
			lblValor = new JLabel("Valor:");
			GridBagConstraints gbc_lblValor = new GridBagConstraints();
			gbc_lblValor.anchor = GridBagConstraints.EAST;
			gbc_lblValor.insets = new Insets(0, 0, 0, 5);
			gbc_lblValor.gridx = 0;
			gbc_lblValor.gridy = 4;
			contentPanel.add(lblValor, gbc_lblValor);
		}
		{
			txtValor = new JTextField();
			lblValor.setLabelFor(txtValor);
			txtValor.setEditable(false);
			GridBagConstraints gbc_txtValor = new GridBagConstraints();
			gbc_txtValor.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtValor.gridx = 1;
			gbc_txtValor.gridy = 4;
			contentPanel.add(txtValor, gbc_txtValor);
			txtValor.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		txtCodigo.setText(codigo);
		txtCliente.setText(fachada.retornaCliente(
				fachada.retornaPedido(codigo).getTelefoneCliente()).getNome());
		txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(fachada
				.retornaPedido(codigo).getDataPedido()));
		txtValor.setText(fachada.retornaPedido(codigo).getValor().toString());

		for (Produto produto : fachada.retornaPedido(codigo).getProdutos()) {
			tableModelProdutos.insertRow(this.tblProdutos.getRowCount(),
					new Object[] { produto.getNome(), produto.getDescricao(),
							produto.getTamanho(), produto.getValor() });
		}

	}
}
