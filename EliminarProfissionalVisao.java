/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33O49
Ficheiro: EliminarCliente.java
Data: 14.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarProfissionalVisao extends JFrame
{	
	
	PainelCentro centro;
	PainelSul sul;
	
	public EliminarProfissionalVisao()
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
		JComboBox nomeProfJCB, espProcuradaJCB;
		JRadioButton pesquisarPorNomeJRB, pesquisarPorCargoJRB;
		ButtonGroup pesquisar;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 2));
			
			pesquisar = new ButtonGroup();
			
			add( pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome", true) );
			add( pesquisarPorCargoJRB = new JRadioButton("Pesquisar Por Especialidade", false) );
			pesquisar.add(pesquisarPorNomeJRB);
			pesquisar.add(pesquisarPorCargoJRB);
			
			add( new JLabel("Escolha Por Nome") );
			add( nomeProfJCB = new JComboBox( ProfissionalFile.getAllNames() ) );
			
			add( new JLabel("Escolha Por Especialidade") );
			add( espProcuradaJCB = new JComboBox( ProfissionalFile.getAllEspecialidade() ) );
			espProcuradaJCB.setEnabled(false);
			
			pesquisarPorNomeJRB.addActionListener(this);
			pesquisarPorCargoJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorNomeJRB)
			{
				nomeProfJCB.setEnabled(true);
				espProcuradaJCB.setEnabled(false);
			}
			else
			{
				nomeProfJCB.setEnabled(false);
				espProcuradaJCB.setEnabled(true);
			}
		}
		
		public int getTipoPesquisa()
		{
			if (pesquisarPorNomeJRB.isSelected())
				return 1;
			else
				return 2;
		}
		public String getNomeProcurado()
		{
			return String.valueOf(nomeProfJCB.getSelectedItem());
		}
		public String getCodProcurado()
		{
			return String.valueOf(espProcuradaJCB.getSelectedItem());
		}
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton pesquisarJB, cancelarJB;
		
		public PainelSul()
		{
			add(pesquisarJB = new JButton("Eliminar", new ImageIcon("image/delete24.png") ) );
			add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png") ) );
			
			pesquisarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarJB)
			{
				ProfissionalModelo modelo;
				
				if (centro.getTipoPesquisa() == 1)
				{
					modelo = ProfissionalFile.getProfPorNome( centro.getNomeProcurado() );
					
					JOptionPane.showMessageDialog(null, modelo.toString() );
					
					int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja eliminar estres dados?");
					
					if (opcao == JOptionPane.YES_OPTION)
					{
						//eliminar dados
						
						modelo.setStatus(false);
						
						new ProfissionalFile().eliminarDados( modelo );
					}
					else
						JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador!");
				}
				else
					ProfissionalFile.pesquisarProfPorEspecialidade( centro.getCodProcurado() );
			}
			else
				dispose();
		}
	}

	public static void main(String[] args) {
			new EliminarProfissionalVisao();
		}
}