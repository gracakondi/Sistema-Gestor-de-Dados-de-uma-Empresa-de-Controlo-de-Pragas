/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: ProfissionalVisao.java
Data: 24.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class ProfissionalVisao extends JFrame
{
	PainelCentro centro;
	PainelSul sul;
	boolean editar;
	
	public ProfissionalVisao(boolean alterar, ProfissionalModelo modelo)
	{
		super("Cadastro de Novo Profissional");
		
		editar = alterar;
		
		definirTema();
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/funcionario.png"));
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
		private JTextField codProfJTF, nomeJTF, telefoneJTF; 
		private JComboBox  generoJCB, cargoJCB, especialidadeJCB;
		private String [] generosArray = {"Masculino", "Feminino", "Personalizado"};
		private String [] cargoArray = { "Técnico Operacional", "Auxiliar de Serviços", "Supervisor Operacional"};
		private ProfissionalFile ProfissionalFile;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 4, 10, 5) );
			ProfissionalFile = new ProfissionalFile();
			
			//linha1
			add( new JLabel("Codigo ") );
			add( codProfJTF = new JTextField() );
			codProfJTF.setText( "000" + ProfissionalFile.getProximoCodigo() );
			codProfJTF.setFocusable(false);
			
			add( new JLabel("Nome") );
			add( nomeJTF = new JTextField() );
			
			//linha2
			add( new JLabel("Cargo") );
			add( cargoJCB = new JComboBox( cargoArray ) );

			add( new JLabel("Especialidade") );
			add( especialidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Especialidade.tab") );
			
			add( new JLabel("Genero") );
			add( generoJCB = new JComboBox( generosArray ) );
			
			//linha3			
			add( new JLabel("Telefone") );
			add( telefoneJTF = new JTextField() );
			
		}

		public PainelCentro(ProfissionalModelo modelo)
		{
			setLayout( new GridLayout(3, 4, 10, 5) );
			ProfissionalFile = new ProfissionalFile();

			//linha1
			add( new JLabel("Codigo") );
			add( codProfJTF = new JTextField() );
			codProfJTF.setText( "000"+ modelo.getCodProfissional() );
			codProfJTF.setFocusable(false);
			
			add( new JLabel("Nome") );
			add( nomeJTF = new JTextField() );
			nomeJTF.setText( modelo.getNome() );
			
			//linha2
			add( new JLabel("Cargo") );
			add( cargoJCB = new JComboBox( cargoArray ) );
			cargoJCB.setSelectedItem( modelo.getCargo() );

			add( new JLabel("Especialidade") );
			add( especialidadeJCB = UInterfaceBox.createJComboBoxsTabela2("Especialidade.tab") );
			especialidadeJCB.setSelectedItem( modelo.getEspecialidade() );

			add( new JLabel("Genero") );
			add( generoJCB = new JComboBox( generosArray ) );
			generoJCB.setSelectedItem( modelo.getGenero() );
			
			//linha3
			
			add( new JLabel("Telefone") );
			add( telefoneJTF = new JTextField() );
			telefoneJTF.setText( "+244" + modelo.getTelefone() );
		}
		
		// Métodos GET
		public int getCodProfissional()
		{
			return Integer.parseInt( codProfJTF.getText().trim());
		}
		public int getTelefone()
		{
			return Integer.parseInt( telefoneJTF.getText().trim());
		}
		public String getNome()
		{
			return nomeJTF.getText().trim();
		}
		public String getGenero()
		{
			return String.valueOf( generoJCB.getSelectedItem() );
		}
		public String getCargo()
		{
			return String.valueOf( cargoJCB.getSelectedItem() );
		}
		public String getEspecialidade()
		{
		return String.valueOf( especialidadeJCB.getSelectedItem() );
		}
		
		
		//--- metodos set
		public void setCodProfissional(int id)
		{
			codProfJTF.setText("" + id);
		}
		public void setTelefone(int telefone)
		{
			telefoneJTF.setText("" + telefone);
		}
		public void setNome(String nome)
		{
			nomeJTF.setText(nome);
		}
		public void setGenero(String genero)
		{
			generoJCB.setSelectedItem(genero);
		}
		public void setCargo(String cargo)
		{
			cargoJCB.setSelectedItem(cargo);
		}
		public void setEspecialidade(String especialidade)
		{
			especialidadeJCB.setSelectedItem(especialidade);
		}
		
		
		//--- salvar
		public void salvar()
		{			
			ProfissionalModelo modelo = new ProfissionalModelo(getCodProfissional(), getTelefone(), 
				getNome(), getCargo(), getGenero(), getEspecialidade(),  true);
					
			JOptionPane.showMessageDialog(null, modelo.toString() );
			
			modelo.salvar();			
			dispose();
		}
		
		//--- alterar
		public void alterar()
		{			
			ProfissionalModelo modelo = new ProfissionalModelo(getCodProfissional(), getTelefone(), getNome(), 
			getCargo(), getGenero(), getEspecialidade(),  true);
					
			JOptionPane.showMessageDialog(null, modelo.toString() );
			
			modelo.salvarDados();			
			dispose();
		}
		
	}
	
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
		new ProfissionalVisao(false, new ProfissionalModelo());
	}
}