/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: ApresentacaoVisao.java
Data:20.05.2025
---------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;

public class ApresentacaoVisao extends JFrame
{
	
	private PainelCentro centro;
	private PainelSul sul;

	public ApresentacaoVisao()
	{
		super("Tela de Boas Vindas");
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/topo.jpg"));
		painelNorte.add(lbImagem);
		
		getContentPane().add(painelNorte, BorderLayout.NORTH);
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JTextArea areaPrincipal;
		JCheckBox concordarJCB;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(2, 1) );
			
			add( new JScrollPane( areaPrincipal = new JTextArea(40, 30) ) );
			areaPrincipal.setFocusable(false);
			areaPrincipal.setText("Bem vindo ao Sistema de Gestao de Controlo de Pragas\n\n" +
			"\tEste sistema foi concebido para facilitar a gestao de informacoes \n" + 
			"\tdentro da empresa, permitindo registrar clientes, servicos e funcionarios \n" + 
			"\t e tambem localizar os seus dados de forma rapida e segura.\n\n" + 
			"\tEste projecto foi criado no ambito da cadeira de Fundamentos de Programacao II, \n" +
			"\tno Curso de Engenharia Informatica da UCAN. \n" + 
			"\tno pelo estudante Gildo Kondi - 33049. \n" + 
			"\tÉ de uso exclusivo aos estudantes e professores desta instituicao. \n\n" + 
			"\tSe concorda com os termos e condicoes clica em concordar para avancar");
			
			add( concordarJCB = new JCheckBox("Aceito os termos de uso!") );
			
			concordarJCB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{			
			if (evt.getSource() == concordarJCB)
				if (concordarJCB.isSelected())
					sul.activarBotao(true);
				else
					sul.activarBotao(false);
		}
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		private JButton entrarJB, sairJB;
		
		public PainelSul()
		{
			add( entrarJB = new JButton("Entrar") );
			add( sairJB = new JButton("Sair") );
			
			activarBotao( false );
			
			entrarJB.addActionListener(this);
			sairJB.addActionListener(this);
		}
		
		public void activarBotao(boolean status)
		{
			entrarJB.setEnabled(status);
		}
		
		public void actionPerformed(ActionEvent evt)
		{			
			if (evt.getSource() == entrarJB)
			{
				dispose();
				new LoginVisao();
			}
			else
				dispose();
		}
	}
	
	public static void main(String args[])
	{
		Vector_Tabelas.inic();
		new ApresentacaoVisao();
	}
}