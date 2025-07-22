/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: EditarProfissionalVisao.java
Data: 10.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarProfissionalVisao extends JFrame
{	
	
	PainelCentro centro;
	PainelSul sul;
	
	public EditarProfissionalVisao()
	{
		super("Pesquise para Editar Dados");
		
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JComboBox nomeProfJCB, especieProcuradaJCB;
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
			
			add( new JLabel("Escolha o Nome Procurado") );
			add( nomeProfJCB = new JComboBox( ProfissionalFile.getAllNames() ) );
			
			add( new JLabel("Escolha a Especialidade") );
			add( especieProcuradaJCB = new JComboBox( ProfissionalFile.getAllEspecialidade() ) );
			especieProcuradaJCB.setEnabled(false);
			
			pesquisarPorNomeJRB.addActionListener(this);
			pesquisarPorCargoJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorNomeJRB)
			{
				nomeProfJCB.setEnabled(true);
				especieProcuradaJCB.setEnabled(false);
			}
			else
			{
				nomeProfJCB.setEnabled(false);
				especieProcuradaJCB.setEnabled(true);
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
			return String.valueOf(especieProcuradaJCB.getSelectedItem());
		}
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton pesquisarJB, cancelarJB;
		
		public PainelSul()
		{
			add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.png") ) );
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
					new ProfissionalVisao(true, modelo);
				}
				else
					ProfissionalFile.pesquisarProfPorEspecialidade( centro.getCodProcurado() );
			}
			else
				dispose();
		}

		
	}

	public static void main(String[] args) {
			new EditarProfissionalVisao();
		}
}
