package delivery.ui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.Dimension;

import delivery.business.exceptions.client.BairroObrigatorioException;
import delivery.business.exceptions.client.CPFObrigatorioException;
import delivery.business.exceptions.client.CidadeObrigatorioException;
import delivery.business.exceptions.client.LogradouroObrigatorioException;
import delivery.business.exceptions.client.NomeObrigatorioException;
import delivery.business.exceptions.client.NumeroObrigatorioException;
import delivery.business.exceptions.client.TelefoneExistenteException;
import delivery.business.exceptions.client.TelefoneObrigatorioException;
import delivery.business.frontage.Fachada;
import delivery.ui.MainWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class AddClientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextArea txtObservacoes;
	private JLabel lblCPF;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblObservacoes;
	private JLabel lblLogradouro;
	private JLabel lblNumero;
	private JLabel lblComplemento;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JButton okButton;
	private MaskFormatter mask;

	public AddClientDialog(final MainWindow mw, final Fachada fachada) {
		setMinimumSize(new Dimension(450, 400));
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Adicionar Cliente");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		{
			lblNome = new JLabel("Nome:");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.gridx = 0;
			gbc_lblNome.gridy = 0;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			txtNome = new JTextField();
			lblNome.setLabelFor(txtNome);
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
			try {
				mask = new MaskFormatter("###.###.###-##");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtCPF = new JFormattedTextField(mask);
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
			try {
				mask = new MaskFormatter("####-####");
			} catch (ParseException e) {
			}
			txtTelefone = new JFormattedTextField(mask);
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Adicionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nome = txtNome.getText();
						String cpf = txtCPF.getText();
						String telefone = txtTelefone.getText();
						String logradouro = txtLogradouro.getText();
						String complemento = txtComplemento.getText();
						String numero = txtNumero.getText();
						String bairro = txtBairro.getText();
						String cidade = txtCidade.getText();
						String observacoes = txtObservacoes.getText();
						if (!fachada.existeCliente(telefone) && telefone.length() == 9) {
							try {
								fachada.adicionarCliente(nome, cpf, telefone,
										logradouro, numero, complemento,
										cidade, bairro, observacoes);
							} catch (CPFObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"CPF é obrigatório.");
							} catch (NomeObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Nome é obrigatório.");
							} catch (TelefoneObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Telefone é obrigatório.");
							} catch (BairroObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Bairro é obrigatório.");
							} catch (CidadeObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Cidade é obrigatório.");
							} catch (LogradouroObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Logradouro é obrigatório.");
							} catch (NumeroObrigatorioException e1) {
								JOptionPane.showMessageDialog(null,
										"Número é obrigatório.");
							} catch (TelefoneExistenteException e1) {
								JOptionPane.showMessageDialog(null,
										"Telefone é obrigatório.");
							} finally {
								if (fachada.existeCliente(telefone)) {
									mw.atualizarTabelaClientes(fachada
											.filtrarClientes(nome));
									mw.setTxtFiltroClientes(nome);
									dispose();
								}
							}
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
