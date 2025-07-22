/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33O49
Ficheiro: EliminarServicoVisao.java
Data: 14.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarServicoVisao extends JFrame
{	
	
	PainelCentro centro;
	PainelSul sul;
	
	public EliminarServicoVisao()
	{
		super("Pesquisar para Eliminar Dados");
		
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JComboBox servicoJCB;
		JRadioButton pesquisarPorServicoJRB;
		ButtonGroup pesquisar;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 2));
			
			pesquisar = new ButtonGroup();
			
			add( pesquisarPorServicoJRB = new JRadioButton("Pesquisar Por   Servico", true) );
			pesquisar.add(pesquisarPorServicoJRB);
			
			add( new JLabel("Escolha o Nome Procurado") );
			add( servicoJCB = new JComboBox( ServicoFile.getAllServices() ) );
			
			
			pesquisarPorServicoJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorServicoJRB)
			{
				servicoJCB.setEnabled(true);
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
			add(pesquisarJB = new JButton("Eliminar", 
			new ImageIcon("image/delete24.png") ) );
			add(cancelarJB = new JButton("Cancelar", 
			new ImageIcon("image/cancel24.png") ) );
			
			pesquisarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarJB)
			{
				ServicoModelo modelo; 
				
					modelo = ServicoFile.getServicoPorNome( centro.getNomeProcurado() );
					
					JOptionPane.showMessageDialog(null, modelo.toString() );
					
					int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja eliminar estres dados?");
					
					if (opcao == JOptionPane.YES_OPTION)
					{
						//eliminar dados
						
						modelo.setStatus(false);
						
						new ServicoFile().eliminarDados( modelo );
					}
					else
						JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador!");
			}
			else
				dispose();
		}
	}

	public static void main(String[] args) {
			new EliminarServicoVisao();
		}
}