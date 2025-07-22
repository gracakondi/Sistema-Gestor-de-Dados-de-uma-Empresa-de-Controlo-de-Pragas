/*------------------------------------
Tema: Gestão de uma Empresa de Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: EditarClienteVisao.java
Data: 10.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarClienteVisao extends JFrame
{	
	
	PainelCentro centro;
	PainelSul sul;
	
	public EditarClienteVisao()
	{
		super("Pesquisar para Editar Dados");
		
		getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel implements ActionListener
	{
		JComboBox nomeJCB;
		JTextField codProcuradoJTF;
		JRadioButton pesquisarPorNomeJRB, pesquisarPorCodJRB;
		ButtonGroup pesquisar;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 2));
			
			pesquisar = new ButtonGroup();
			
			add( pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome", true) );
			add( pesquisarPorCodJRB = new JRadioButton("Pesquisar Por Codigo", false) );
			pesquisar.add(pesquisarPorNomeJRB);
			pesquisar.add(pesquisarPorCodJRB);
			
			add( new JLabel("Escolha o Nome Procurado") );
			add( nomeJCB = new JComboBox( ClienteFile.getAllNames() ) );
			
			add( new JLabel("Digite o Código Procurado") );
			add( codProcuradoJTF = new JTextField() );
			codProcuradoJTF.setEnabled(false);
			
			pesquisarPorNomeJRB.addActionListener(this);
			pesquisarPorCodJRB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == pesquisarPorNomeJRB)
			{
				nomeJCB.setEnabled(true);
				codProcuradoJTF.setEnabled(false);
			}
			else
			{
				nomeJCB.setEnabled(false);
				codProcuradoJTF.setEnabled(true);
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
			return String.valueOf(nomeJCB.getSelectedItem());
		}
		public int getCodProcurado()
		{
			return Integer.parseInt( codProcuradoJTF.getText().trim());
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
				ClienteModelo modelo;
				
				if (centro.getTipoPesquisa() == 1)
				{
					modelo = ClienteFile.getClientePorNome( centro.getNomeProcurado() );
					new ClienteVisao(true, modelo);
				}
				else
					modelo = ClienteFile.getClientePorID( centro.getCodProcurado() );
					new ClienteVisao(true, modelo);
			}
			else
				dispose();
		}

		
	}

	public static void main(String[] args) {
			new EditarClienteVisao();
		}
}
