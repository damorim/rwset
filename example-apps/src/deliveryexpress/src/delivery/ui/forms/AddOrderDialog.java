package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

import delivery.business.Cliente;
import delivery.business.Pedido;
import delivery.business.Produto;
import delivery.business.exceptions.client.ClienteNaoCadastradoException;
import delivery.business.exceptions.client.TelefoneObrigatorioException;
import delivery.business.exceptions.requests.DataObrigatorioException;
import delivery.business.exceptions.requests.ProdutosObrigatorioException;
import delivery.business.exceptions.requests.QuantidadeInvalidaException;
import delivery.business.frontage.Fachada;
import delivery.ui.MainWindow;

public class AddOrderDialog extends JDialog {

	private static final long serialVersionUID = -7001864889619738563L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblProdutos;
	private JLabel lblCliente;
	private JLabel lblProdutos;
	private JLabel lblValorLabel;
	private JLabel lblValor;
	private JLabel lblDesconto;
	private JLabel lblTotal;
	@SuppressWarnings("rawtypes")
	private JComboBox cbClientes;

	private double valor;
	private double frete;
	private double taxaDesconto;
	private double total;

	private HashMap<String, String> clienteTelefoneMap;
	private DefaultTableModel tableModelProdutos;
	private ArrayList<Produto> produtosSelecionados = new ArrayList<Produto>();

	private JLabel lblData;
	private JTextField txtData;

	private Fachada fachada;

