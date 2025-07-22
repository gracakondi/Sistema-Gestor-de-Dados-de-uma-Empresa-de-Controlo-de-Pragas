/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: ClienteVisao.java
Data: 24.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class ClienteVisao extends JFrame
{
	PainelCentro centro;
	PainelSul sul;
	boolean editar;
	
	public ClienteVisao(boolean alterar, ClienteModelo modelo)
	{
		super("Cadastro de Novo Cliente");
		
		editar = alterar;
		
		definirTema();
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/clientes32.png"));
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
		private JTextField codJTF, nomeJTF, telefoneJTF, emailJTF;
		private JComboBox generoJCB;
		private String [] generosArray = {"Masculino", "Feminino"};
		private JTextFieldData txtData;
		private ClienteFile ClienteFile;
		
		public PainelCentro()
		{
			setLayout(new GridLayout(3, 4, 10, 5));
	
			ClienteFile = new ClienteFile();
			
			//linha1
			add( new JLabel("Codigo:") );
			add( codJTF = new JTextField() );
			codJTF.setText( "000" + ClienteFile.getProximoCodigo() );
			codJTF.setFocusable(false);
			
			add( new JLabel("Adicione o Nome:") );
			add( nomeJTF = new JTextField() );
			
			//linha2
			add( new JLabel("Adicione o Telefone:") );
			add( telefoneJTF = new JTextField() );

			add( new JLabel("Escolha o seu Género") );
			add( generoJCB = new JComboBox( generosArray ) );

			//linha3
			add( new JLabel("Escolha a sua Data de Nascimento") );
			JPanel painelData = new JPanel( new GridLayout(1, 1) );

			txtData = new JTextFieldData("Data?");
			painelData.add( txtData.getDTestField() );
			painelData.add( txtData.getDButton() );
			add(painelData);
			
			add( new JLabel("Adicione um Email") );
			add( emailJTF = new JTextField() );
			
		}
		public PainelCentro(ClienteModelo modelo)
		{
			setLayout(new GridLayout(3, 4, 10, 5));
			
			ClienteFile = new ClienteFile();
			
			//linha1
			add( new JLabel("Codigo:") );
			add( codJTF = new JTextField() );
			codJTF.setText( "000"+ modelo.getCodCliente() );
			codJTF.setFocusable(false);
			
			add( new JLabel("Nome:") );
			add( nomeJTF = new JTextField() );
			nomeJTF.setText( modelo.getNome() );
			
			//linha2
			add( new JLabel("Telefone") );
			add( telefoneJTF = new JTextField() );
			telefoneJTF.setText( "" + modelo.getTelefone() );

			add( new JLabel("Email") );
			add( emailJTF = new JTextField() );
			emailJTF.setText( modelo.getEmail() );

			//linha3
			add( new JLabel("Genero") );
			add( generoJCB = new JComboBox( generosArray ) );
			generoJCB.setSelectedItem( modelo.getGenero() );;
			
			add( new JLabel("Data de Nascimento") );
			JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data?");
			painelData.add( txtData.getDTestField() );
			painelData.add( txtData.getDButton() );
			add(painelData);	
			txtData.getDTestField().setText( modelo.getDataNascimento() );				
		}
		
		// Métodos GET // Já está certo
		public int getCodCliente()
		{
			return Integer.parseInt( codJTF.getText().trim());
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
		public String getEmail()
		{
			return emailJTF.getText().trim();
		}
		public String getDataNascimento()
		{
			return txtData.getDTestField().getText();
		}
		
		//Metodo SET // Já está certo
		public void setCodCliente(int codCliente)
		{
			codJTF.setText("" + codCliente);
		}
		public void setTelefone(int telefone)
		{
			telefoneJTF.setText( "" + telefone );
		}
		public void setNome(String nome)
		{
			nomeJTF.setText(nome);
		}
		public void setGenero(String genero)
		{
			generoJCB.setSelectedItem(genero);
		}
		public void setEmail(String email)
		{
			emailJTF.setText(email);
		}
		public void setDataNascimento(String data)
		{
			txtData.getDTestField().setText(data);
		}
		
		//--- salvar
		public void salvar()
		{			//DEVE SEGUIR A ORDEM DO CONSTRUTOR NO MODELO
			ClienteModelo modelo = new ClienteModelo(getCodCliente(), getTelefone(), 
			getNome(), getGenero(), getEmail(), getDataNascimento(), true);
			
			JOptionPane.showMessageDialog(null, modelo.toString() );
			
			modelo.salvar();			
			dispose();
		}
		 
		//--- alterar
		public void alterar()
		{			
			ClienteModelo modelo = new ClienteModelo(getCodCliente(), getTelefone(), 
			getNome(), getGenero(), getEmail(), getDataNascimento(), true);
					
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

		public static void main(String args[])
	{
		new ClienteVisao(false, new ClienteModelo());
	}
}
