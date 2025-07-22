/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: AgendamentoVisao.java
Data: 24.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;


public class AgendamentoVisao extends JFrame
{
	PainelCentro centro;
	PainelSul sul;
	boolean editar;
	
	public AgendamentoVisao(boolean alterar, AgendamentoModelo modelo)
	{
		super("Cadastro de Novo Agendamento");
		
		editar = alterar;
		
		definirTema();
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/tables.png"));
		painelNorte.add(lbImagem);
		
		getContentPane().add(painelNorte, BorderLayout.NORTH);
		
		if (!alterar)
			getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		else
			getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
		
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	class PainelCentro extends JPanel
	{
		private JTextField codAgJTF, enderecoJTF, horaAgendaJTF;
		private JComboBox servicoJCB, profissionalJCB, clienteJCB;
		private JComboBoxPersonal provinciasJCB, municipiosJCB, comunasJCB, tipoimovelJCB;
		private JComboBoxTabela3_Tabela3 provMunCom;
		private JTextFieldData txtData;
		private AgendamentoFile AgendamentoFile;
		private ServicoFile ServicoFile;
		
		
		public PainelCentro()
		{
			setLayout( new GridLayout(6, 10, 30, 10) );
			provMunCom = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
			AgendamentoFile = new AgendamentoFile();
			ServicoFile = new ServicoFile();
			
			//linha1
			add( new JLabel("Cod Agen") );
			add( codAgJTF = new JTextField() );
			codAgJTF.setText( "000" + AgendamentoFile.getProximoCodigo() );
			codAgJTF.setFocusable(false);
			
			add( new JLabel("Endereco") );
			add( enderecoJTF = new JTextField() );

			//linha2
			add( new JLabel("Hora ") );
			add( horaAgendaJTF = new JTextField() );

			add( new JLabel("Data") );
			JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data?");
			painelData.add( txtData.getDTestField() );
			painelData.add( txtData.getDButton() );
			add(painelData);

			//linha3
			add( new JLabel("Tipo de Imovel") );
			add( tipoimovelJCB = UInterfaceBox.createJComboBoxsTabela2("TipoImovel.tab") );

			add( new JLabel("Provincia") );
			add( provinciasJCB = provMunCom.getComboBoxFather() );

			//linha4
			add( new JLabel("Municipio") );
			add( municipiosJCB = provMunCom.getComboBoxSun() );
						
			add( new JLabel("Comuna") );
			add( comunasJCB = provMunCom.getComboBoxNeto() );

			//linha5
			add( new JLabel ("Servico"));
			add( servicoJCB = new JComboBox( ServicoFile.getAllServices()));

			add( new JLabel("Profissional"));
			add( profissionalJCB = new JComboBox( ProfissionalFile.getAllNames()));

			//linha6
			add( new JLabel("Cliente"));
			add( clienteJCB = new JComboBox( ClienteFile.getAllNames()));
						
		}
		public PainelCentro(AgendamentoModelo modelo)
		{
			setLayout( new GridLayout(6, 4, 30, 15) );
			provMunCom = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
			AgendamentoFile = new AgendamentoFile();
			ServicoFile = new ServicoFile();
			//linha1
			add( new JLabel("Codiigo") );
			add( codAgJTF = new JTextField() );
			codAgJTF.setText( "000"+ modelo.getCodAg() );
			codAgJTF.setFocusable(false);
			
			add( new JLabel("Endereco") );
			add( enderecoJTF = new JTextField() );
			enderecoJTF.setText( modelo.getEndereco() );
			
			//linha2
			add( new JLabel("Tipo de Imovel") );
			add( tipoimovelJCB = UInterfaceBox.createJComboBoxsTabela2("TipoImovel.tab") );
			tipoimovelJCB.setSelectedItem(modelo.getTipoimovel() );

			add( new JLabel("Hora da Agenda") );
			add( horaAgendaJTF = new JTextField() );
			horaAgendaJTF.setText(""+ modelo.getHoraAgenda() );
			
			//linha3
			add( new JLabel("Data de Agendamento") );
			JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data?");
			painelData.add( txtData.getDTestField() );
			painelData.add( txtData.getDButton() );
			add(painelData);	
			txtData.getDTestField().setText( modelo.getDataAgenda() );
			
			add( new JLabel("Provincia") );
			add( provinciasJCB = provMunCom.getComboBoxFather() );
			provinciasJCB.setSelectedItem( modelo.getProvincia() );

			//linha4
			add( new JLabel("Municipio") );
			add( municipiosJCB = provMunCom.getComboBoxSun() );
			municipiosJCB.setSelectedItem( modelo.getMunicipio() );
			
			
			add( new JLabel("Comuna") );
			add( comunasJCB = provMunCom.getComboBoxNeto() );
			comunasJCB.setSelectedItem( modelo.getComuna() );

			//linha5
			add( new JLabel ("Servico"));
			add( servicoJCB = new JComboBox( ServicoFile.getAllServices()));

			add( new JLabel("Profissional"));
			add( profissionalJCB = new JComboBox( ProfissionalFile.getAllNames()));

			//linha6
			add( new JLabel("Cliente"));
			add( clienteJCB = new JComboBox( ClienteFile.getAllNames()));
		}
		
		//--- metodos get
		public int getCodAg()
		{
			return Integer.parseInt( codAgJTF.getText().trim());
		}
		public int getHoraAgenda()
		{
			return Integer.parseInt( horaAgendaJTF.getText().trim());
		}
		public String getEndereco()
		{
			return enderecoJTF.getText().trim();
		}
		public String getTipoimovel()
		{
			return String.valueOf( tipoimovelJCB.getSelectedItem() );
		}
		public String getDataAgenda()
		{
			return txtData.getDTestField().getText();
		}
			public String getProvincia()
		{
			return String.valueOf( provinciasJCB.getSelectedItem() );
		}
		public String getMunicipio()
		{
			return String.valueOf(municipiosJCB.getSelectedItem() );
		}
		public String getComuna()
		{
			return String.valueOf( comunasJCB.getSelectedItem() );
		}
			public String getServico()
		{
			return String.valueOf( servicoJCB.getSelectedItem() );
		}
		public String getProfissional()
		{
			return String.valueOf( profissionalJCB.getSelectedItem() );
		}
		public String getCliente()
		{
			return String.valueOf( clienteJCB.getSelectedItem() );
		}
		
		//--- metodos set
		public void setCodAg(int id)
		{
			codAgJTF.setText("" + id);
		}
		public void setHoraAgenda(int horaAgenda)
		{
			horaAgendaJTF.setText("" + horaAgenda);
		}
		public void setEndereco(String endereco)
		{
			enderecoJTF.setText(endereco);
		}
		public void setTipoimovel(String tipoImovel)
		{
			tipoimovelJCB.setSelectedItem( tipoImovel );
		}
		public void setDataAgenda (String data)
		{
			txtData.getDTestField().setText(data);
		}
		public void setProvincia(String provincia)
		{
			provinciasJCB.setSelectedItem(provincia);
		}
		public void setMunicipio(String municipio)
		{
			municipiosJCB.setSelectedItem(municipio);
		}
		public void setComuna(String comuna)
		{
			comunasJCB.setSelectedItem(comuna);
		}
		public void setServico(String servico)
		{
			servicoJCB.setSelectedItem( servico );
		}
		public void setProfissional(String profissional)
		{
			profissionalJCB.setSelectedItem( profissional );
		}
		public void setCliente(String cliente)
		{
			clienteJCB.setSelectedItem( cliente );
		}

		//--- salvar
		public void salvar()
		{			
				try {
		// buscar o objeto real do serviço pelo nome selecionado
		String nomeServico = getServico();
		ServicoModelo servicoSelecionado = ServicoFile.buscarPorNome(nomeServico);

		AgendamentoModelo modelo = new AgendamentoModelo(
			getCodAg(),
			getHoraAgenda(),
			getEndereco(),
			getTipoimovel(),
			getDataAgenda(),
			getProvincia(),
			getMunicipio(),
			getComuna(),
			servicoSelecionado,
		 	getProfissional(),
			getCliente(),
			true
		);

		JOptionPane.showMessageDialog(null, modelo.toString());
		modelo.salvar();
		dispose();

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao salvar o agendamento.");
				}
		}
		
		//--- alterar
		public void alterar()
		{			
			try {
				// buscar o objeto real do serviço pelo nome selecionado
				String nomeServico = getServico();
				ServicoModelo servicoSelecionado = ServicoFile.buscarPorNome(nomeServico); // tu vais criar esse método!

			AgendamentoModelo modelo = new AgendamentoModelo(
			getCodAg(),
			getHoraAgenda(),
			getEndereco(),
			getTipoimovel(),
			getDataAgenda(),
			getProvincia(),
			getMunicipio(),
			getComuna(),
			servicoSelecionado,
		 	getProfissional(),
			getCliente(),
			true
			);

		JOptionPane.showMessageDialog(null, modelo.toString());
		modelo.salvarDados();
		dispose();

	} catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erro ao salvar o agendamento.");
	}
		
	}  }
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add(salvarJB = new JButton("Salvar", new ImageIcon("image/save24.png") ) );
			add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png")) );
			
			salvarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == salvarJB)
				if (editar)
					centro.alterar();
				else
					centro.salvar();
			else
				dispose();
		}
	}
	
	 public void definirTema() 
	 {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }
public static void main(String[] args) {
	Vector_Tabelas.inic();
		new AgendamentoVisao(false, new AgendamentoModelo());
	}
}