	public ArrayList<String> bairrosDesconto = new ArrayList<String>();
	private JLabel lblNewLabel;
	private JLabel lblFreteLabel;
	private JLabel lblFrete;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddOrderDialog(final MainWindow mw, final Fachada fachada) {
		this.fachada = fachada;
		this.valor = 0;
		this.frete = 0;
		this.taxaDesconto = 0;
		setModal(true);
		setMinimumSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Novo pedido");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		/*
		 * BAIRROS COM DESCONTO
		 */
		bairrosDesconto.add("Aflitos");
		bairrosDesconto.add("Gra�as");
		bairrosDesconto.add("Espinheiro");
		bairrosDesconto.add("Tamarineira");
		bairrosDesconto.add("Jaqueira");
		bairrosDesconto.add("Torre");
		bairrosDesconto.add("Parnamirim");
		bairrosDesconto.add("Casa Forte");
		bairrosDesconto.add("Apipucos");
		bairrosDesconto.add("Casa Amarela");
		bairrosDesconto.add("Hip�dromo");
		bairrosDesconto.add("Rosarinho");
		bairrosDesconto.add("Campo Grande");
		bairrosDesconto.add("Torr�es");

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		valor = 0.0;
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblCliente = new JLabel("Cliente:");
			GridBagConstraints gbc_lblCliente = new GridBagConstraints();
			gbc_lblCliente.anchor = GridBagConstraints.EAST;
			gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
			gbc_lblCliente.gridx = 0;
			gbc_lblCliente.gridy = 0;
			contentPanel.add(lblCliente, gbc_lblCliente);
		}
		{
			cbClientes = new JComboBox();
			ArrayList<Cliente> clientes = fachada.filtrarClientes("");
			clienteTelefoneMap = new HashMap<String, String>();
			for (int i = 0; i < clientes.size(); i++) {
				clienteTelefoneMap.put(clientes.get(i).getNome(),
						clientes.get(i).gettelefone());
				cbClientes.addItem(clientes.get(i).getNome());
			}
			cbClientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calculaFrete(clienteTelefoneMap.get(((JComboBox) e
							.getSource()).getSelectedItem().toString()));
				}
			});
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			contentPanel.add(cbClientes, gbc_comboBox);
		}
		{
			lblProdutos = new JLabel("Produtos:");
			GridBagConstraints gbc_lblProdutos = new GridBagConstraints();
			gbc_lblProdutos.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblProdutos.insets = new Insets(0, 0, 5, 5);
			gbc_lblProdutos.gridx = 0;
			gbc_lblProdutos.gridy = 1;
			contentPanel.add(lblProdutos, gbc_lblProdutos);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				String tblProdutosCols[] = { "X", "C�digo", "Nome",
						"Descri��o", "Tamanho", "Valor" };
				tableModelProdutos = new DefaultTableModel(null,
						tblProdutosCols);
				tblProdutos = new JTable(tableModelProdutos);
				tblProdutos.setAutoCreateColumnsFromModel(true);
				tblProdutos.setFocusable(false);
				tblProdutos.setRowHeight(36);
				tblProdutos.getTableHeader().setBackground(Color.lightGray);
				tblProdutos.getColumn("X").setCellRenderer(
						new BooleanCellRenderer());
				tblProdutos.getColumn("X").setCellEditor(
						new BooleanCellEditor(tblProdutos, this, fachada));
				tblProdutos.getColumn("X").setMaxWidth(30);
				GridBagConstraints gbc_tblProdutos = new GridBagConstraints();
				gbc_tblProdutos.insets = new Insets(0, 0, 5, 0);
				gbc_tblProdutos.fill = GridBagConstraints.BOTH;
				gbc_tblProdutos.gridx = 1;
				gbc_tblProdutos.gridy = 2;
				panel.add(new JScrollPane(tblProdutos), BorderLayout.CENTER);

				for (Produto produto : fachada.filtrarProdutos("")) {
					tableModelProdutos.insertRow(
							this.tblProdutos.getRowCount(), new Object[] {
									new Boolean(false), produto.getCodigo(),
									produto.getNome(), produto.getDescricao(),
									produto.getTamanho(), produto.getValor() });
				}
			}
		}
		{
			lblData = new JLabel("Data:");
			GridBagConstraints gbc_lblData = new GridBagConstraints();
			gbc_lblData.anchor = GridBagConstraints.EAST;
			gbc_lblData.insets = new Insets(0, 0, 5, 5);
			gbc_lblData.gridx = 0;
			gbc_lblData.gridy = 2;
			contentPanel.add(lblData, gbc_lblData);
		}
		{
			MaskFormatter mascaraData = null;
			try {
				mascaraData = new MaskFormatter("##/##/####");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			txtData = new JFormattedTextField(mascaraData);
			lblData.setLabelFor(txtData);
			GridBagConstraints gbc_txtData = new GridBagConstraints();
			gbc_txtData.insets = new Insets(0, 0, 5, 0);
			gbc_txtData.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtData.gridx = 1;
			gbc_txtData.gridy = 2;
			contentPanel.add(txtData, gbc_txtData);
			txtData.setColumns(10);
		}
		{
			lblValorLabel = new JLabel("Valor:");
			GridBagConstraints gbc_lblValorLabel = new GridBagConstraints();
			gbc_lblValorLabel.anchor = GridBagConstraints.EAST;
			gbc_lblValorLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblValorLabel.gridx = 0;
			gbc_lblValorLabel.gridy = 3;
			contentPanel.add(lblValorLabel, gbc_lblValorLabel);
		}
		{
			lblValor = new JLabel("R$ 0,00");
			lblValorLabel.setLabelFor(lblValor);
			GridBagConstraints gbc_lblValor = new GridBagConstraints();
			gbc_lblValor.insets = new Insets(0, 0, 5, 0);
			gbc_lblValor.anchor = GridBagConstraints.EAST;
			gbc_lblValor.gridx = 1;
			gbc_lblValor.gridy = 3;
			contentPanel.add(lblValor, gbc_lblValor);
		}
		{
			lblFreteLabel = new JLabel("Frete:");
			GridBagConstraints gbc_lblFreteLabel = new GridBagConstraints();
			gbc_lblFreteLabel.anchor = GridBagConstraints.EAST;
			gbc_lblFreteLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblFreteLabel.gridx = 0;
			gbc_lblFreteLabel.gridy = 4;
			contentPanel.add(lblFreteLabel, gbc_lblFreteLabel);
		}
		{
			lblFrete = new JLabel("R$ 0,00");
			GridBagConstraints gbc_lblFrete = new GridBagConstraints();
			gbc_lblFrete.anchor = GridBagConstraints.EAST;
			gbc_lblFrete.insets = new Insets(0, 0, 5, 0);
			gbc_lblFrete.gridx = 1;
			gbc_lblFrete.gridy = 4;
			contentPanel.add(lblFrete, gbc_lblFrete);
		}
		{
			lblNewLabel = new JLabel("Desconto:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 5;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}

		lblDesconto = new JLabel("0%");
		GridBagConstraints gbc_lblDesconto = new GridBagConstraints();
		gbc_lblDesconto.insets = new Insets(0, 0, 5, 0);
		gbc_lblDesconto.anchor = GridBagConstraints.EAST;
		gbc_lblDesconto.gridx = 1;
		gbc_lblDesconto.gridy = 5;
		contentPanel.add(lblDesconto, gbc_lblDesconto);

		JLabel lblTotalLabel = new JLabel("Total:");
		GridBagConstraints gbc_lblTotalLabel = new GridBagConstraints();
		gbc_lblTotalLabel.anchor = GridBagConstraints.EAST;
		gbc_lblTotalLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalLabel.gridx = 0;
		gbc_lblTotalLabel.gridy = 6;
		contentPanel.add(lblTotalLabel, gbc_lblTotalLabel);

		lblTotal = new JLabel("R$ 0,00");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 6;
		contentPanel.add(lblTotal, gbc_lblTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Adicionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String clienteTelefone = clienteTelefoneMap
								.get(cbClientes.getSelectedItem().toString());
						String data = txtData.getText();
						Produto[] produtos = new Produto[produtosSelecionados
								.size()];
						for (int i = 0; i < produtosSelecionados.size(); i++) {
							produtos[i] = produtosSelecionados.get(i);
						}

						if ((fachada.quantidadePedidosTelefone(clienteTelefone) + 1) % 5 == 0) {
							JOptionPane
									.showMessageDialog(null,
											"Esse � o seu quinto pedido! Um dos produtos sair� de gra�a!");

							produtos = new Produto[produtosSelecionados.size() - 1];

							Produto menorValor = null;
							for (int i = 0; i < produtosSelecionados.size(); i++) {
								if (i == 0) {
									menorValor = produtosSelecionados.get(i);
								}
								if (produtosSelecionados.get(i).getValor()
										.doubleValue() < menorValor.getValor()
										.doubleValue()) {
									menorValor = produtosSelecionados.get(i);
								}
							}
							produtosSelecionados.remove(menorValor);

							for (int i = 0; i < produtosSelecionados.size(); i++) {
								produtos[i] = produtosSelecionados.get(i);
							}
						}

						Pedido novoPedido = new Pedido(clienteTelefone,
								produtos, produtos.length,
								getValorTotal(clienteTelefone), data);

						if (data.replaceAll(" ", "").length() == 10) {
							try {
								fachada.adicionarPedido(novoPedido);
								mw.atualizarTabelaPedidos(fachada.filtrarPedidos(
										mw.getTxtFiltroPedidosInicial(),
										mw.getTxtFiltroPedidosFinal()));
								dispose();
							} catch (DataObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Data inv�lida.");
							} catch (TelefoneObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Telefone inv�lido.");
							} catch (QuantidadeInvalidaException e1) {
								JOptionPane
										.showMessageDialog(null,
												"O pedido deve conter pelo menos um produto.");
							} catch (ProdutosObrigatorioException e1) {
								JOptionPane
										.showMessageDialog(null,
												"O pedido deve conter pelo menos um produto.");
							} catch (ClienteNaoCadastradoException e1) {
								JOptionPane.showMessageDialog(null,
										"Cliente n�o encontrado.");
							}

						} else {
							JOptionPane
									.showMessageDialog(null, "Data inv�lida");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		Object selected = cbClientes.getSelectedItem();
		if (selected != null)
			calculaFrete(clienteTelefoneMap.get(selected.toString()));

	}

	public void calculaFrete(String telefone) {
		String bairro = fachada.retornaCliente(telefone).getEndereco()
				.getBairro();
		if (bairrosDesconto.contains(bairro)) {
			frete = 3.5;
		} else {
			frete = 7;
		}
		this.lblFrete.setText("R$ " + frete);
		atualizaTotal();
	}

	public void somarValor(double somaValor) {
		valor = valor + somaValor;
		lblValor.setText("R$ " + valor);
		atualizaTotal();
	}

	public void subtrairValor(double subValor) {
		valor = valor - subValor;
		lblValor.setText("R$ " + valor);
		atualizaTotal();
	}

	public BigDecimal getValorTotal(String telefone) {
		return BigDecimal.valueOf(total);
	}

	public void atualizaTotal() {
		if (valor > 0) {
			total = frete + valor;
			if (taxaDesconto > 0)
				total = total * (1 - taxaDesconto);
			this.lblTotal.setText("R$ " + total);
		}
	}

	public void atualizaDesconto() {
		this.taxaDesconto = Double.parseDouble(getQuantidadeExcedente() + "") / 10;
		System.out.println(taxaDesconto);
		atualizaTotal();
		this.lblDesconto.setText(taxaDesconto * 100 + "%");
	}

	public void toggleProduto(Produto p) {
		if (produtosSelecionados.contains(p)) {
			subtrairValor(Double.parseDouble(p.getValor().toString()));
			produtosSelecionados.remove(p);
		} else {
			somarValor(Double.parseDouble(p.getValor().toString()));
			produtosSelecionados.add(p);
		}
	}

	public int getQuantidadeExcedente() {
		if (quantidadeProdutos() - 1 > 5) {
			return quantidadeProdutos() - 1 - 5;
		} else {
			return 0;
		}
	}

	public int quantidadeProdutos() {
		return produtosSelecionados.size() + 1;
	}
}

class BooleanCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;

	public BooleanCellEditor(final JTable table, final AddOrderDialog aod,
			final Fachada fachada) {
		super(new JCheckBox());
		JCheckBox checkBox = (JCheckBox) getComponent();
		checkBox.setHorizontalAlignment(JCheckBox.CENTER);

		checkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.convertRowIndexToModel(table.getEditingRow());
				Produto p = fachada.retornaProduto(table.getModel()
						.getValueAt(row, 1).toString());
				if (aod.quantidadeProdutos() < 8) {
					aod.toggleProduto(p);
				} else {
					if (!((JCheckBox) e.getSource()).isSelected()) {
						aod.toggleProduto(p);
					} else {
						JOptionPane
								.showMessageDialog(aod, "7 itens no m�ximo!");
					}
					((JCheckBox) e.getSource()).setSelected(false);
				}
				aod.atualizaDesconto();
			}
		});
	}
}

class BooleanCellRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public BooleanCellRenderer() {
		super();
		setHorizontalAlignment(JLabel.CENTER);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Checkbox.background"));
		}
		setSelected((value != null && ((Boolean) value).booleanValue()));
		return this;
	}

}