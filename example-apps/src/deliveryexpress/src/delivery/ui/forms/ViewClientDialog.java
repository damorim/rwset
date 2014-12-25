package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import delivery.business.Cliente;
import delivery.business.frontage.Fachada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewClientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextArea txtObservacoes;
	private JLabel lblCPF;
	private JLabel lblNNome;
	private JLabel lblTelefone;
	private JLabel lblObservacoes;
	private JLabel lblLogradouro;
	private JLabel lblNumero;
	private JLabel lblComplemento;
	private JLabel lblBairro;
	private JLabel lblCidade;

	public ViewClientDialog(final Fachada fachada, String telefone) {
		setTitle("Visualizar cadastro");
		setMinimumSize(new Dimension(450, 400));
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNNome = new JLabel("Nome:");
			GridBagConstraints gbc_lblNNome = new GridBagConstraints();
			gbc_lblNNome.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblNNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNNome.gridx = 0;
			gbc_lblNNome.gridy = 0;
			contentPanel.add(lblNNome, gbc_lblNNome);
		}
		{
			txtNome = new JTextField();
			txtNome.setEditable(false);
			lblNNome.setLabelFor(txtNome);
			GridBagConstraints gbc_txtNome = new GridBagConstraints();
			gbc_txtNome.insets = new Insets(0, 0, 5, 0);
			gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNome.gridx = 1;
			gbc_txtNome.gridy = 0;
			contentPanel.add(txtNome, gbc_txtNome);
			txtNome.setColumns(10);
		}
		{
			lblCPF = new JLabel("CPF:");
			GridBagConstraints gbc_lblCPF = new GridBagConstraints();
			gbc_lblCPF.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblCPF.insets = new Insets(0, 0, 5, 5);
			gbc_lblCPF.gridx = 0;
			gbc_lblCPF.gridy = 1;
			contentPanel.add(lblCPF, gbc_lblCPF);
		}
		{
			txtCPF = new JTextField();
			txtCPF.setEditable(false);
			lblCPF.setLabelFor(txtCPF);
			GridBagConstraints gbc_txtCPF = new GridBagConstraints();
			gbc_txtCPF.insets = new Insets(0, 0, 5, 0);
			gbc_txtCPF.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCPF.gridx = 1;
			gbc_txtCPF.gridy = 1;
			contentPanel.add(txtCPF, gbc_txtCPF);
			txtCPF.setColumns(10);
		}
		{
			lblTelefone = new JLabel("Telefone:");
			GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
			gbc_lblTelefone.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
			gbc_lblTelefone.gridx = 0;
			gbc_lblTelefone.gridy = 2;
			contentPanel.add(lblTelefone, gbc_lblTelefone);
		}
		{
			txtTelefone = new JTextField();
			txtTelefone.setEditable(false);
			lblTelefone.setLabelFor(txtTelefone);
			GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
			gbc_txtTelefone.insets = new Insets(0, 0, 5, 0);
			gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTelefone.gridx = 1;
			gbc_txtTelefone.gridy = 2;
			contentPanel.add(txtTelefone, gbc_txtTelefone);
			txtTelefone.setColumns(10);
		}
		{
			lblLogradouro = new JLabel("Logradouro:");
			GridBagConstraints gbc_lblLogradouro = new GridBagConstraints();
			gbc_lblLogradouro.anchor = GridBagConstraints.EAST;
			gbc_lblLogradouro.insets = new Insets(0, 0, 5, 5);
			gbc_lblLogradouro.gridx = 0;
			gbc_lblLogradouro.gridy = 3;
			contentPanel.add(lblLogradouro, gbc_lblLogradouro);
		}
		lblLogradouro.setLabelFor(txtLogradouro);
		{
			txtLogradouro = new JTextField();
			txtLogradouro.setEditable(false);
			GridBagConstraints gbc_txtLogradouro = new GridBagConstraints();
			gbc_txtLogradouro.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLogradouro.insets = new Insets(0, 0, 5, 0);
			gbc_txtLogradouro.gridx = 1;
			gbc_txtLogradouro.gridy = 3;
			contentPanel.add(txtLogradouro, gbc_txtLogradouro);
			txtLogradouro.setColumns(10);
		}
		{
			lblNumero = new JLabel("Número:");
			GridBagConstraints gbc_lblNumero = new GridBagConstraints();
			gbc_lblNumero.anchor = GridBagConstraints.EAST;
			gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumero.gridx = 0;
			gbc_lblNumero.gridy = 4;
			contentPanel.add(lblNumero, gbc_lblNumero);
		}
		lblNumero.setLabelFor(txtNumero);
		{
			txtNumero = new JTextField();
			txtNumero.setEditable(false);
			GridBagConstraints gbc_txtNumero = new GridBagConstraints();
			gbc_txtNumero.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNumero.insets = new Insets(0, 0, 5, 0);
			gbc_txtNumero.gridx = 1;
			gbc_txtNumero.gridy = 4;
			contentPanel.add(txtNumero, gbc_txtNumero);
			txtNumero.setColumns(10);
		}
		{
			lblComplemento = new JLabel("Complemento:");
			GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
			gbc_lblComplemento.anchor = GridBagConstraints.EAST;
			gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
			gbc_lblComplemento.gridx = 0;
			gbc_lblComplemento.gridy = 5;
			contentPanel.add(lblComplemento, gbc_lblComplemento);
		}
		lblComplemento.setLabelFor(txtComplemento);
		{
			txtComplemento = new JTextField();
			txtComplemento.setEditable(false);
			GridBagConstraints gbc_txtComplemento = new GridBagConstraints();
			gbc_txtComplemento.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtComplemento.insets = new Insets(0, 0, 5, 0);
			gbc_txtComplemento.gridx = 1;
			gbc_txtComplemento.gridy = 5;
			contentPanel.add(txtComplemento, gbc_txtComplemento);
			txtComplemento.setColumns(10);
		}
		{
			lblBairro = new JLabel("Bairro:");
			GridBagConstraints gbc_lblBairro = new GridBagConstraints();
			gbc_lblBairro.anchor = GridBagConstraints.EAST;
			gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
			gbc_lblBairro.gridx = 0;
			gbc_lblBairro.gridy = 6;
			contentPanel.add(lblBairro, gbc_lblBairro);
		}
		lblBairro.setLabelFor(txtBairro);
		{
			txtBairro = new JTextField();
			txtBairro.setEditable(false);
			GridBagConstraints gbc_txtBairro = new GridBagConstraints();
			gbc_txtBairro.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtBairro.insets = new Insets(0, 0, 5, 0);
			gbc_txtBairro.gridx = 1;
			gbc_txtBairro.gridy = 6;
			contentPanel.add(txtBairro, gbc_txtBairro);
			txtBairro.setColumns(10);
		}
		{
			lblCidade = new JLabel("Cidade:");
			GridBagConstraints gbc_lblCidade = new GridBagConstraints();
			gbc_lblCidade.anchor = GridBagConstraints.EAST;
			gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
			gbc_lblCidade.gridx = 0;
			gbc_lblCidade.gridy = 7;
			contentPanel.add(lblCidade, gbc_lblCidade);
		}
		lblCidade.setLabelFor(txtCidade);
		{
			txtCidade = new JTextField();
			txtCidade.setEditable(false);
			GridBagConstraints gbc_txtCidade = new GridBagConstraints();
			gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCidade.insets = new Insets(0, 0, 5, 0);
			gbc_txtCidade.gridx = 1;
			gbc_txtCidade.gridy = 7;
			contentPanel.add(txtCidade, gbc_txtCidade);
			txtCidade.setColumns(10);
		}
		{
			lblObservacoes = new JLabel("Observações:");
			GridBagConstraints gbc_lblObservacoes = new GridBagConstraints();
			gbc_lblObservacoes.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblObservacoes.insets = new Insets(0, 0, 5, 5);
			gbc_lblObservacoes.gridx = 0;
			gbc_lblObservacoes.gridy = 8;
			contentPanel.add(lblObservacoes, gbc_lblObservacoes);
		}
		lblObservacoes.setLabelFor(txtObservacoes);
		{
			txtObservacoes = new JTextArea();
			txtObservacoes.setEditable(false);
			txtObservacoes.setMinimumSize(new Dimension(0, 60));
			txtObservacoes.setCaretColor(Color.DARK_GRAY);
			txtObservacoes.setTabSize(4);
			txtObservacoes.setRows(3);
			GridBagConstraints gbc_txtObservacoes = new GridBagConstraints();
			gbc_txtObservacoes.insets = new Insets(0, 0, 5, 0);
			gbc_txtObservacoes.fill = GridBagConstraints.BOTH;
			gbc_txtObservacoes.gridx = 1;
			gbc_txtObservacoes.gridy = 8;
			contentPanel.add(txtObservacoes, gbc_txtObservacoes);
		}
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("OK");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		

		Cliente cliente = fachada.retornaCliente(telefone);
		txtNome.setText(cliente.getNome());
		txtCPF.setText(cliente.getCpf());
		txtTelefone.setText(cliente.gettelefone());
		txtLogradouro.setText(cliente.getEndereco().getLogradouro());
		txtNumero.setText(cliente.getEndereco().getNumero());
		txtComplemento.setText(cliente.getEndereco().getComplemento());
		txtBairro.setText(cliente.getEndereco().getBairro());
		txtCidade.setText(cliente.getEndereco().getCidade());
		txtObservacoes.setText(cliente.getObservacoes());
		
	}

}
