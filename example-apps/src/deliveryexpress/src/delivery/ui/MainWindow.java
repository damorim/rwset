package delivery.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import delivery.business.Cliente;
import delivery.business.Pedido;
import delivery.business.Produto;
import delivery.business.exceptions.product.CodigoObrigatorioException;
import delivery.business.frontage.Fachada;
import delivery.ui.forms.AddClientDialog;
import delivery.ui.forms.AddOrderDialog;
import delivery.ui.forms.AddProductDialog;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPedidos;
	private JTable tblClientes;
	private JTable tblProdutos;
	private JTextField txtFiltrarProdutos;
	private JTextField txtFiltrarClientes;
	private DefaultTableModel tableModelClientes;
	private DefaultTableModel tableModelProdutos;
	private DefaultTableModel tableModelPedidos;
	private TableRowSorter<TableModel> tableSorterClientes;
	private TableRowSorter<TableModel> tableSorterProdutos;
	private TableRowSorter<TableModel> tableSorterPedidos;

	private Fachada fachada;
	private JTextField txtFiltrarPedidosDataInicial;
	private JTextField txtFiltrarPedidosDataFinal;

	private MaskFormatter mascaraTelefone;
	private JTextField txtRelatorioFiltroClienteNome;
	private JFormattedTextField txtRelatorioFiltroClienteDataInicial;
	private JFormattedTextField txtRelatorioFiltroClienteDataFinal;
	private JTextField txtRelatorioFiltroProdutoCodigo;
	private JFormattedTextField txtRelatorioFiltroProdutoDataInicial;
	private JFormattedTextField txtRelatorioFiltroProdutoDataFinal;

	private JButton btnGerarRelatorioProduto;
	private JTextPane txtpaneRelatorioClientes;
	private JTextPane txtpaneRelatorioProdutos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria uma interface para que um usuário leigo possa interagir com o
	 * sistema
	 */
	public MainWindow() {
		fachada = new Fachada();
		setMinimumSize(new Dimension(1000, 700));
		setTitle("Delivery Express");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
		} catch (InstantiationException e1) {
		} catch (IllegalAccessException e1) {
		} catch (UnsupportedLookAndFeelException e1) {
		}
		final MainWindow mw = this;

		try {
			mascaraTelefone = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel pnlPedidos = new JPanel();
		pnlPedidos.setLocation(new Point(15, 0));
		tabbedPane.addTab("Pedidos", null, pnlPedidos, null);

		pnlPedidos.setLayout(new BorderLayout(0, 0));

		JPanel pnlToolbarPedidos = new JPanel();
		pnlPedidos.add(pnlToolbarPedidos, BorderLayout.NORTH);
		pnlToolbarPedidos.setLayout(new BorderLayout(0, 0));

		JPanel pnlButtonsPedido = new JPanel();
		pnlToolbarPedidos.add(pnlButtonsPedido, BorderLayout.WEST);

		JButton btnNovoPedido = new JButton("Novo pedido");
		btnNovoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOrderDialog dialog = new AddOrderDialog(mw, fachada);
				dialog.setVisible(true);
			}
		});
		pnlButtonsPedido.add(btnNovoPedido);

		JPanel pnlFiltrarPedidos = new JPanel();
		pnlToolbarPedidos.add(pnlFiltrarPedidos, BorderLayout.EAST);

		JLabel lblFiltrarPedidosData = new JLabel("Filtrar por data:");
		pnlFiltrarPedidos.add(lblFiltrarPedidosData);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		pnlFiltrarPedidos.add(horizontalStrut_1);

		txtFiltrarPedidosDataInicial = new JFormattedTextField(mascaraTelefone);
		txtFiltrarPedidosDataInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabelaPedidos(fachada.filtrarPedidos(
						getTxtFiltroPedidosInicial(),
						getTxtFiltroPedidosFinal()));
			}
		});
		lblFiltrarPedidosData.setLabelFor(txtFiltrarPedidosDataInicial);
		pnlFiltrarPedidos.add(txtFiltrarPedidosDataInicial);
		txtFiltrarPedidosDataInicial.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 10));
		separator.setOrientation(SwingConstants.VERTICAL);
		pnlFiltrarPedidos.add(separator);

		JLabel lblDataA = new JLabel("à");
		pnlFiltrarPedidos.add(lblDataA);

		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		pnlFiltrarPedidos.add(horizontalStrut_3);

		txtFiltrarPedidosDataFinal = new JFormattedTextField(mascaraTelefone);
		txtFiltrarPedidosDataFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabelaPedidos(fachada.filtrarPedidos(
						getTxtFiltroPedidosInicial(),
						getTxtFiltroPedidosFinal()));
			}
		});
		lblDataA.setLabelFor(txtFiltrarPedidosDataFinal);
		pnlFiltrarPedidos.add(txtFiltrarPedidosDataFinal);
		txtFiltrarPedidosDataFinal.setColumns(10);

		JPanel pnlPedidosAberto = new JPanel();
		pnlPedidos.add(pnlPedidosAberto);
		pnlPedidosAberto.setLayout(new BorderLayout(0, 0));

		tblPedidos = new JTable();
		final String tblProdutosPedidoCols[] = { "Código", "Cliente", "Data",
				"#", "Valor", "Ações" };
		tableModelPedidos = new DefaultTableModel(null, tblProdutosPedidoCols) {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int row, int column) {
				Object value = super.getValueAt(row, column);
				if (column == 2) {
					try {
						SimpleDateFormat formatter = new SimpleDateFormat();
						Date date = formatter.parse(value.toString());
						formatter.applyPattern("dd/MM/yyyy");
						return formatter.format(date);
					} catch (ParseException e) {
					}
				}
				return value;
			}
		};
		tblPedidos = new JTable(tableModelPedidos);
		tblPedidos.setAutoCreateColumnsFromModel(true);
		tblPedidos.setFocusable(false);
		tblPedidos.setRowHeight(36);
		tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPedidos.getTableHeader().setBackground(Color.lightGray);
		tblPedidos.getColumnModel().getColumn(5)
				.setCellRenderer(new ButtonsRendererPedidos());
		tblPedidos
				.getColumnModel()
				.getColumn(5)
				.setCellEditor(
						new ButtonsEditorPedidos(tblPedidos, this, fachada));
		tblPedidos.getColumn("Código").setMaxWidth(100);
		tblPedidos.getColumn("Data").setMaxWidth(160);
		tblPedidos.getColumn("Data").setMinWidth(160);
		tblPedidos.getColumn("#").setMaxWidth(50);
		tblPedidos.getColumn("Valor").setMaxWidth(100);
		tblPedidos.getColumn("Valor").setMinWidth(100);
		tblPedidos.getColumn("Ações").setMinWidth(220);
		tblPedidos.getColumn("Ações").setMaxWidth(220);
		tblPedidos.setFont(new Font("Verdana", Font.PLAIN, 15));
		tableSorterPedidos = new TableRowSorter<TableModel>(tableModelPedidos);
		tableSorterPedidos.setComparator(2, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = null, d2 = null;
				try {
					d1 = formatter.parse(o1);
					d2 = formatter.parse(o2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return d1.compareTo(d2);
			}
		});
		tblPedidos.setRowSorter(tableSorterPedidos);
		tableSorterPedidos.toggleSortOrder(2);
		tableSorterPedidos.toggleSortOrder(2);
		tableSorterPedidos.setSortable(5, false);
		tblPedidos.setFillsViewportHeight(false);
		pnlPedidosAberto.add(new JScrollPane(tblPedidos,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		this.atualizarTabelaPedidos(fachada.filtrarPedidos("", ""));

		JPanel pnlClientes = new JPanel();
		tabbedPane.addTab("Clientes", null, pnlClientes, null);
		pnlClientes.setLayout(new BoxLayout(pnlClientes, BoxLayout.Y_AXIS));

		JPanel pnlListaClientes = new JPanel();
		pnlClientes.add(pnlListaClientes);
		pnlListaClientes.setLayout(new BorderLayout(5, 5));

		String tblClientesCols[] = { "Nome", "CPF", "Telefone", "Ações" };
		tableModelClientes = new DefaultTableModel(null, tblClientesCols);
		tblClientes = new JTable(tableModelClientes);
		tblClientes.setAutoCreateColumnsFromModel(true);
		tblClientes.setFocusable(false);
		tblClientes.setRowHeight(36);
		tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblClientes.getTableHeader().setBackground(Color.lightGray);
		tblClientes.getColumnModel().getColumn(3)
				.setCellRenderer(new ButtonsRendererClientes());
		tblClientes
				.getColumnModel()
				.getColumn(3)
				.setCellEditor(
						new ButtonsEditorClientes(tblClientes, this, fachada));
		tblClientes.getColumn("Nome").setPreferredWidth(170);
		tblClientes.getColumn("Ações").setMinWidth(220);
		tblClientes.getColumn("Ações").setMaxWidth(220);
		tblClientes.setFont(new Font("Verdana", Font.PLAIN, 15));
		tableSorterClientes = new TableRowSorter<TableModel>(tableModelClientes);
		tblClientes.setRowSorter(tableSorterClientes);
		tableSorterClientes.toggleSortOrder(0);
		tableSorterClientes.setSortable(3, false);
		tblClientes.setFillsViewportHeight(false);
		pnlListaClientes.add(new JScrollPane(tblClientes,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		this.atualizarTabelaClientes(fachada.filtrarClientes(""));

		JPanel pnlToolbarClientes = new JPanel();
		pnlListaClientes.add(pnlToolbarClientes, BorderLayout.NORTH);
		pnlToolbarClientes.setLayout(new BorderLayout(0, 0));

		JPanel pnlButtonsClientes = new JPanel();
		pnlToolbarClientes.add(pnlButtonsClientes, BorderLayout.WEST);
		pnlButtonsClientes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
		btnAdicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClientDialog addClientDialog = new AddClientDialog(mw,
						fachada);
				addClientDialog.setVisible(true);
			}
		});
		pnlButtonsClientes.add(btnAdicionarCliente);

		JPanel pnlFiltrarClientes = new JPanel();
		pnlToolbarClientes.add(pnlFiltrarClientes, BorderLayout.EAST);
		pnlFiltrarClientes.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		Box hbFiltrarClientes = Box.createHorizontalBox();
		pnlFiltrarClientes.add(hbFiltrarClientes);

		JLabel lblFiltrarClientes = new JLabel("Filtrar por nome:");
		hbFiltrarClientes.add(lblFiltrarClientes);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		hbFiltrarClientes.add(horizontalStrut_2);

		txtFiltrarClientes = new JTextField();
		txtFiltrarClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabelaClientes(fachada
						.filtrarClientes(txtFiltrarClientes.getText()));
			}
		});
		txtFiltrarClientes.setColumns(10);
		hbFiltrarClientes.add(txtFiltrarClientes);

		JPanel pnlProdutos = new JPanel();
		tabbedPane.addTab("Produtos", null, pnlProdutos, null);
		pnlProdutos.setLayout(new BoxLayout(pnlProdutos, BoxLayout.Y_AXIS));

		JPanel pnlListaProdutos = new JPanel();
		pnlProdutos.add(pnlListaProdutos);
		pnlListaProdutos.setLayout(new BorderLayout(0, 0));

		JPanel pnlToolbarProdutos = new JPanel();
		pnlListaProdutos.add(pnlToolbarProdutos, BorderLayout.NORTH);
		pnlToolbarProdutos.setLayout(new BorderLayout(0, 0));

		JPanel pnlButtonsProdutos = new JPanel();
		pnlToolbarProdutos.add(pnlButtonsProdutos, BorderLayout.WEST);

		JButton btnAdicionarProduto = new JButton("Adicionar produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductDialog addProductDialog = new AddProductDialog(mw,
						fachada);
				addProductDialog.setVisible(true);
			}
		});
		pnlButtonsProdutos.add(btnAdicionarProduto);

		JPanel pnlFiltrarProdutos = new JPanel();
		pnlToolbarProdutos.add(pnlFiltrarProdutos, BorderLayout.EAST);
		pnlFiltrarProdutos.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		Box hbFiltrarProdutos = Box.createHorizontalBox();
		pnlFiltrarProdutos.add(hbFiltrarProdutos);

		JLabel lblFiltrar = new JLabel("Filtrar:");
		hbFiltrarProdutos.add(lblFiltrar);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		hbFiltrarProdutos.add(horizontalStrut);

		txtFiltrarProdutos = new JTextField();
		txtFiltrarProdutos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabelaProdutos(fachada
						.filtrarProdutos(txtFiltrarProdutos.getText()));
			}
		});
		hbFiltrarProdutos.add(txtFiltrarProdutos);
		txtFiltrarProdutos.setColumns(10);

		tblProdutos = new JTable();
		String tblProdutosCols[] = { "Código", "Nome", "Tamanho", "Valor",
				"Ações" };
		tableModelProdutos = new DefaultTableModel(null, tblProdutosCols);
		tblProdutos = new JTable(tableModelProdutos);
		tblProdutos.setAutoCreateColumnsFromModel(true);
		tblProdutos.setFocusable(false);
		tblProdutos.setRowHeight(36);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdutos.getTableHeader().setBackground(Color.lightGray);
		tblProdutos.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonsRendererProdutos());
		tblProdutos
				.getColumnModel()
				.getColumn(4)
				.setCellEditor(
						new ButtonsEditorProdutos(tblProdutos, this, fachada));
		tblProdutos.getColumn("Código").setPreferredWidth(60);
		tblProdutos.getColumn("Nome").setPreferredWidth(200);
		tblProdutos.getColumn("Ações").setMinWidth(220);
		tblProdutos.getColumn("Ações").setMaxWidth(220);
		tblProdutos.setFont(new Font("Verdana", Font.PLAIN, 15));
		tableSorterProdutos = new TableRowSorter<TableModel>(tableModelProdutos);
		tblProdutos.setRowSorter(tableSorterProdutos);
		tableSorterProdutos.toggleSortOrder(0);
		pnlListaProdutos.add(new JScrollPane(tblProdutos,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		this.atualizarTabelaProdutos(fachada.filtrarProdutos(""));

		JPanel pnlRelatorios = new JPanel();
		tabbedPane.addTab("Relatórios", null, pnlRelatorios, null);
		GridBagLayout gbl_pnlRelatorios = new GridBagLayout();
		gbl_pnlRelatorios.columnWidths = new int[] { 981, 0 };
		gbl_pnlRelatorios.rowHeights = new int[] { 308, 308, 0, 0 };
		gbl_pnlRelatorios.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlRelatorios.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		pnlRelatorios.setLayout(gbl_pnlRelatorios);

		JPanel pnlRelatorioClientes = new JPanel();
		pnlRelatorioClientes.setBorder(new TitledBorder(null,
				"Gerar relatório de cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlRelatorioClientes = new GridBagConstraints();
		gbc_pnlRelatorioClientes.fill = GridBagConstraints.BOTH;
		gbc_pnlRelatorioClientes.insets = new Insets(0, 0, 5, 0);
		gbc_pnlRelatorioClientes.gridx = 0;
		gbc_pnlRelatorioClientes.gridy = 0;
		pnlRelatorios.add(pnlRelatorioClientes, gbc_pnlRelatorioClientes);
		pnlRelatorioClientes.setLayout(new BorderLayout(0, 0));

		JPanel pnlRelatorioFiltroCliente = new JPanel();
		pnlRelatorioClientes.add(pnlRelatorioFiltroCliente, BorderLayout.NORTH);
		pnlRelatorioFiltroCliente.setLayout(new FlowLayout(FlowLayout.LEFT, 10,
				5));

		JLabel lblRelatorioFiltroClienteNome = new JLabel("Filtrar por nome:");
		pnlRelatorioFiltroCliente.add(lblRelatorioFiltroClienteNome);

		txtRelatorioFiltroClienteNome = new JTextField();
		lblRelatorioFiltroClienteNome
				.setLabelFor(txtRelatorioFiltroClienteNome);
		pnlRelatorioFiltroCliente.add(txtRelatorioFiltroClienteNome);
		txtRelatorioFiltroClienteNome.setColumns(20);

		JLabel lblRelatorioFiltroClienteDataInicial = new JLabel(
				"Filtrar por data de pedidos:");
		pnlRelatorioFiltroCliente.add(lblRelatorioFiltroClienteDataInicial);

		txtRelatorioFiltroClienteDataInicial = new JFormattedTextField(
				mascaraTelefone);
		lblRelatorioFiltroClienteDataInicial
				.setLabelFor(txtRelatorioFiltroClienteDataInicial);
		pnlRelatorioFiltroCliente.add(txtRelatorioFiltroClienteDataInicial);
		txtRelatorioFiltroClienteDataInicial.setColumns(7);

		JLabel lblRelatorioFiltroClienteDataFinal = new JLabel("à");
		pnlRelatorioFiltroCliente.add(lblRelatorioFiltroClienteDataFinal);

		txtRelatorioFiltroClienteDataFinal = new JFormattedTextField(
				mascaraTelefone);
		lblRelatorioFiltroClienteDataFinal
				.setLabelFor(txtRelatorioFiltroClienteDataFinal);
		pnlRelatorioFiltroCliente.add(txtRelatorioFiltroClienteDataFinal);
		txtRelatorioFiltroClienteDataFinal.setColumns(7);

		JButton btnGerarRelatorioCliente = new JButton("Gerar relatório");
		btnGerarRelatorioCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTxtpaneRelatorioClientes(fachada.gerarRelatorioClientes(
						getTxtFiltroRelatorioClienteNome(),
						getTxtFiltroRelatorioClienteDataInicial(),
						getTxtFiltroRelatorioClienteDataFinal()));
			}
		});
		pnlRelatorioFiltroCliente.add(btnGerarRelatorioCliente);

		txtpaneRelatorioClientes = new JTextPane();
		pnlRelatorioClientes.add(new JScrollPane(txtpaneRelatorioClientes), BorderLayout.CENTER);

		JPanel pnlRelatorioProdutos = new JPanel();
		pnlRelatorioProdutos.setBorder(new TitledBorder(null,
				"Gerar relat\u00F3rio de produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlRelatorioProdutos = new GridBagConstraints();
		gbc_pnlRelatorioProdutos.insets = new Insets(0, 0, 5, 0);
		gbc_pnlRelatorioProdutos.fill = GridBagConstraints.BOTH;
		gbc_pnlRelatorioProdutos.gridx = 0;
		gbc_pnlRelatorioProdutos.gridy = 1;
		pnlRelatorios.add(pnlRelatorioProdutos, gbc_pnlRelatorioProdutos);
		pnlRelatorioProdutos.setLayout(new BorderLayout(0, 0));

		JPanel pnlRelatorioFiltroProduto = new JPanel();
		pnlRelatorioProdutos.add(pnlRelatorioFiltroProduto, BorderLayout.NORTH);
		pnlRelatorioFiltroProduto.setLayout(new FlowLayout(FlowLayout.LEFT, 10,
				5));

		JLabel lblRelatorioFiltroProdutoNome = new JLabel("Código do produto:");
		pnlRelatorioFiltroProduto.add(lblRelatorioFiltroProdutoNome);

		txtRelatorioFiltroProdutoCodigo = new JTextField();
		txtRelatorioFiltroProdutoCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (getTxtFiltroRelatorioProdutoCodigo().isEmpty()) {
					txtRelatorioFiltroProdutoDataInicial.setEnabled(false);
					txtRelatorioFiltroProdutoDataFinal.setEnabled(false);
					btnGerarRelatorioProduto.setEnabled(false);
				} else {
					txtRelatorioFiltroProdutoDataInicial.setEnabled(true);
					txtRelatorioFiltroProdutoDataFinal.setEnabled(true);
					btnGerarRelatorioProduto.setEnabled(true);
				}
			}
		});
		lblRelatorioFiltroProdutoNome
				.setLabelFor(txtRelatorioFiltroProdutoCodigo);
		txtRelatorioFiltroProdutoCodigo.setColumns(20);
		pnlRelatorioFiltroProduto.add(txtRelatorioFiltroProdutoCodigo);

		JLabel lblRelatorioFiltroProdutoDataInicial = new JLabel(
				"Filtrar por data de pedidos:");
		pnlRelatorioFiltroProduto.add(lblRelatorioFiltroProdutoDataInicial);

		txtRelatorioFiltroProdutoDataInicial = new JFormattedTextField(
				mascaraTelefone);
		lblRelatorioFiltroProdutoDataInicial
				.setLabelFor(txtRelatorioFiltroProdutoDataInicial);
		txtRelatorioFiltroProdutoDataInicial.setColumns(7);
		txtRelatorioFiltroProdutoDataInicial.setEnabled(false);
		pnlRelatorioFiltroProduto.add(txtRelatorioFiltroProdutoDataInicial);

		JLabel lblRelatorioFiltroProdutoDataFinal = new JLabel("à");
		pnlRelatorioFiltroProduto.add(lblRelatorioFiltroProdutoDataFinal);

		txtRelatorioFiltroProdutoDataFinal = new JFormattedTextField(
				mascaraTelefone);
		lblRelatorioFiltroProdutoDataFinal
				.setLabelFor(txtRelatorioFiltroProdutoDataFinal);
		txtRelatorioFiltroProdutoDataFinal.setColumns(7);
		txtRelatorioFiltroProdutoDataFinal.setEnabled(false);
		pnlRelatorioFiltroProduto.add(txtRelatorioFiltroProdutoDataFinal);

		btnGerarRelatorioProduto = new JButton("Gerar relatório");
		btnGerarRelatorioProduto.setEnabled(false);
		btnGerarRelatorioProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setTxtpaneRelatorioProdutos(fachada.gerarRelatorioProdutos(
							getTxtFiltroRelatorioProdutoCodigo(),
							getTxtFiltroRelatorioProdutoDataInicial(),
							getTxtFiltroRelatorioProdutoDataFinal()));
				} catch (CodigoObrigatorioException e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(mw,
							"Insira um código válido.");
				}
			}
		});
		pnlRelatorioFiltroProduto.add(btnGerarRelatorioProduto);

		txtpaneRelatorioProdutos = new JTextPane();
		pnlRelatorioProdutos.add(new JScrollPane(txtpaneRelatorioProdutos), BorderLayout.CENTER);
	}

	/*
	 * Métodos para atualizar as tabelas que listam os clientes, produtos,
	 * pedidos
	 */

	public void atualizarTabelaClientes(ArrayList<Cliente> clientes) {
		while (tableModelClientes.getRowCount() > 0) {
			tableModelClientes.removeRow(0);
		}
		for (int i = 0; i < clientes.size(); i++) {
			tableModelClientes.insertRow(this.tblClientes.getRowCount(),
					new Object[] { clientes.get(i).getNome(),
							clientes.get(i).getCpf(),
							clientes.get(i).gettelefone() });
		}
	}

	public void atualizarTabelaProdutos(ArrayList<Produto> produtos) {
		while (tableModelProdutos.getRowCount() > 0) {
			tableModelProdutos.removeRow(0);
		}
		for (int i = 0; i < produtos.size(); i++) {
			tableModelProdutos.insertRow(this.tblProdutos.getRowCount(),
					new Object[] { produtos.get(i).getCodigo(),
							produtos.get(i).getNome(),
							produtos.get(i).getTamanho(),
							produtos.get(i).getValor() });
		}
	}

	public void atualizarTabelaPedidos(ArrayList<Pedido> pedidos) {
		while (tableModelPedidos.getRowCount() > 0) {
			tableModelPedidos.removeRow(0);
		}
		for (int i = 0; i < pedidos.size(); i++) {
			if (fachada.existeCliente(pedidos.get(i).getTelefoneCliente()))
				tableModelPedidos.insertRow(
						this.tblPedidos.getRowCount(),
						new Object[] {
								pedidos.get(i).getCodidoPedido(),
								fachada.retornaCliente(
										pedidos.get(i).getTelefoneCliente())
										.getNome(),
								pedidos.get(i).getDataPedidoString(),
								pedidos.get(i).getQuantidadeProdutos(),
								"R$ " + pedidos.get(i).getValor() });
		}
	}

	/*
	 * Getters e setters
	 */

	public String getTxtFiltroClientes() {
		return txtFiltrarClientes.getText();
	}

	public void setTxtFiltroClientes(String text) {
		txtFiltrarClientes.setText(text);
	}

	public String getTxtFiltroProdutos() {
		return txtFiltrarProdutos.getText();
	}

	public void setTxtFiltroProdutos(String text) {
		txtFiltrarProdutos.setText(text);
	}

	public String getTxtFiltroPedidosInicial() {
		if (txtFiltrarPedidosDataInicial.getText().replaceAll(" ", "").length() == 10)
			return txtFiltrarPedidosDataInicial.getText();
		return "";
	}

	public void setTxtFiltroPedidosInicial(String dataInicial) {
		txtFiltrarClientes.setText(dataInicial);
	}

	public String getTxtFiltroPedidosFinal() {
		if (txtFiltrarPedidosDataFinal.getText().replaceAll(" ", "").length() == 10)
			return txtFiltrarPedidosDataFinal.getText();
		return "";
	}

	public void setTxtFiltroPedidosFinal(String dataFinal) {
		txtFiltrarProdutos.setText(dataFinal);
	}

	public String getTxtFiltroRelatorioClienteNome() {
		return txtRelatorioFiltroClienteNome.getText();
	}

	public void setTxtFiltroRelatorioClienteNome(String text) {
		txtRelatorioFiltroClienteNome.setText(text);
	}

	public String getTxtFiltroRelatorioClienteDataInicial() {
		if (txtRelatorioFiltroClienteDataInicial.getText().replaceAll(" ", "")
				.length() == 10)
			return txtRelatorioFiltroClienteDataInicial.getText();
		return "";
	}

	public void setTxtFiltroRelatorioClienteDataInicial(String text) {
		txtRelatorioFiltroClienteDataInicial.setText(text);
	}

	public String getTxtFiltroRelatorioClienteDataFinal() {
		if (txtRelatorioFiltroClienteDataFinal.getText().replaceAll(" ", "")
				.length() == 10)
			return txtRelatorioFiltroClienteDataFinal.getText();
		return "";
	}

	public void setTxtFiltroRelatorioClienteDataFinal(String text) {
		txtRelatorioFiltroClienteDataFinal.setText(text);
	}

	public String getTxtFiltroRelatorioProdutoCodigo() {
		return txtRelatorioFiltroProdutoCodigo.getText();
	}

	public void setTxtFiltroRelatorioProdutoCodigo(String text) {
		txtRelatorioFiltroProdutoCodigo.setText(text);
	}

	public String getTxtFiltroRelatorioProdutoDataInicial() {
		if (txtRelatorioFiltroProdutoDataInicial.getText().replaceAll(" ", "")
				.length() == 10)
			return txtRelatorioFiltroProdutoDataInicial.getText();
		return "";
	}

	public void setTxtFiltroRelatorioProdutoDataInicial(String text) {
		txtRelatorioFiltroProdutoDataInicial.setText(text);
	}

	public String getTxtFiltroRelatorioProdutoDataFinal() {
		if (txtRelatorioFiltroProdutoDataFinal.getText().replaceAll(" ", "")
				.length() == 10)
			return txtRelatorioFiltroProdutoDataFinal.getText();
		return "";
	}

	public void setTxtFiltroRelatorioProdutoDataFinal(String text) {
		txtRelatorioFiltroProdutoDataFinal.setText(text);
	}

	public void setTxtpaneRelatorioClientes(ArrayList<String> relatorio) {
		txtpaneRelatorioClientes.setText("");
		for (String s : relatorio) {
			txtpaneRelatorioClientes.setText(txtpaneRelatorioClientes.getText()
					+ "\n" + s);
		}
	}

	public void setTxtpaneRelatorioProdutos(ArrayList<String> relatorio) {
		txtpaneRelatorioProdutos.setText("");
		for (String s : relatorio) {
			txtpaneRelatorioProdutos.setText(txtpaneRelatorioProdutos.getText()
					+ "\n" + s);
		}
	}
}
