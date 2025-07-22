/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: PesquisarAgendamento.java
Data: 03.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;


public class PesquisarAgendamentoVisao extends JFrame
{	
	PainelCentro centro;
	PainelSul sul;
	
	public PesquisarAgendamentoVisao()
	{
		super("Pesquisar Por Agendamentos");
		
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JComboBox dataProcuradaJTF;
		JTextField idProcuradoJTF;
		JRadioButton pesquisarPorIDJRB, pesquisarPorDataJRB;
		ButtonGroup pesquisar;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 2));
			
			pesquisar = new ButtonGroup();
			
			add( pesquisarPorIDJRB = new JRadioButton("Pesquisar Por Data", true) );
			add( pesquisarPorDataJRB = new JRadioButton("Pesquisar Por Código", false) );
			pesquisar.add(pesquisarPorIDJRB);
			pesquisar.add(pesquisarPorDataJRB);
			
			add( new JLabel("Escolha a Data Procurada") );
			add( dataProcuradaJTF = new JComboBox( AgendamentoFile.getAllDates() ) );
			
			add( new JLabel("Digite o Código Procurado") );
			add( idProcuradoJTF = new JTextField() );
			idProcuradoJTF.setEnabled(false);
			
			pesquisarPorIDJRB.addActionListener(this);
			pesquisarPorDataJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorIDJRB)
			{
				dataProcuradaJTF.setEnabled(true);
				idProcuradoJTF.setEnabled(false);
			}
			else
			{
				dataProcuradaJTF.setEnabled(false);
				idProcuradoJTF.setEnabled(true);
			}
		}
		
		public int getTipoPesquisa()
		{
			if (pesquisarPorIDJRB.isSelected())
				return 1;
			else
				return 2;
		}
		public int getIDProcurado()
		{
			return Integer.parseInt( idProcuradoJTF.getText().trim());
		}
		public String getDataProcurada()
		{
			return String.valueOf(dataProcuradaJTF.getSelectedItem());
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
				if (centro.getTipoPesquisa() == 1)
					AgendamentoFile.pesquisarAgendamentoPorData( centro.getDataProcurada() );
				else
					AgendamentoFile.pesquisarAgendamentoPorID( centro.getIDProcurado() );
			else
				dispose();
		}

		
	}

	public static void main(String[] args) {
			new PesquisarAgendamentoVisao();
		}
}