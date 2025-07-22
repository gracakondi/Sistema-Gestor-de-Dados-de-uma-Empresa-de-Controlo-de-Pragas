/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: LoginVisao.java
Data:07.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginVisao extends JFrame
{	
	private PainelCentro centro;
	private PainelSul sul;
	
	public LoginVisao()
	{
		super("Tela de Login");
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/v.JPG"));
		painelNorte.add(lbImagem);
		
		getContentPane().add(painelNorte, BorderLayout.NORTH);
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel
	{
		private JTextField userJTF;
		private JPasswordField passwordJPF;
		private String correctUser = "33049", correctPassword = "ucan";
		
		public PainelCentro()
		{
			setLayout( new GridLayout(2, 2) );
				
			add( new JLabel("Username: ") );
			add( userJTF = new JTextField());
			
			add( new JLabel("Password: ") );
			add( passwordJPF = new JPasswordField());	
		}
		
		public String getUser()
		{
			return userJTF.getText().trim();
		}

		public String getPassword()
		{
			return passwordJPF.getText().trim();
		}
		
		public boolean loginValido()
		{
			if (getUser().equals(correctUser) && getPassword().equals(correctPassword) )
				return true;
			return false;
		}		
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		private JButton entrarJB, sairJB;
		
		public PainelSul()
		{
			add( entrarJB = new JButton("Entrar") );
			add( sairJB = new JButton("Sair") );
				
			entrarJB.addActionListener(this);
			sairJB.addActionListener(this);
		}
				
		public void actionPerformed(ActionEvent evt)
		{			
			if (evt.getSource() == entrarJB)
			{
				if (centro.loginValido())
				{
					String user = centro.getUser();
					
					dispose();
					
					new MenuPrincipalVisao(user );
				}
				else
					JOptionPane.showMessageDialog(null, "Login invalido!", "Invalid Login", 
						JOptionPane.ERROR_MESSAGE);
			}
			else
				dispose();
		}
	}
	
	public static void main(String args[])
	{
	//	Vector_Tabelas.inic();
		
		new LoginVisao();
	}
}