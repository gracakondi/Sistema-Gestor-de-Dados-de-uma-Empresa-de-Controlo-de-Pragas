/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: MenuPrincipalVisao.java
Data: 07.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class MenuPrincipalVisao extends JFrame implements ActionListener
{
	private JMenuBar menuBar;
	
	private JMenu menuCliente, menuServico, menuAgendamento, menuProf, menuTabelas, menuPesquisas, ajudaMenu;
	private JMenuItem novoCliente, editarCliente, eliminarCliente, sairCliente;
	private JMenuItem novoServico, editarServico, eliminarServico, sairServico;
	private JMenuItem novoAgendamento, editarAgendamento, eliminarAgendamento, sair;
	private JMenuItem novoProfissonal, editarProfissional, eliminarProfissional, sairProf;
	private JMenuItem especialidadeItem, tipoImovelItem, provinciaItem, municipioItem, comunaItem; 
	private JMenuItem listarCliente, listarServicos, listarProfissional, listarAgendamento;
	private JMenuItem pesquisarCliente, pesquisarServico, pesquisarProfissional, pesquisarAgendamento, sobreNosItem;
	
	public MenuPrincipalVisao(String user)
	{
		super(" Menu Principal | Agente: " + user);
	
		instanciadeObjectos();
		JPanel PainelCentro = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/6.jpg"));
		PainelCentro.add(lbImagem);

		getContentPane().add(PainelCentro, BorderLayout.CENTER);
		setJMenuBar(menuBar);

		setSize(1920, 1080);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void instanciadeObjectos()
	{
		menuBar = new JMenuBar();
		
		menuCliente = new JMenu("Cliente"); 
		menuCliente.setIcon(new ImageIcon("image/clientes24.png"));
		menuCliente.setMnemonic('C');
		
		menuServico = new JMenu("Servico");
		menuServico.setIcon(new ImageIcon("image/tec24.png"));
		menuServico.setMnemonic('S');

		menuAgendamento = new JMenu("Agendamento");
		menuAgendamento.setIcon(new ImageIcon("image/pedido24.png"));
		menuAgendamento.setMnemonic('A');
		
		menuProf = new JMenu("Profissional");
		menuProf.setIcon(new ImageIcon("image/profissionais.png"));
		menuProf.setMnemonic('P');
		
		menuTabelas = new JMenu("Tabelas");
		menuTabelas.setIcon(new ImageIcon("image/tabela24.png"));

		menuPesquisas = new JMenu("Pesquisar");
		menuPesquisas.setIcon(new ImageIcon("image/search24.png"));

		ajudaMenu = new JMenu("Ajuda");
		ajudaMenu.setIcon(new ImageIcon("image/help.png"));

		//items do menu ficheiro Cliente
		novoCliente = new JMenuItem("Novo Cliente"); 
		novoCliente.setIcon(new ImageIcon("image/novo24.png"));
		editarCliente = new JMenuItem("Editar Cliente");
		editarCliente.setIcon(new ImageIcon("image/edit24.png"));
		eliminarCliente = new JMenuItem("Eliminar Cliente");
		eliminarCliente.setIcon(new ImageIcon("image/delete24.png"));
		listarCliente = new JMenuItem("Listar Cliente");
		listarCliente.setIcon(new ImageIcon("image/ver.png"));
		sairCliente = new JMenuItem("Sair");
		sairCliente.setIcon(new ImageIcon("image/logout24.png"));
		
		menuCliente.add(novoCliente);
		menuCliente.add(editarCliente);
		menuCliente.add(eliminarCliente);
		menuCliente.add(listarCliente);
		menuCliente.addSeparator();
		menuCliente.add(sairCliente);
		//menuCliente.add(pesquisarCliente);
		
		
		//items do menu ficheiro novoServico
		novoServico = new JMenuItem("Novo Servico");
		novoServico.setIcon(new ImageIcon("image/novo24.png"));
	//	novoServico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		editarServico = new JMenuItem("Editar Servico");
		editarServico.setIcon(new ImageIcon("image/edit24.png"));
		eliminarServico = new JMenuItem("Eliminar Servico");
		eliminarServico.setIcon(new ImageIcon("image/delete24.png"));
		listarServicos = new JMenuItem("Listar Servicos");
		listarServicos.setIcon(new ImageIcon("image/ver.png"));
		sairServico = new JMenuItem("Sair");
		sairServico.setIcon(new ImageIcon("image/logout24.png"));
		
		menuServico.add(novoServico);
		menuServico.add(editarServico);
		menuServico.add(eliminarServico);
		menuServico.add(listarServicos);
		menuServico.addSeparator();
		menuServico.add(sairServico);	

		//items do menu ficheiro Agendamento
		novoAgendamento = new JMenuItem("Novo Agendamento");
		novoAgendamento.setIcon(new ImageIcon("image/novo24.png"));
		editarAgendamento = new JMenuItem("Editar Agendamento");
		editarAgendamento.setIcon(new ImageIcon("image/edit24.png"));
		eliminarAgendamento = new JMenuItem("Eliminar Agendamento");
		eliminarAgendamento.setIcon(new ImageIcon("image/delete24.png"));
		listarAgendamento = new JMenuItem ("Listar Agendamentos");
		listarAgendamento.setIcon(new ImageIcon("image/ver.png"));
		sair= new JMenuItem("Sair");
		sair.setIcon(new ImageIcon("image/logout24.png"));
		
		menuAgendamento.add(novoAgendamento);
		menuAgendamento.add(editarAgendamento);
		menuAgendamento.add(eliminarAgendamento);
		menuAgendamento.add(listarAgendamento);
		menuAgendamento.addSeparator();
		menuAgendamento.add(sair);
		
		//items do menu ficheiro Profissional
		novoProfissonal = new JMenuItem("Novo Profissional");
		novoProfissonal.setIcon(new ImageIcon("image/novo24.png"));
		editarProfissional = new JMenuItem("Editar Profissional");
		editarProfissional.setIcon(new ImageIcon("image/edit24.png"));
		eliminarProfissional = new JMenuItem("Eliminar Profissional");
		eliminarProfissional.setIcon(new ImageIcon("image/delete24.png"));
		listarProfissional = new JMenuItem ("Listar Profissional");
		listarProfissional.setIcon(new ImageIcon("image/ver.png"));
		sairProf = new JMenuItem("Sair");
		sairProf.setIcon(new ImageIcon("image/logout24.png"));
		
		menuProf.add(novoProfissonal);
		menuProf.add(editarProfissional);
		menuProf.add(eliminarProfissional);
		menuProf.add(listarProfissional);
		menuProf.addSeparator();
		menuProf.add(sairProf);
		
		//items do menu Tabelas
		especialidadeItem = new JMenuItem("Adicionar Especialidade");
		especialidadeItem.setIcon(new ImageIcon("image/next24.png"));
		tipoImovelItem = new JMenuItem("Adicionar Tipo de Imovel");
		tipoImovelItem.setIcon(new ImageIcon("image/next24.png"));
		provinciaItem = new JMenuItem("Adicionar Provincia");
		provinciaItem.setIcon(new ImageIcon("image/next24.png"));
		municipioItem = new JMenuItem("Adicionar Municipio");
		municipioItem.setIcon(new ImageIcon("image/next24.png"));
		comunaItem = new JMenuItem("Adicionar Comuna");
		comunaItem.setIcon(new ImageIcon("image/next24.png"));

		menuTabelas.add(especialidadeItem);
		menuTabelas.add(tipoImovelItem);
		menuTabelas.add(provinciaItem);
		menuTabelas.add(municipioItem);
		menuTabelas.add(comunaItem);

		//Items do menu Pesquisas
		pesquisarCliente = new JMenuItem("Pesquisar Por Cliente");
		pesquisarCliente.setIcon(new ImageIcon("image/next24.png"));
		pesquisarServico = new JMenuItem("Pesquisar Por Servicos");
		pesquisarServico.setIcon(new ImageIcon("image/next24.png"));
		pesquisarProfissional = new JMenuItem("Pesquisar Por Profissional");
		pesquisarProfissional.setIcon(new ImageIcon("image/next24.png"));
		pesquisarAgendamento = new JMenuItem ("Pesquisar Por Agendamento");
		pesquisarAgendamento.setIcon(new ImageIcon("image/next24.png"));

		sobreNosItem = new JMenuItem("Sobre Nos");
		sobreNosItem.setIcon(new ImageIcon("image/next24.png"));

		menuPesquisas.add(pesquisarCliente);
		menuPesquisas.add(pesquisarServico);
		menuPesquisas.add(pesquisarProfissional);
		menuPesquisas.add(pesquisarAgendamento);

		ajudaMenu.add(sobreNosItem);

		//Adicionei os menus ao menuBar
		menuBar.add(menuCliente);
		menuBar.add(menuServico);
		menuBar.add(menuAgendamento);		
		menuBar.add(menuProf);
		menuBar.add(menuTabelas);
		menuBar.add(menuPesquisas);
		menuBar.add(ajudaMenu);

		//Adicionei o evento ao clicar nos Itens dos menus
		novoCliente.addActionListener(this);
		editarCliente.addActionListener(this);
		eliminarCliente.addActionListener(this);
		listarCliente.addActionListener(this);
		sairCliente.addActionListener(this);

		novoServico.addActionListener(this);
		editarServico.addActionListener(this);
		eliminarServico.addActionListener(this);
		listarServicos.addActionListener(this);
		sairServico.addActionListener(this);

		novoAgendamento.addActionListener(this);
		editarAgendamento.addActionListener(this);
		eliminarAgendamento.addActionListener(this);
		listarAgendamento.addActionListener(this);
		sair.addActionListener(this);

		novoProfissonal.addActionListener(this);
		editarProfissional.addActionListener(this);
		eliminarProfissional.addActionListener(this);
		listarProfissional.addActionListener(this);
		sairProf.addActionListener(this);
		
		especialidadeItem.addActionListener(this); 
		tipoImovelItem.addActionListener(this);
		provinciaItem.addActionListener(this);
		municipioItem.addActionListener(this);
		comunaItem.addActionListener(this);

		pesquisarCliente.addActionListener(this);
		pesquisarServico.addActionListener(this);
		pesquisarProfissional.addActionListener(this);
		pesquisarAgendamento.addActionListener(this);

		sobreNosItem.addActionListener(this);
		
	}

public void actionPerformed(ActionEvent evt)
	{
		
		if (evt.getSource() == novoCliente)
			new ClienteVisao(false, new ClienteModelo() );		
		else if (evt.getSource() == editarCliente)
			new EditarClienteVisao();
		 if (evt.getSource() == eliminarCliente)
		 	new EliminarCliente();
		else if (evt.getSource() == listarCliente)
			 ClienteFile.listarCliente();

		else if (evt.getSource() == novoServico)
			new ServicoVisao(false, new ServicoModelo() );		
		else if (evt.getSource() == editarServico)
			new EditarServicoVisao();
		else if (evt.getSource() == eliminarServico)
			new EliminarServicoVisao();
		else if (evt.getSource() == listarServicos)
			 ServicoFile.listarServicos();

		if (evt.getSource() == novoProfissonal)
			new ProfissionalVisao(false, new ProfissionalModelo() );		
		else if (evt.getSource() == editarProfissional)
			new EditarProfissionalVisao();
		else if (evt.getSource() == eliminarProfissional)
			new EliminarProfissionalVisao();
		else if (evt.getSource() == listarProfissional)
			ProfissionalFile.listarProfissional();

		if (evt.getSource() == novoAgendamento)
			new AgendamentoVisao(false, new AgendamentoModelo() );		
		else if (evt.getSource() == editarAgendamento)
			new EditarAgendamentoVisao();
		else if (evt.getSource() == eliminarAgendamento)
			new EliminarAgendamentoVisao();
		else if (evt.getSource() == listarAgendamento)
			AgendamentoFile.listarAgendamento();

		if(evt.getSource() == pesquisarCliente)
			new PesquisarCliente();
		else if (evt.getSource() == pesquisarServico)
			new PesquisarServicoVisao();
		else if (evt.getSource() == pesquisarProfissional)
			new PesquisarProfissionalVisao();
		else if (evt.getSource() == pesquisarAgendamento)
			new PesquisarAgendamentoVisao();

		else if (evt.getSource() == sobreNosItem)
{
	String output = 
		"Tema: Gestão de uma Empresa de Controlo de Pragas\n" +
		"Desenvolvido por: Gildo Kondi\n" +
		"ID: 33049\n" +
		"Data: 07/06/2025\n\n" +
		"Este sistema permite:\n" +
		"- Registar e gerir clientes\n" +
		"- Criar e editar serviços prestados\n" +
		"- Agendar visitas de controlo de pragas\n" +
		"- Cadastrar profissionais\n" +
		"- Pesquisar informações por nome, data ou ID\n\n" +
		"Implementação: Java com interface gráfica (Swing)\n" +
		"Armazenamento: Ficheiros binários\n" +
		"IDE utilizada: Notepad++\n\n" +
		"Aviso: Todos os dados são armazenados localmente.";

	JTextArea area = new JTextArea(20, 60);
	area.setText(output);
	area.setWrapStyleWord(true);
	area.setLineWrap(true);
	area.setCaretPosition(0);
	area.setEditable(false);
	area.setFocusable(false);

	JOptionPane.showMessageDialog(null, new JScrollPane(area), 
		"Ajuda - Sobre o Sistema", JOptionPane.INFORMATION_MESSAGE);
}


		if (evt.getSource() == especialidadeItem)
			Tabela2.editarNovosItems("Especialidade.tab", "Nova Especialidade");
		if (evt.getSource() == tipoImovelItem)
			Tabela2.editarNovosItems("TipoImovel.tab", "Novo Tipo Imovel");
		if (evt.getSource() == provinciaItem)
			Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
		if (evt.getSource() == municipioItem)
			Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincias", "Municipios", "Novo Municipio");
		if (evt.getSource() == comunaItem)
			Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", "Provincias", "Municipios", "Comunas", "Nova Comuna");

	}

	public static void main(String args[])
	{
		Vector_Tabelas.inic();
		new MenuPrincipalVisao( "Gildo" );
	}
}