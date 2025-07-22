/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: PesquisarServicoVisao.java
Data: 03.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;


public class PesquisarServicoVisao extends JFrame
{	
	PainelCentro centro;
	PainelSul sul;
	
	public PesquisarServicoVisao()
	{
		super("Pesquisar Por Setvico");
		
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JComboBox servicoJCB;
		JTextField codProcuradoJTF;
		JRadioButton pesquisarPorServicoJRB;
		ButtonGroup pesquisar;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 2));
			
			pesquisar = new ButtonGroup();
			
			add( pesquisarPorServicoJRB = new JRadioButton("Pesquisar Por Servico", true) );
			pesquisar.add(pesquisarPorServicoJRB);
			
			add( new JLabel("Escolha o Servico Procurado") );
			add( servicoJCB = new JComboBox( ServicoFile.getAllServices() ) );
			
			pesquisarPorServicoJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorServicoJRB)
			{
				servicoJCB.setEnabled(true);
				codProcuradoJTF.setEnabled(false);
			}
			else
			{
				servicoJCB.setEnabled(false);
				codProcuradoJTF.setEnabled(true);
			}
		}
		
		public String getNomeProcurado()
		{
			return String.valueOf(servicoJCB.getSelectedItem());
		}
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton pesquisarJB, cancelarJB;
		
		public PainelSul()
		{
			add(pesquisarJB = new JButton("Pesquisar", 
			new ImageIcon("image/search32.png") ) );
			add(cancelarJB = new JButton("Cancelar", 
			new ImageIcon("image/cancel24.png") ) );
			
			pesquisarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarJB)
				ServicoFile.pesquisarServicoPorNome( centro.getNomeProcurado() );
            else
				dispose();
		}

	}

	public static void main(String[] args) {
			new PesquisarServicoVisao();
		}
}